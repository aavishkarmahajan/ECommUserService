package com.scaler.ECommUserService.service;

import com.scaler.ECommUserService.Exception.InvalidCredentialsException;
import com.scaler.ECommUserService.Exception.UserNotFoundException;
import com.scaler.ECommUserService.dto.UserDTO;
import com.scaler.ECommUserService.mapper.UserMapper;
import com.scaler.ECommUserService.model.Session;
import com.scaler.ECommUserService.model.SessionStatus;
import com.scaler.ECommUserService.model.User;
import com.scaler.ECommUserService.repository.SessionRepository;
import com.scaler.ECommUserService.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseEntity<UserDTO> login(String email, String password){
        //Get user details from DB
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with the given email address does not exist");
        }
        //Check user login password if matches
        User user = optionalUser.get();
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new InvalidCredentialsException("Invalid login credentials");
        }
        //token generation
        //String token = RandomStringUtils.randomAlphanumeric(30);
        MacAlgorithm alg = Jwts.SIG.HS256; //HS256 algo for JWT
        SecretKey key = alg.key().build(); //generate secret key

        //build claims
        Map<String, Object> jsonForJWT = new HashMap<>();
        jsonForJWT.put("email", user.getEmail());
        jsonForJWT.put("roles", user.getRoles());
        jsonForJWT.put("createdAt", new Date());
        jsonForJWT.put("expiryAt", new Date(LocalDate.now().plusDays(3).toEpochDay()));

        String token = Jwts.builder()
                .claims(jsonForJWT)
                .signWith(key,alg)
                .compact();

        //session creation
        Session session = new Session();
        session.setUser(user);
        session.setToken(token);
        session.setTokenStart(new Date());
        session.setSessionStatus(SessionStatus.ACTIVE);
        sessionRepository.save(session);
        //setting the token in header
        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, token);
        //return UserMapper.userToUserDTO(user);
        UserDTO userDTO = UserMapper.userToUserDTO(user);
        return new ResponseEntity<>(userDTO,headers, HttpStatus.OK);
    }

    public UserDTO signup(String email, String password){
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        User saveUser = userRepository.save(user);
        return UserMapper.userToUserDTO(user);
    }

}

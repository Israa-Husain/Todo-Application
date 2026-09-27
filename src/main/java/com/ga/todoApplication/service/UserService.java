package com.ga.todoApplication.service;


import com.ga.todoApplication.exception.InformationExistException;
import com.ga.todoApplication.model.User;
import com.ga.todoApplication.model.request.LoginRequest;
import com.ga.todoApplication.model.response.LoginResponse;
import com.ga.todoApplication.repository.UserRepository;
import com.ga.todoApplication.security.JWTUtils;
import com.ga.todoApplication.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private MyUserDetails myUserDetails;


    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, JWTUtils jwtUtils, @Lazy AuthenticationManager authenticationManager, @Lazy MyUserDetails myUserDetails){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils=jwtUtils;
        this.authenticationManager=authenticationManager;
        this.myUserDetails=myUserDetails;
    }

    public User createUser(User userObject){
        System.out.println("service calling createUser ==>");
        if(!userRepository.existsByEmailAddress(userObject.getEmailAddress())){
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("User with email address "+userObject.getEmailAddress()+ " already exists");
        }
    }

    public User findUserByEmailAddress(String email){
        return userRepository.findUserByEmailAddress(email);
    }


    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmailAddress(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
            final String JWT = jwtUtils.generateJwtToken(myUserDetails);
            return ResponseEntity.ok(new LoginResponse(JWT));
        } catch (Exception e) {
            return ResponseEntity.ok(
                    new LoginResponse("Error: user name or email is incorrect")
            );
        }
    }

}


package io.api.AutoInsure.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import io.api.AutoInsure.dto.AuthRequest;
import io.api.AutoInsure.entity.User;
import io.api.AutoInsure.service.JwtService;
import io.api.AutoInsure.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;



//    @PostMapping("/register")
//    public String addNewUser(@RequestBody User user) {
//        return userService.registerUser(user);
//    }

//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
//        return ResponseEntity.ok(userService.registerUser(user));
//    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        String message = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }
}
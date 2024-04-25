package io.api.AutoInsure.controller;

import io.api.AutoInsure.config.MyUserDetails;
import io.api.AutoInsure.dto.UserDTO;
import io.api.AutoInsure.exception.UnauthorizedException;
import io.api.AutoInsure.response.TokenResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/current-user")
    public ResponseEntity<UserDTO> currentUser() {
        try {
            UserDTO userDTO = userService.getCurrentUserDetails();
            return ResponseEntity.ok(userDTO);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        String message = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(authRequest.getUsername());
                TokenResponse tokenResponse = new TokenResponse(token);
                return ResponseEntity.ok(tokenResponse);
            } else {
            throw new UsernameNotFoundException("Invalid user request!");
            }
        } catch (AuthenticationException e) {
            throw new UsernameNotFoundException("Invalid username or password", e);
        }

    }
    // updating user details
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserDetails(@PathVariable int userId, @RequestBody User updatedUser) {
        User user = userService.updateUserDetails(userId, updatedUser);
        return ResponseEntity.ok(user);
    }
}


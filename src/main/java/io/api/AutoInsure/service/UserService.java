package io.api.AutoInsure.service;

import io.api.AutoInsure.dto.UserDTO;
import io.api.AutoInsure.exception.NotFoundException;
import jakarta.validation.constraints.Null;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.api.AutoInsure.config.MyUserDetails;
import io.api.AutoInsure.entity.User;
import io.api.AutoInsure.exception.UnauthorizedException;

import io.api.AutoInsure.repository.UserRepository;

import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;



    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
    public UserDTO getCurrentUserDetails() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof MyUserDetails userDetails) {
            return new UserDTO(
                    userDetails.getUser().getNationalId(),
                    userDetails.getUser().getFullName(),
                    userDetails.getUser().getEmail(),
                    userDetails.getUser().getPhoneNumber(),
                    userDetails.getUser().getCountry()
            );
        } else {
            throw new UnauthorizedException("User not authenticated");
        }
    }
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        userRepository.save(user);
        return "user added to system ";
    }

    public User updateUserDetails(int userId, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (updatedUser.getFullName() != null) {
                user.setFullName(updatedUser.getFullName());
            }
            if (updatedUser.getEmail() != null) {
                user.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getPhoneNumber() != null) {
                user.setPhoneNumber(updatedUser.getPhoneNumber());
            }
            if (updatedUser.getCountry() != null) {
                user.setCountry(updatedUser.getCountry());
            }

            return userRepository.save(user);
        } else {
            throw new NotFoundException("User not found");
        }
    }



}
package com.git.javier.javiers.controller;

import com.git.javier.javiers.dto.ChangePasswordRequest;
import com.git.javier.javiers.dto.EmailConfirmationRequest;
import com.git.javier.javiers.dto.LoginRequest;
import com.git.javier.javiers.exeption.ResourceNotFoundException;
import com.git.javier.javiers.model.User;
import com.git.javier.javiers.service.JwtService;
import com.git.javier.javiers.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        final UserDetails userDetails = userService.getUserByEmail(loginRequest.getEmail());
        final String jwt = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/register")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok("Login");
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody User user){
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        userService.changePassword(email, request);
        return ResponseEntity.ok().body("Password changed");
    }

    @PostMapping("/confirm-email")
    public ResponseEntity<?> confirmEmail(@RequestBody EmailConfirmationRequest request){
        try{
            userService.confirmEmail(request.getEmail(), request.getConfirmationCode());
            return ResponseEntity.ok().body("Email confirmed successfuly");
        }catch (BadCredentialsException e){
            return ResponseEntity.badRequest().body("Invalid confirmation code");
        }
        catch (ResourceNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    //update
    @GetMapping("/user/role")
    public ResponseEntity<String> getUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);

        if (user != null) {
            String role = String.valueOf(user.getRole());
            return ResponseEntity.ok(role);
        }
        return ResponseEntity.notFound().build();
    }

    //update
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers(); // Ensure this method exists in your UserService
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build(); // Returns 204 if no users are found
        }
        return ResponseEntity.ok(users); // Returns 200 with the list of users
    }

    @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
            try {
                userService.deleteUserById(id); // Asegúrate de que este método existe en UserService
                return ResponseEntity.ok("Usuario con ID " + id + " eliminado correctamente.");
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Usuario con ID " + id + " no encontrado.");
            }
        }

}


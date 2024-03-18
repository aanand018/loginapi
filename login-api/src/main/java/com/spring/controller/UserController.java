package com.spring.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entity.User;
import com.spring.repo.UserRepository;
import com.spring.service.OtpService;

//UserController.java
@RestController
@RequestMapping("/api/user")
public class UserController {
	
 @Autowired
 private UserRepository userRepository;

 @Autowired
 private OtpService otpService;

 
 @PostMapping("/register")
 public ResponseEntity<String> registerUser(@RequestBody Map<String, String> registrationRequest) {
     String phoneNumber = registrationRequest.get("phoneNumber");
     String otp = registrationRequest.get("otp");
     String password = registrationRequest.get("password");

     // Validate OTP before allowing user registration
     if (otpService.validateOtp(phoneNumber, otp)) {
         // Check if the user already exists
         if (userRepository.findByPhoneNumber(phoneNumber).isPresent()) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with this phone number already exists");
         }

         // Create a new user
         User newUser = new User();
         newUser.setPhoneNumber(phoneNumber);
         newUser.setPassword(password);
         newUser.setOtp(otp);

         // Save the user to the database
         userRepository.save(newUser);

         return ResponseEntity.ok("User registered successfully");
     } else {
         // Invalid OTP
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
     }
 
 
 }
 
 
 
 
 @PostMapping("/login")
 public ResponseEntity<String> loginUser(@RequestBody Map<String, String> loginRequest) {
     String phoneNumber = loginRequest.get("phoneNumber");
     String otp = loginRequest.get("otp");

     Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);

     if (userOptional.isPresent()) {
         User user = userOptional.get();

         if (otpService.validateOtp(phoneNumber, otp)) {
             // Successful login
             return ResponseEntity.ok("Login successful");
         } else {
             // Invalid OTP
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
         }
     } else {
         // User not found
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
     }
 }
 
 
 
 
 
 
}


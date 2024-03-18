package com.spring.service;

import java.util.Random;

import org.springframework.stereotype.Service;

//OtpServiceImpl.java
@Service
public class OtpServiceImpl implements OtpService {
	
 private static final int OTP_LENGTH = 6;

 @Override
 public String generateOtp() {
     // Generate OTP logic (you can use a library or implement your own)
     Random random = new Random();
     return String.format("%06d", random.nextInt(1000000));
 }

 @Override
 public boolean validateOtp(String phoneNumber, String otp) {
     // Validate OTP logic
     // Implement your verification logic, for example, compare with stored OTP in the database
	 
	
	 
	 
     return true; // Return true for simplicity
 }
}

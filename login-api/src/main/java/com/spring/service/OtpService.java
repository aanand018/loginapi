package com.spring.service;

//OtpService.java
public interface OtpService {
	
	
 String generateOtp();
 boolean validateOtp(String phoneNumber, String otp);
}


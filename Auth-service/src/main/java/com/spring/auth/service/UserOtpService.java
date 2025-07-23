package com.spring.auth.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.spring.auth.entity.OtpEntry;
import com.spring.auth.repository.OtpEntryRepo;

@Service
public class UserOtpService {

	@Autowired
	OtpEntryRepo entryRepo;
	
//	public String generateAlphaNumericOtp(int length) {
//	    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//	    SecureRandom random = new SecureRandom();
//	    StringBuilder sb = new StringBuilder();
//	    for (int i = 0; i < length; i++) {
//	        sb.append(chars.charAt(random.nextInt(chars.length())));
//	    }
//	    return sb.toString();

	public void storeOtp(String email) {
		Random random = new Random();
	    int otp = 100000 + random.nextInt(900000); // 6-digit
	     String.valueOf(otp);
	     System.out.println(otp);
	     String val=String.valueOf(otp);
	     OtpEntry entry=new OtpEntry(email,val);
	     entryRepo.save(entry);
	     
	}
	@Scheduled(fixedRate = 60000) // Every 1 min
	public void deleteExpiredOtps() {
	    List<OtpEntry> all = entryRepo.findAll();
	    for (OtpEntry otp : all) {
	        if (otp.isExpired()) {
	            entryRepo.delete(otp);
	        }
	    }
	}

}

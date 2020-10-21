package com.dansa.JBCouponProject.DailyTasks;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dansa.JBCouponProject.security.TokenManager;

@Component
public class TokenTimer {
	
	private static final int sleep= 1000*60*30; // 30 minutes
	
	@Autowired
	TokenManager tokenManager; 
	
	@Scheduled(fixedRate = sleep)
	public void deleteTokens() {
		System.err.println("under going token clean at: " + new Date().toLocaleString());
		tokenManager.deleteExpiredToken();
		System.err.println("finished token clensing, will be back in " + sleep/60000 + " minutes");
	}

}

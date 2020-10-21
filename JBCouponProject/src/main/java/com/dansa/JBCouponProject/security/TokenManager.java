package com.dansa.JBCouponProject.security;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.dansa.JBCouponProject.Service.ClientService;

import lombok.Getter;

@Component
@Getter
public class TokenManager {
	
	private Map<String, UserData> tokenMap = new HashMap<String, UserData>();
	
	public String addToken(ClientService clientService) {
		String token = UUID.randomUUID().toString(); 
		long time = System.currentTimeMillis();
		tokenMap.put(token, new UserData(clientService, time));
		return token;
		
	}

	public void deleteToken(String token) {
		tokenMap.remove(token);
		System.out.println("token: " + token + " was deleted");
	}
	
	public boolean doesTokenExist(String token) {
		if (tokenMap.containsKey(token)) {
			long time= System.currentTimeMillis();
			tokenMap.get(token).setTimeStamp(time);
			return true; 
		}
		return false;
	}
	
	
	public void deleteExpiredToken() {
		for (Map.Entry<String, UserData> entry : tokenMap.entrySet()) {
			UserData userData = entry.getValue();
			Date now = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30));
			Date then = new Date(userData.getTimeStamp());
			if (now.before(then)) {
				String token = entry.getKey();
				tokenMap.remove(token);
				System.err.println("client: " + entry.getKey() + " token has been deleted");
				
			}
		}
	}
}

package com.dansa.JBCouponProject.security;

import com.dansa.JBCouponProject.Service.ClientService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
	
	private ClientService clientService;
	private long timeStamp;

}

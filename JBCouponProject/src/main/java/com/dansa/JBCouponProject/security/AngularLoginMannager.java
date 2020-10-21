package com.dansa.JBCouponProject.security;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dansa.JBCouponProject.Beans.Clienttype;
import com.dansa.JBCouponProject.Service.AdminService;
import com.dansa.JBCouponProject.Service.CompanyService;
import com.dansa.JBCouponProject.Service.CustomerService;
import com.dansa.JBCouponProject.exeptions.loginFailedExeption;

@Service
public class AngularLoginMannager {

	@Autowired
	AdminService adminService;
	@Autowired
	CompanyService companyService;
	@Autowired
	CustomerService customerService;
	@Autowired
	TokenManager tokenManager;

	public String login(String email, String password, Clienttype clientType) throws loginFailedExeption, LoginException {

		switch (clientType) {
		case administor:
			if (adminService.login(email, password)) {
				System.out.println("logged in as admin");
				return tokenManager.addToken(adminService);
			}
		case company:
			if (companyService.login(email, password)) {
				System.out.println("logged in as company");
				return tokenManager.addToken(companyService);
			}
		case customer:
			if (customerService.login(email, password)) {
				System.out.println("logged in as customer");				
				return tokenManager.addToken(customerService);
			}
		default:
			throw new LoginException();

		}
	}

}

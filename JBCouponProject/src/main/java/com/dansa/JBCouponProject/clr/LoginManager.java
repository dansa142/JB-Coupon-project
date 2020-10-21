//package com.dansa.JBCouponProject.clr;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.dansa.JBCouponProject.Beans.Clienttype;
//import com.dansa.JBCouponProject.Service.AdminService;
//import com.dansa.JBCouponProject.Service.ClientService;
//import com.dansa.JBCouponProject.Service.CompanyService;
//import com.dansa.JBCouponProject.Service.CustomerService;
//import com.dansa.JBCouponProject.exeptions.loginFailedExeption;
//
//@Service
//public class LoginManager {
//	
//	@Autowired
//	private AdminService adminService; 
//	@Autowired
//	private CompanyService companyService;
//	@Autowired
//	private CustomerService customerService; 
//	
//	public ClientService login(String email, String password, Clienttype clientType) throws loginFailedExeption {
//		System.out.println("client type");
//		if (clientType == Clienttype.administor && adminService.login(email, password)) {
//			
//			return adminService; 
//		}else if (clientType == Clienttype.company && companyService.login(email, password)) {
//			return companyService; 
//		}else if (clientType == Clienttype.customer && customerService.login(email, password)) {
//			return customerService; 
//		} else {
//			System.out.println("login has failed, please check email and password");
//			return null; 
//		}
//	}
//
//}

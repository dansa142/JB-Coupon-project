package com.dansa.JBCouponProject.Rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dansa.JBCouponProject.Beans.Coupon;
import com.dansa.JBCouponProject.Service.AdminService;
import com.dansa.JBCouponProject.security.TokenManager;

@RestController
@RequestMapping("home")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class HomeControler {
	
	@Autowired
	AdminService adminService; 
	
	@Autowired
	TokenManager tokenManager;
	
	@GetMapping("get-all-coupons")
	public ResponseEntity<?> getAllCoupons() {
		return new ResponseEntity<List<Coupon>>(adminService.getAllCoupons(), HttpStatus.OK);
	}
	
	@DeleteMapping("logout/{token}")
	public ResponseEntity<?> logOut(@PathVariable String token){
		tokenManager.deleteToken(token);
		return new ResponseEntity<>("\" Token was deleted\"", HttpStatus.OK);
	}
	

}

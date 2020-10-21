package com.dansa.JBCouponProject.Rest;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dansa.JBCouponProject.Beans.Catagory;
import com.dansa.JBCouponProject.Beans.Clienttype;
import com.dansa.JBCouponProject.Beans.Coupon;
import com.dansa.JBCouponProject.Beans.Customer;
import com.dansa.JBCouponProject.Service.CustomerService;
import com.dansa.JBCouponProject.exeptions.doesNotExistExeption;
import com.dansa.JBCouponProject.exeptions.invalidActionExeption;
import com.dansa.JBCouponProject.exeptions.loginFailedExeption;
import com.dansa.JBCouponProject.security.AngularLoginMannager;
import com.dansa.JBCouponProject.security.TokenManager;

@RestController
@RequestMapping("customer")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CustomerController {
	
	@Autowired
	CustomerService customerService; 
	
	@Autowired
	AngularLoginMannager loginManager; 
	
	@Autowired
	TokenManager tokenManager;
	
	
	@PostMapping("login/{email}/{password}")
	public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password) throws LoginException {
		HttpHeaders responseHeaders = new HttpHeaders();
		try {
			String token = loginManager.login(email, password, Clienttype.customer);
			responseHeaders.set("token", token);
			responseHeaders.add("Access-Control-Expose-Headers", "Token");
			return ResponseEntity.ok().headers(responseHeaders).body("\"Logged in as company" + customerService.getCustomerDetails().getFirstName() + " " + customerService.getCustomerDetails().getLastName() + "\"");
		} catch (loginFailedExeption e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("purchase-coupon")
	public ResponseEntity<?> purchaseCoupon(@RequestBody Coupon coupon, @RequestHeader(name = "token", required = false) String token) throws doesNotExistExeption, invalidActionExeption{
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		customerService.purchaseCoupon(coupon);
		return new ResponseEntity<Coupon>(HttpStatus.NO_CONTENT); 
	}
	
	@GetMapping("get-all-customer-coupons")
	public ResponseEntity<?> getAllCustomerCoupons(@RequestHeader(name = "token", required = false) String token){
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<List<Coupon>>(customerService.getAllCustomerCoupons(), HttpStatus.OK);
	}
	
	@GetMapping("get-all-coupons-by-catagory/{catagory}")
	public ResponseEntity<?> getAllCouponsByCatagory(@PathVariable String catagory, @RequestHeader(name = "token", required = false) String token){
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		Catagory cata = Catagory.valueOf(catagory);
		return new ResponseEntity<List<Coupon>>(customerService.getCouponsByCatagory(cata), HttpStatus.OK);
	}
	
	@GetMapping("my-coupons-by-catagory/{catagory}")
	public ResponseEntity<?> getCustomerCouponsByCatagory(@PathVariable String catagory, @RequestHeader(name = "token", required = false) String token){
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		Catagory cata = Catagory.valueOf(catagory);
		return new ResponseEntity<List<Coupon>>(customerService.getCustomerCouponsByCatagory(cata), HttpStatus.OK);
	}
	
	@GetMapping("get-coupons-by-price/{price}")
	public ResponseEntity<?> getAllCouponsByPrice(@PathVariable double price, @RequestHeader(name = "token", required = false) String token){
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<List<Coupon>>(customerService.getCouponsByPrice(price), HttpStatus.OK);
	}
	
	@GetMapping("get-customer-details")
	public ResponseEntity<?> getCustomerDetails(@RequestHeader(name = "token", required = false) String token){
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<Customer>(customerService.getCustomerDetails(), HttpStatus.OK); 
	}
	
	@GetMapping("get-all-coupons")
	public ResponseEntity<?> getAllCoupons(@RequestHeader(name = "token", required = false) String token){
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<List<Coupon>>(customerService.getAllCoupons(), HttpStatus.OK);
	}
}

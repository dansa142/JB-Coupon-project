package com.dansa.JBCouponProject.Rest;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dansa.JBCouponProject.Beans.Catagory;
import com.dansa.JBCouponProject.Beans.Clienttype;
import com.dansa.JBCouponProject.Beans.Company;
import com.dansa.JBCouponProject.Beans.Coupon;
import com.dansa.JBCouponProject.Service.CompanyService;
import com.dansa.JBCouponProject.exeptions.alreadyExistsExeption;
import com.dansa.JBCouponProject.exeptions.doesNotExistExeption;
import com.dansa.JBCouponProject.exeptions.invalidActionExeption;
import com.dansa.JBCouponProject.exeptions.loginFailedExeption;
import com.dansa.JBCouponProject.security.AngularLoginMannager;
import com.dansa.JBCouponProject.security.TokenManager;

@RestController
@RequestMapping("Company")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@ResponseBody
public class CompanyControler {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private AngularLoginMannager loginManager;

	@Autowired
	TokenManager tokenManager;

	@PostMapping("login/{email}/{password}")
	public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password) throws LoginException {
		HttpHeaders responseHeaders = new HttpHeaders();
		try {
			String token = loginManager.login(email, password, Clienttype.company);
			responseHeaders.set("token", token);
			responseHeaders.add("Access-Control-Expose-Headers", "Token");
			return ResponseEntity.ok().headers(responseHeaders)
					.body("\"Logged in as company" + companyService.getCompanyDetails().getName() + "\"");
		} catch (loginFailedExeption e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("add-coupon")
	public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon,
			@RequestHeader(name = "token", required = false) String token) throws alreadyExistsExeption {
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		companyService.addCoupon(coupon);
		return new ResponseEntity<Coupon>(HttpStatus.CREATED);
	}

	@PutMapping("update-coupon")
	public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon,
			@RequestHeader(name = "token", required = false) String token) throws invalidActionExeption {
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		companyService.updateCoupon(coupon);
		return new ResponseEntity<Coupon>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("delete-coupon")
	public ResponseEntity<?> deleteCoupon(@RequestBody Coupon coupon,
			@RequestHeader(name = "token", required = false) String token)
			throws doesNotExistExeption, invalidActionExeption {
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		companyService.deleteCoupon(coupon.getId());
		return new ResponseEntity<Coupon>(HttpStatus.OK);
	}

	@GetMapping("get-all-coupons")
	public ResponseEntity<?> getAllCompanyCoupons(@RequestHeader(name = "token", required = false) String token) {
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<List<Coupon>>(companyService.getAllCouponsByCompanyID(), HttpStatus.OK);
	}

	@GetMapping("get-all-coupons-by-catagory/{catagory}")
	public ResponseEntity<?> getAllCouponsByCatagory(@PathVariable String catagory,
			@RequestHeader(name = "token", required = false) String token) {		
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		Catagory catEnum = Catagory.valueOf(catagory);
		return new ResponseEntity<List<Coupon>>(companyService.getAllCompanyCouponsByCatagory(catEnum), HttpStatus.OK);
	}

	@GetMapping("get-all-coupons-by-price/{price}")
	public ResponseEntity<?> getAllCouponsEntityBymaxPrice(@PathVariable int price,
			@RequestHeader(name = "token", required = false) String token) {
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<List<Coupon>>(companyService.getAllCompanyCouponsByPrice(price), HttpStatus.OK);
	}

	@GetMapping("get-company-details")
	public ResponseEntity<?> getCompanyDetails(@RequestHeader(name = "token", required = false) String token) {
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<Company>(companyService.getCompanyDetails(), HttpStatus.OK);
	}
	
	@PutMapping("update-company")
	public ResponseEntity<?> updateCompany(@RequestBody Company company,@RequestHeader(name = "token", required = false) String token)
			throws doesNotExistExeption, invalidActionExeption {
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		companyService.updateCompany(company);
		return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
	}

}

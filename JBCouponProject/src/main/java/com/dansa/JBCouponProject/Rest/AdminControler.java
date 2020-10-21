package com.dansa.JBCouponProject.Rest;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RestController;

import com.dansa.JBCouponProject.Beans.Clienttype;
import com.dansa.JBCouponProject.Beans.Company;
import com.dansa.JBCouponProject.Beans.Coupon;
import com.dansa.JBCouponProject.Beans.Customer;
import com.dansa.JBCouponProject.Service.AdminService;
import com.dansa.JBCouponProject.exeptions.alreadyExistsExeption;
import com.dansa.JBCouponProject.exeptions.doesNotExistExeption;
import com.dansa.JBCouponProject.exeptions.invalidActionExeption;
import com.dansa.JBCouponProject.exeptions.loginFailedExeption;
import com.dansa.JBCouponProject.security.AngularLoginMannager;
import com.dansa.JBCouponProject.security.TokenManager;

@RestController
@RequestMapping("Admin")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AdminControler {

	@Autowired
	AdminService adminService;

	@Autowired
	AngularLoginMannager loginManager;

	@Autowired
	TokenManager tokenManager;

	@PostMapping("login/{email}/{password}")
	public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password) throws LoginException {
		HttpHeaders responseHeaders = new HttpHeaders();
		try {
			String token = loginManager.login(email, password, Clienttype.administor);
			responseHeaders.set("token", token);
			responseHeaders.add("Access-Control-Expose-Headers", "Token");
			return ResponseEntity.ok().headers(responseHeaders).body("\"looged in as admin\"");

		} catch (loginFailedExeption e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	

	@PostMapping("add-company")
	public ResponseEntity<?> addCompany(@RequestBody Company company,@RequestHeader(name = "token", required = false) String token) throws alreadyExistsExeption {
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		adminService.addCompany(company);
		return new ResponseEntity<Company>(HttpStatus.CREATED);
	}

	@PutMapping("update-company")
	public ResponseEntity<?> updateCompany(@RequestBody Company company,@RequestHeader(name = "token", required = false) String token)
			throws doesNotExistExeption, invalidActionExeption {
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		adminService.updateCompany(company);
		return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("delete-company")
	public ResponseEntity<?> deleteCompany(@RequestBody Company company,@RequestHeader(name = "token", required = false) String token) throws doesNotExistExeption {
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		adminService.deleteCompany(company);
		return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("get-all-companies")
	public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "token", required = false) String token) {
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<List<Company>>(adminService.getAllCompanies(), HttpStatus.OK);

	}

	@GetMapping("get-one-company/{companyId}")
	public ResponseEntity<?> getOneCompany(@PathVariable int companyId,@RequestHeader(name = "token", required = false) String token) throws doesNotExistExeption {
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<Company>(adminService.getOneCompanyByID(companyId), HttpStatus.OK);
	}

	@PostMapping("add-customer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer, @RequestHeader(name = "token", required = false) String token) throws alreadyExistsExeption {
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		adminService.addCustomer(customer);
		return new ResponseEntity<Customer>(HttpStatus.CREATED);
	}

	@PutMapping("update-customer")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @RequestHeader(name = "token", required = false) String token)
			throws doesNotExistExeption, invalidActionExeption {
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		adminService.updateCustomer(customer);
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("delete-customer")
	public ResponseEntity<?> deleteCustomer(@RequestBody Customer customer, @RequestHeader(name = "token", required = false) String token) throws doesNotExistExeption {
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		adminService.deleteCustomer(customer);
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("get-all-customers")
	public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "token", required = false) String token) {
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<List<Customer>>(adminService.getAllCustomers(), HttpStatus.OK);
	}

	@GetMapping("get-one-customer/{customerId}")
	public ResponseEntity<?> getOneCustomer(@PathVariable int customerId, @RequestHeader(name = "token", required = false) String token) throws doesNotExistExeption {
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<Customer>(adminService.getOneCustomer(customerId), HttpStatus.OK);
	}

	@DeleteMapping("delete-coupon")
	public ResponseEntity<?> deleteCoupon(@RequestBody Coupon coupon, @RequestHeader(name = "token", required = false) String token) {
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		adminService.deleteCoupon(coupon);
		return new ResponseEntity<Coupon>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("get-all-coupons")
	public ResponseEntity<?> getAllCoupons(@RequestHeader(name = "token", required = false) String token) {
		if (!tokenManager.doesTokenExist(token))
			return new ResponseEntity<>("\"this is not possible\"", HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<List<Coupon>>(adminService.getAllCoupons(), HttpStatus.OK);
	}
}

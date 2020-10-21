package com.dansa.JBCouponProject.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dansa.JBCouponProject.Beans.Company;
import com.dansa.JBCouponProject.Beans.Customer;
import com.dansa.JBCouponProject.Service.AdminService;
import com.dansa.JBCouponProject.exeptions.alreadyExistsExeption;

@RestController
@RequestMapping("register")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class RegisterController {

	@Autowired
	private AdminService adminService;

	@PostMapping("company")
	public ResponseEntity<?> addCompany(@RequestBody Company company) throws alreadyExistsExeption {
		adminService.addCompany(company);
		return new ResponseEntity<Company>(HttpStatus.CREATED);
	}

	@PostMapping("customer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) throws alreadyExistsExeption {
		adminService.addCustomer(customer);
		return new ResponseEntity<Customer>(HttpStatus.CREATED);
	}

}

package com.dansa.JBCouponProject.Service;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.dansa.JBCouponProject.Beans.Company;
import com.dansa.JBCouponProject.Beans.Coupon;
import com.dansa.JBCouponProject.Beans.Customer;
import com.dansa.JBCouponProject.exeptions.alreadyExistsExeption;
import com.dansa.JBCouponProject.exeptions.doesNotExistExeption;
import com.dansa.JBCouponProject.exeptions.invalidActionExeption;

@Service
@Lazy
public class AdminService extends ClientService {

	@Override
	public boolean login(String email, String password) {		
		if (email.equals("admin@admin.com") && password.equals("admin")) {			
			return true;
		}
		return false;
	}

	public void addCompany(Company company) throws alreadyExistsExeption {
		if (companyRepo.findByEmailAndPassword(company.getEmail(), company.getPassword()) != null) {
			throw new alreadyExistsExeption("company");
		}
		if (companyRepo.findByName(company.getName()) != null) {
			throw new alreadyExistsExeption("name");
		}
		companyRepo.save(company);
		// adding ID to Company after adding to DB
		company.setId(companyRepo.findByEmailAndPassword(company.getEmail(), company.getPassword()).getId());
	}

	public void updateCompany(Company company) throws doesNotExistExeption, invalidActionExeption {
		if (companyRepo.findById(company.getId()) == null) {
			throw new doesNotExistExeption("company");
		}
		if (!company.getName().equals(companyRepo.getOne(company.getId()).getName())) {

			// Changing name back to original
			company.setName(companyRepo.getOne(company.getId()).getName());
			throw new invalidActionExeption("Sorry, you can`t change your company name");

		}
		companyRepo.saveAndFlush(company);
	}

	public void deleteCompany(Company company) throws doesNotExistExeption {
		if (companyRepo.findById(company.getId()) == null) {
			throw new doesNotExistExeption("company");
		}
		List<Coupon> coupons = couponRepo.findByCompanyId(company.getId());
		List<Customer> customers = customerRepo.findAll();
		for (Customer customer : customers) {
			List<Coupon> cusCoupons = customer.getCoupons();
			for (int i = 0; i < cusCoupons.size(); i++) {
				if (cusCoupons.get(i).getCompanyId() == company.getId()) {
					cusCoupons.remove(i);
					i--;
				}
			}
			customerRepo.saveAndFlush(customer);
		}
		for (Coupon coupon : coupons) {
			couponRepo.delete(coupon);
		}
		companyRepo.delete(company);
		System.out.println("company " + company.getName() + " was deleted successfully");
	}

	public Company getOneCompanyByID(int id) throws doesNotExistExeption {
		if (companyRepo.findById(id) == null) {
			throw new doesNotExistExeption("company");
		}
		return companyRepo.getOne(id);
	}

	public List<Company> getAllCompanies() {
		return companyRepo.findAll();
	}

	public void addCustomer(Customer customer) throws alreadyExistsExeption {
		if (customerRepo.findByEmailAndPassword(customer.getEmail(), customer.getPassword()) != null) {
			throw new alreadyExistsExeption("customer");
		}
		customerRepo.save(customer);
		// adding ID to Company after adding to DB
		customer.setId(customerRepo.findByEmailAndPassword(customer.getEmail(), customer.getPassword()).getId());
	}

	public void updateCustomer(Customer customer) throws doesNotExistExeption, invalidActionExeption {
		if (customerRepo.findById(customer.getId()) == null) {
			throw new doesNotExistExeption("customer");
		} 
		customerRepo.saveAndFlush(customer);
	}

	public void deleteCustomer(Customer customer) throws doesNotExistExeption {
		if (customerRepo.findByEmailAndPassword(customer.getEmail(), customer.getPassword()) == null) {
			throw new doesNotExistExeption("customer");
		}
		customerRepo.delete(customer);
	}

	public Customer getOneCustomer(int id) throws doesNotExistExeption {
		if (customerRepo.getOne(id) == null) {
			throw new doesNotExistExeption("customer");
		}
		return customerRepo.getOne(id);
	}

	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	public void deleteCoupon(Coupon coupon) {
		couponRepo.delete(coupon);
	}

	public List<Coupon> getAllCoupons() {
		return couponRepo.findAll();
	}

}

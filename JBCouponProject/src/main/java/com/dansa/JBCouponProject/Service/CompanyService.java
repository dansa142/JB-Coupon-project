package com.dansa.JBCouponProject.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dansa.JBCouponProject.Beans.Catagory;
import com.dansa.JBCouponProject.Beans.Company;
import com.dansa.JBCouponProject.Beans.Coupon;
import com.dansa.JBCouponProject.Beans.Customer;
import com.dansa.JBCouponProject.exeptions.alreadyExistsExeption;
import com.dansa.JBCouponProject.exeptions.doesNotExistExeption;
import com.dansa.JBCouponProject.exeptions.invalidActionExeption;
import com.dansa.JBCouponProject.exeptions.loginFailedExeption;

@Service
public class CompanyService extends ClientService {

	

	private int companyId;

	public boolean login(String email, String password) throws loginFailedExeption {
		if (companyRepo.findByEmailAndPassword(email, password) == null) {
			throw new loginFailedExeption();
		}
		this.companyId = companyRepo.findByEmailAndPassword(email, password).getId();
		return true;
	}

	public void addCoupon(Coupon coupon) throws alreadyExistsExeption {
		List<Coupon> coupons = getAllCouponsByCompanyID();
		Company company = companyRepo.getOne(companyId);
		if (coupons != null) {
			for (Coupon coupon2 : coupons) {
				if (coupon.getTitle().equals(coupon2.getTitle())) {
					throw new alreadyExistsExeption("title");
				}
			}
		}
		coupon.setCompanyId(companyId);
		coupons.add(coupon);
		company.setCoupons(coupons);
		companyRepo.saveAndFlush(company);
		// giving coupon ID after injecting into DB
		coupon.setId(couponRepo.findByCompanyIdAndTitle(coupon.getCompanyId(), coupon.getTitle()).getId());

	}

	public void updateCoupon(Coupon coupon) throws invalidActionExeption {
		if (coupon.getCompanyId() != companyId) {
			coupon.setAmount(couponRepo.getOne(coupon.getId()).getAmount());
			coupon.setCatagory(couponRepo.getOne(coupon.getId()).getCatagory());
			coupon.setDescription(couponRepo.getOne(coupon.getId()).getDescription());
			coupon.setEndDate(couponRepo.getOne(coupon.getId()).getEndDate());
			coupon.setImage(couponRepo.getOne(coupon.getId()).getImage());
			coupon.setPrice(couponRepo.getOne(coupon.getId()).getPrice());
			coupon.setStartDate(couponRepo.getOne(coupon.getId()).getStartDate());
			coupon.setTitle(couponRepo.getOne(coupon.getId()).getTitle());
			throw new invalidActionExeption("You can only change your own company`s coupons.");

		}
		couponRepo.saveAndFlush(coupon);

	}

	public void deleteCoupon(int couponId) throws doesNotExistExeption, invalidActionExeption {
		List<Coupon> coupons = couponRepo.findAllById(couponId); 
		List<Customer> customers = customerRepo.findAll(); 
		if (coupons.isEmpty()) {
			throw new doesNotExistExeption("coupon");
		} else if (couponRepo.getOne(couponId).getCompanyId() != companyId) {
			throw new invalidActionExeption("you can only delete your own coupons.");
		} else {
			System.out.println("coupon " + couponRepo.getOne(couponId).getTitle() + " was deleted succefully");
			for (Customer customer : customers) {
				List<Coupon> cusCoupons = customer.getCoupons(); 
				for (int i = 0; i < cusCoupons.size(); i++) {
					if (cusCoupons.get(i).getId() == couponId) {
						cusCoupons.remove(i); 
					}
				}
				customer.setCoupons(cusCoupons);
				customerRepo.saveAndFlush(customer); 
			}
			couponRepo.deleteById(couponId);
			Company company = companyRepo.getOne(companyId);
			company.setCoupons(getAllCouponsByCompanyID());
			
			
			
		}
	}

	public List<Coupon> getAllCouponsByCompanyID() {
		return couponRepo.findByCompanyId(companyId);
	}

	public List<Coupon> getAllCompanyCouponsByCatagory(Catagory catagory) {
		List<Coupon> coupons = couponRepo.findByCompanyId(companyId);
		List<Coupon> coupons2 = new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			if (coupon.getCatagory() == catagory) {
				coupons2.add(coupon);
			}
		}
		return coupons2;
	}

	public List<Coupon> getAllCompanyCouponsByPrice(int price) {
		List<Coupon> coupons = couponRepo.findByCompanyId(companyId);
		List<Coupon> coupons2 = new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			if (coupon.getPrice() <= price) {
				coupons2.add(coupon);
			}
		}
		return coupons2;
	}

	public Company getCompanyDetails() {
		Company comp = companyRepo.getOne(this.companyId);
		comp.setCoupons(getAllCouponsByCompanyID());
		return comp;
	}
	
	public Company getByEmailAndPassword(String email, String password) {
		Company comp = companyRepo.findByEmailAndPassword(email, password);
		return comp; 
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
}

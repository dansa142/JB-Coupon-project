package com.dansa.JBCouponProject.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dansa.JBCouponProject.Beans.Catagory;
import com.dansa.JBCouponProject.Beans.Coupon;
import com.dansa.JBCouponProject.Beans.Customer;
import com.dansa.JBCouponProject.exeptions.doesNotExistExeption;
import com.dansa.JBCouponProject.exeptions.invalidActionExeption;
import com.dansa.JBCouponProject.exeptions.loginFailedExeption;

@Service
public class CustomerService extends ClientService{

	

	private int customerId;

	public boolean login(String email, String password) throws loginFailedExeption {
		if (customerRepo.findByEmailAndPassword(email, password) == null) {
			throw new loginFailedExeption();
		}
		this.customerId = customerRepo.findByEmailAndPassword(email, password).getId();
		return true;
	}

	public void purchaseCoupon(Coupon coupon) throws doesNotExistExeption, invalidActionExeption {
		if (couponRepo.getOne(coupon.getId()) == null) {
			throw new doesNotExistExeption("coupon");
		}
		Customer customer = customerRepo.getOne(customerId);
		List<Coupon> coupons = customer.getCoupons();
		for (Coupon coupon2 : coupons) {
			if (coupon.getId() == coupon2.getId()) {
				throw new invalidActionExeption("sorry, you have already purchased this coupon");
			}
		}
		if (coupon.getAmount() == 0) {
			throw new invalidActionExeption("sorry, this coupon is out of stock");
		}

		if (coupon.getEndDate().before(new Date())) {
			throw new invalidActionExeption("sorry, this coupon is expired");
		}
		coupons.add(coupon);
		customer.setCoupons(coupons);
		customerRepo.saveAndFlush(customer);
		coupon.setAmount((couponRepo.getOne(coupon.getId()).getAmount())-1);
		couponRepo.saveAndFlush(coupon);
		System.out.println("coupon " + coupon.getId() + " was purchased succesfully");

	}
	
	public List<Coupon> getAllCustomerCoupons(){
		return customerRepo.getOne(customerId).getCoupons(); 
	}
	
	public List<Coupon> getCouponsByCatagory(Catagory catagory){
		List<Coupon> coupons = getAllCoupons(); 
		List<Coupon> coupons2 = new ArrayList<Coupon>(); 
		for (Coupon coupon : coupons) {
			if (coupon.getCatagory() == catagory) {
				coupons2.add(coupon); 
			}
		}
		return coupons2; 
	}
	
	public List<Coupon> getCustomerCouponsByCatagory(Catagory catagory){
		List<Coupon> coupons = getAllCustomerCoupons(); 
		List<Coupon> coupons2 = new ArrayList<Coupon>(); 
		for (Coupon coupon : coupons) {
			if (coupon.getCatagory() == catagory) {
				coupons2.add(coupon); 
			}
		}
		return coupons2; 
	}
	
	public List<Coupon> getCouponsByPrice(double price){
		List<Coupon> coupons = getAllCustomerCoupons(); 
		List<Coupon> coupons2 = new ArrayList<Coupon>(); 
		for (Coupon coupon : coupons) {
			if (coupon.getPrice() <= price) {
				coupons2.add(coupon); 
			}
		}
		return coupons2; 
	}
	
	public Customer getCustomerDetails() {
		return customerRepo.getOne(customerId); 
	}
	
	public List<Coupon> getAllCoupons() {
		return couponRepo.findAll();
	}

	
}
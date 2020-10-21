//package com.dansa.JBCouponProject.clr;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.dansa.JBCouponProject.Beans.Catagory;
//import com.dansa.JBCouponProject.Beans.Coupon;
//import com.dansa.JBCouponProject.Service.AdminService;
//import com.dansa.JBCouponProject.Beans.Clienttype;
//import com.dansa.JBCouponProject.Service.CustomerService;
//import com.dansa.JBCouponProject.clr.LoginManager;
//import com.dansa.JBCouponProject.exeptions.doesNotExistExeption;
//import com.dansa.JBCouponProject.exeptions.invalidActionExeption;
//import com.dansa.JBCouponProject.exeptions.loginFailedExeption;
//
//@Component
//@Order(3)
//public class CustomerTest implements CommandLineRunner {
//
//	@Autowired
//	private LoginManager loginManager;
//	private CustomerService customerService = null;
//	@Autowired
//	private AdminService adminService = new AdminService(); // Testing only!
//
//	@Override
//	public void run(String... args) {
//		System.out.println("****************testing customer service****************");
//		List<Coupon> coupons = adminService.getAllCoupons();
//
//		try {
//			customerService = (CustomerService) loginManager.login("chensa@jb.com", "chensa", Clienttype.customer);
//		} catch (loginFailedExeption e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		System.out.println("---trying to purchase coupon that has expierd");
//		try {
//			customerService.purchaseCoupon(coupons.get(2));
//		} catch (doesNotExistExeption | invalidActionExeption e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println(coupons.get(2));
//
//		System.out.println("---trying to purchase coupon that is sold out:");
//		try {
//			customerService.purchaseCoupon(coupons.get(0));
//		} catch (doesNotExistExeption | invalidActionExeption e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println("---trying to purchase same coupon twice");
//
//		try {
//			customerService.purchaseCoupon(coupons.get(4));
//		} catch (doesNotExistExeption | invalidActionExeption e) {
//			System.out.println(e.getMessage());
//		}
//
//		try {
//			customerService.purchaseCoupon(coupons.get(4));
//		} catch (doesNotExistExeption | invalidActionExeption e) {
//			System.out.println(e.getMessage());
//		}
//
//		try {
//			customerService.purchaseCoupon(coupons.get(5));
//		} catch (doesNotExistExeption | invalidActionExeption e) {
//			System.out.println(e.getMessage());
//		}
//
//		try {
//			customerService.purchaseCoupon(coupons.get(1));
//		} catch (doesNotExistExeption | invalidActionExeption e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println("---trying to get all customer coupons:");
//		List<Coupon> coupons2 = customerService.getAllCoupons();
//		for (Coupon coupon : coupons2) {
//			System.out.println(coupon);
//		}
//
//		System.out.println("---trying to get all coupons under $55");
//		List<Coupon> coupons3 = customerService.getCouponsByPrice(55.0);
//		for (Coupon coupon : coupons3) {
//			System.out.println(coupon);
//		}
//
//		System.out.println("---trying to get all customer restaurant coupons");
//		List<Coupon> coupons4 = customerService.getCouponsByCatagory(Catagory.restaurant);
//		for (Coupon coupon : coupons4) {
//			System.out.println(coupon);
//		}
//
//		System.out.println("---trying to get customer details:");
//		System.out.println(customerService.getCustomerDetails());
//
//	}
//
//}

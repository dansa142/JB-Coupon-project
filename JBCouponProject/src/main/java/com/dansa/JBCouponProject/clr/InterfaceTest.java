//package com.dansa.JBCouponProject.clr;
//
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.dansa.JBCouponProject.Beans.Catagory;
//import com.dansa.JBCouponProject.Beans.Company;
//import com.dansa.JBCouponProject.Beans.Coupon;
//import com.dansa.JBCouponProject.Service.AdminService;
//import com.dansa.JBCouponProject.Beans.Clienttype;
//import com.dansa.JBCouponProject.Service.CompanyService;
//import com.dansa.JBCouponProject.Service.CustomerService;
//import com.dansa.JBCouponProject.clr.LoginManager;
//import com.dansa.JBCouponProject.exeptions.alreadyExistsExeption;
//import com.dansa.JBCouponProject.exeptions.doesNotExistExeption;
//import com.dansa.JBCouponProject.exeptions.invalidActionExeption;
//import com.dansa.JBCouponProject.exeptions.loginFailedExeption;
//import com.dansa.JBCouponProject.DailyTasks.CouponExperationDaily;
//import com.dansa.JBCouponProject.util.ConvertToSQLDate;
//
//@Component
//@Order(4)
//public class InterfaceTest implements CommandLineRunner{
//
//	@Autowired
//	private LoginManager loginManager; 
//	@Autowired
//	private AdminService adminService; 
//	@Autowired
//	private CompanyService companyService; 
//	@Autowired
//	private CustomerService customerService; 
//	@Autowired
//	private CouponExperationDaily expiered; 
//	@Override
//	public void run(String... args){
//		
//		Coupon cou11 = new Coupon(); 
//		cou11.setTitle("aaa");
//		cou11.setAmount(50);
//		cou11.setCatagory(Catagory.sports);
//		cou11.setDescription("buy 1 get 1 free");
//		cou11.setStartDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 01, 01)));
//		cou11.setEndDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 12, 31)));
//		cou11.setPrice(15.9);
//		
//		Coupon cou12 = new Coupon(); 
//		cou12.setTitle("bbb");
//		cou12.setAmount(50);
//		cou12.setCatagory(Catagory.food);
//		cou12.setDescription("buy 1 get 1 free");
//		cou12.setStartDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 01, 01)));
//		cou12.setEndDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 12, 31)));
//		cou12.setPrice(50);
//		
//		Coupon cou13 = new Coupon(); 
//		cou13.setTitle("ccc");
//		cou13.setAmount(50);
//		cou13.setCatagory(Catagory.restaurant);
//		cou13.setDescription("buy 1 get 1 free");
//		cou13.setStartDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 01, 01)));
//		cou13.setEndDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 12, 31)));
//		cou13.setPrice(10.0);
//		
//		Coupon cou14 = new Coupon(); 
//		cou14.setTitle("ddd");
//		cou14.setAmount(50);
//		cou14.setCatagory(Catagory.sports);
//		cou14.setDescription("buy 1 get 1 free");
//		cou14.setStartDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 01, 01)));
//		cou14.setEndDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 12, 31)));
//		cou14.setPrice(75.3);
//		
//		Coupon cou15 = new Coupon(); 
//		cou15.setTitle("eee");
//		cou15.setAmount(50);
//		cou15.setCatagory(Catagory.sports);
//		cou15.setDescription("buy 1 get 1 free");
//		cou15.setStartDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 01, 01)));
//		cou15.setEndDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 12, 31)));
//		cou15.setPrice(200.0);
//		
//		try {
//			companyService = (CompanyService) loginManager.login("underarmor@JB.com", "underarmor", Clienttype.company);
//		} catch (loginFailedExeption e) {
//			System.out.println(e.getMessage());
//		}		
//		
//		try {
//			companyService.addCoupon(cou11);
//		} catch (alreadyExistsExeption e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			companyService.addCoupon(cou12);
//		} catch (alreadyExistsExeption e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			companyService.addCoupon(cou13);
//		} catch (alreadyExistsExeption e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			companyService.addCoupon(cou14);
//		} catch (alreadyExistsExeption e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			companyService.addCoupon(cou15);
//		} catch (alreadyExistsExeption e) {
//			System.out.println(e.getMessage());
//		}
//		
//		System.out.println("---trying to delete other company`s coupon:");
//		
//		try {
//			companyService.deleteCoupon(3);
//		} catch (doesNotExistExeption | invalidActionExeption e) {
//			System.out.println(e.getMessage());
//		}
//		
//		try {
//			customerService = (CustomerService) loginManager.login("shape@jb.com", "shape", Clienttype.customer);
//		} catch (loginFailedExeption e) {
//			System.out.println(e.getMessage());
//		}
//		List<Coupon> coupons = adminService.getAllCoupons(); 
//		
//		try {
//			customerService.purchaseCoupon(coupons.get(1));
//		} catch (doesNotExistExeption | invalidActionExeption e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			customerService.purchaseCoupon(coupons.get(2));
//		} catch (doesNotExistExeption | invalidActionExeption e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			customerService.purchaseCoupon(coupons.get(8));
//		} catch (doesNotExistExeption | invalidActionExeption e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			customerService.purchaseCoupon(coupons.get(10));
//		} catch (doesNotExistExeption | invalidActionExeption e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			customerService.purchaseCoupon(coupons.get(9));
//		} catch (doesNotExistExeption | invalidActionExeption e) {
//			System.out.println(e.getMessage());
//		}
//		
//
//	}
//
//}

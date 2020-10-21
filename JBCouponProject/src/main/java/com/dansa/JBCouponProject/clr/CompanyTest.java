//package com.dansa.JBCouponProject.clr;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.dansa.JBCouponProject.Beans.Catagory;
//import com.dansa.JBCouponProject.Beans.Coupon;
//import com.dansa.JBCouponProject.Beans.Clienttype;
//import com.dansa.JBCouponProject.Service.CompanyService;
//import com.dansa.JBCouponProject.clr.LoginManager;
//import com.dansa.JBCouponProject.exeptions.alreadyExistsExeption;
//import com.dansa.JBCouponProject.exeptions.doesNotExistExeption;
//import com.dansa.JBCouponProject.exeptions.invalidActionExeption;
//import com.dansa.JBCouponProject.exeptions.loginFailedExeption;
//import com.dansa.JBCouponProject.util.ConvertToSQLDate;
//
//@Component
//@Order(2)
//public class CompanyTest implements CommandLineRunner {
//
//	@Autowired
//	private LoginManager loginManager;
//	private CompanyService companyService=null;
//
//
//	@Override
//	public void run(String... args) {
//
//		try {
//			companyService = (CompanyService) loginManager.login("nike@JB.com", "nike", Clienttype.company);
//		} catch (loginFailedExeption e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println("************ company service test ***************");
//		Coupon cou1 = new Coupon();
//		cou1.setTitle("1+1");
//		cou1.setAmount(10);
//		cou1.setCatagory(Catagory.sports);
//		cou1.setDescription("buy 1 get 1 free");
//		cou1.setStartDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 01, 01)));
//		cou1.setEndDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 12, 31)));
//		cou1.setPrice(15.9);
//
//		Coupon cou2 = new Coupon();
//		cou2.setTitle("1+2");
//		cou2.setAmount(10);
//		cou2.setCatagory(Catagory.food);
//		cou2.setDescription("buy 1 get 2 free");
//		cou2.setStartDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 01, 01)));
//		cou2.setEndDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 12, 31)));
//		cou2.setPrice(20.9);
//
//		Coupon cou3 = new Coupon();
//		cou3.setTitle("20% off");
//		cou3.setAmount(500);
//		cou3.setCatagory(Catagory.electricity);
//		cou3.setDescription("20% off everthing");
//		cou3.setStartDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 01, 01)));
//		cou3.setEndDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 12, 31)));
//		cou3.setPrice(150.9);
//
//		Coupon cou4 = new Coupon();
//		cou4.setTitle("buy 2 get 1 free");
//		cou4.setAmount(10);
//		cou4.setCatagory(Catagory.sports);
//		cou4.setDescription("buy 2 get 1 free");
//		cou4.setStartDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 01, 01)));
//		cou4.setEndDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 6, 31)));
//		cou4.setPrice(75.9);
//
//		Coupon cou5 = new Coupon();
//		cou5.setTitle("free sample");
//		cou5.setAmount(10);
//		cou5.setCatagory(Catagory.sports);
//		cou5.setDescription("get free sample of our new product");
//		cou5.setStartDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 07, 27)));
//		cou5.setEndDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 07, 31)));
//		cou5.setPrice(0.0);
//
//		Coupon cou6 = new Coupon();
//		cou6.setTitle("30% off");
//		cou6.setAmount(10);
//		cou6.setCatagory(Catagory.restaurant);
//		cou6.setDescription("30% off everthing");
//		cou6.setStartDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 01, 01)));
//		cou6.setEndDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 12, 31)));
//		cou6.setPrice(60.9);
//
//		Coupon cou7 = new Coupon();
//		cou7.setTitle("90% off");
//		cou7.setAmount(10);
//		cou7.setCatagory(Catagory.sports);
//		cou7.setDescription("90% off everthing");
//		cou7.setStartDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 01, 01)));
//		cou7.setEndDate(ConvertToSQLDate.convertDateToSql(new Date(2020, 12, 31)));
//		cou7.setPrice(50.9);
//
//		try {
//			companyService.addCoupon(cou1);
//		} catch (alreadyExistsExeption e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			companyService.addCoupon(cou2);
//		} catch (alreadyExistsExeption e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			companyService.addCoupon(cou3);
//		} catch (alreadyExistsExeption e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			companyService.addCoupon(cou4);
//		} catch (alreadyExistsExeption e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			companyService.addCoupon(cou5);
//		} catch (alreadyExistsExeption e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			companyService.addCoupon(cou6);
//		} catch (alreadyExistsExeption e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			companyService.addCoupon(cou7);
//		} catch (alreadyExistsExeption e) {
//			System.out.println(e.getMessage());
//		}
//
//		
//		System.out.println("---trying to delete coupon");
//		try {
//			companyService.deleteCoupon(2);
//		} catch (doesNotExistExeption | invalidActionExeption e) {
//			System.out.println(e.getMessage());
//		}
//		
//		System.out.println("---trying to get all company coupons");
//		System.out.println(companyService.getAllCouponsByCompanyID());
//		
//		System.out.println("---trying to delete coupon that doesn`t exict");
//		try {
//			companyService.deleteCoupon(50);
//		} catch (doesNotExistExeption | invalidActionExeption e) {
//			System.out.println(e.getMessage());
//		}
//		
//		System.out.println("---tring to get company coupons by catagory");
//		
//		System.out.println(companyService.getAllCompanyCouponsByCatagory(Catagory.food));
//		
//		System.out.println("---trying to get all company coupons less than $70");
//		System.out.println(companyService.getAllCompanyCouponsByPrice(70));
//		System.out.println("trying to get company details");
//		System.out.println(companyService.getCompanyDetails());
//		
//
//	}
//
//}

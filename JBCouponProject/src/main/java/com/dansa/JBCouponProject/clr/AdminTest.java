//package com.dansa.JBCouponProject.clr;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.dansa.JBCouponProject.Beans.Company;
//import com.dansa.JBCouponProject.Beans.Coupon;
//import com.dansa.JBCouponProject.Beans.Customer;
//import com.dansa.JBCouponProject.Service.AdminService;
//import com.dansa.JBCouponProject.Beans.Clienttype;
//import com.dansa.JBCouponProject.clr.LoginManager;
//import com.dansa.JBCouponProject.exeptions.alreadyExistsExeption;
//import com.dansa.JBCouponProject.exeptions.doesNotExistExeption;
//import com.dansa.JBCouponProject.exeptions.invalidActionExeption;
//import com.dansa.JBCouponProject.exeptions.loginFailedExeption;
//
//@Component
//@Order(1)
//public class AdminTest implements CommandLineRunner {
//
//	@Autowired
//	private LoginManager loginManager;
//
//	private AdminService adminService = null;
//
//	@Override
//	public void run(String... args) {
//		try {
//			adminService = (AdminService) loginManager.login("admin@admin.com", "admin", Clienttype.administor);
//		} catch (loginFailedExeption e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		System.out.println("**************** administor service test*********************");
//		Company com1 = new Company();
//		com1.setName("adidas");
//		com1.setEmail("adidas@JB.com");
//		com1.setPassword("adidas");
//		com1.setCoupons(new ArrayList<Coupon>());
//
//		Company com2 = new Company();
//		com2.setName("puma");
//		com2.setEmail("puma@JB.com");
//		com2.setPassword("puma");
//		com2.setCoupons(new ArrayList<Coupon>());
//
//		Company com3 = new Company();
//		com3.setName("nike");
//		com3.setEmail("nike@JB.com");
//		com3.setPassword("nike");
//		com3.setCoupons(new ArrayList<Coupon>());
//
//		Company com4 = new Company();
//		com4.setName("under armor");
//		com4.setEmail("underarmor@JB.com");
//		com4.setPassword("underarmor");
//		com4.setCoupons(new ArrayList<Coupon>());
//
//		Company com5 = new Company();
//		com5.setName("fila");
//		com5.setEmail("fila@JB.com");
//		com5.setPassword("fila");
//		com5.setCoupons(new ArrayList<Coupon>());
//
//		Company com6 = new Company();
//		com6.setName("fila");
//		com6.setEmail("newballance@JB.com");
//		com6.setPassword("new ballance");
//		com6.setCoupons(new ArrayList<Coupon>());
//
//		try {
//			adminService.addCompany(com1);
//		} catch (alreadyExistsExeption e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			adminService.addCompany(com2);
//		} catch (alreadyExistsExeption e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			adminService.addCompany(com3);
//		} catch (alreadyExistsExeption e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			adminService.addCompany(com4);
//		} catch (alreadyExistsExeption e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			adminService.addCompany(com5);
//		} catch (alreadyExistsExeption e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println("----trying to add company with same name:");
//		try {
//			adminService.addCompany(com6);
//		} catch (alreadyExistsExeption e) {
//			System.out.println(e.getMessage());
//		}
//
//		com1.setEmail("seconedmail@jb.com");
//		try {
//			adminService.updateCompany(com1);
//		} catch (doesNotExistExeption | invalidActionExeption e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println("---trying to change company name:");
//		com1.setName("aaa");
//		try {
//			adminService.updateCompany(com1);
//		} catch (doesNotExistExeption | invalidActionExeption e) {
//			System.out.println(e.getMessage());
//		}
//
//		try {
//			adminService.deleteCompany(com1);
//		} catch (doesNotExistExeption e) {
//			System.out.println(e.getMessage());
//		}
//
//		try {
//			adminService.addCompany(com1);
//		} catch (alreadyExistsExeption e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println("---get one company---");
//		try {
//			System.out.println(adminService.getOneCompanyByID(2));
//		} catch (doesNotExistExeption e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println("--- get all companies---");
//		List<Company> companies = adminService.getAllCompanies();
//		for (Company company : companies) {
//			System.out.println(company);
//		}
//
//		Customer cus1 = new Customer();
//		cus1.setFirstName("dan");
//		cus1.setLastName("sapir");
//		cus1.setEmail("dansa@jb.com");
//		cus1.setPassword("dansa");
//
//		Customer cus2 = new Customer();
//		cus2.setFirstName("chen");
//		cus2.setLastName("sapir");
//		cus2.setEmail("chensa@jb.com");
//		cus2.setPassword("chensa");
//
//		Customer cus3 = new Customer();
//		cus3.setFirstName("yossi");
//		cus3.setLastName("shany");
//		cus3.setEmail("yossh@jb.com");
//		cus3.setPassword("yossh");
//
//		Customer cus4 = new Customer();
//		cus4.setFirstName("kobi");
//		cus4.setLastName("shasha");
//		cus4.setEmail("kosha@jb.com");
//		cus4.setPassword("kosha");
//
//		Customer cus5 = new Customer();
//		cus5.setFirstName("sharon");
//		cus5.setLastName("peretz");
//		cus5.setEmail("shape@jb.com");
//		cus5.setPassword("shape");
//
//		Customer cus6 = new Customer();
//		cus6.setFirstName("omer");
//		cus6.setLastName("levi");
//		cus6.setEmail("omle@jb.com");
//		cus6.setPassword("omle");
//
//		Customer cus7 = new Customer();
//		cus7.setFirstName("eliran");
//		cus7.setLastName("cohen");
//		cus7.setEmail("elico@jb.com");
//		cus7.setPassword("elico");
//
//		try {
//			adminService.addCustomer(cus1);
//		} catch (alreadyExistsExeption e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			adminService.addCustomer(cus2);
//		} catch (alreadyExistsExeption e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			adminService.addCustomer(cus3);
//		} catch (alreadyExistsExeption e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			adminService.addCustomer(cus4);
//		} catch (alreadyExistsExeption e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			adminService.addCustomer(cus5);
//		} catch (alreadyExistsExeption e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			adminService.addCustomer(cus6);
//		} catch (alreadyExistsExeption e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			adminService.addCustomer(cus7);
//		} catch (alreadyExistsExeption e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		System.out.println("---updating customer with diffrent ID---");
//		cus1.setId(15);
//		try {
//			adminService.updateCustomer(cus1);
//		} catch (doesNotExistExeption | invalidActionExeption e) {
//			System.out.println(e.getMessage());
//		}
//
//		try {
//			adminService.deleteCustomer(cus1);
//		} catch (doesNotExistExeption e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println("--- get one customer---");
//		try {
//			System.out.println(adminService.getOneCustomer(2));
//		} catch (doesNotExistExeption e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println("getting all customers");
//		List<Customer> customers = adminService.getAllCustomers();
//		for (Customer customer : customers) {
//			System.out.println(customer);
//
//		}
//
//	}
//
//}

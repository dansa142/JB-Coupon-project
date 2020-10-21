package com.dansa.JBCouponProject.DailyTasks;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dansa.JBCouponProject.Beans.Coupon;
import com.dansa.JBCouponProject.Service.AdminService;
import com.dansa.JBCouponProject.exeptions.doesNotExistExeption;
import com.dansa.JBCouponProject.exeptions.invalidActionExeption;

@Component
public class CouponExperationDaily extends Thread {

	public static final int day = 60 * 60 * 24 * 1000;

	@Autowired
	private AdminService adminService;

	@Scheduled(fixedRate = day)
	public void run() {

		List<Coupon> coupons = adminService.getAllCoupons();
		System.err.println("deleting expired coupons");

		for (Coupon coupon : coupons) {
			if (coupon.getEndDate().before(new Date())) {
				adminService.deleteCoupon(coupon);
				try {
					adminService.updateCompany(adminService.getOneCompanyByID(coupon.getCompanyId()));
				} catch (doesNotExistExeption | invalidActionExeption e) {
					System.out.println(e.getMessage());
				}
				System.err.println("coupon " + coupon.getTitle() + " has expired and has been deletd");
			}
		}
		System.err.println("expierd coupons deleted");
	}

}

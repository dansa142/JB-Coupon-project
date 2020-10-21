package com.dansa.JBCouponProject.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dansa.JBCouponProject.Beans.Company;
import com.dansa.JBCouponProject.Beans.Coupon;

@Repository
public interface CouponReposetory extends JpaRepository<Coupon, Integer>{
	
	Coupon findByCompanyIdAndTitle(int companyID, String title); 
	
	Coupon findByTitleAndDescription (String title, String description); 
	
	List<Coupon> findByCompanyId(int companyId);
	
	List<Coupon> findByPriceLessThan (Double price);

	List<Coupon> findAllById(int couponId);

	Company findByCompanyIdAndId(int companyId, int id);
}

package com.dansa.JBCouponProject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dansa.JBCouponProject.Repo.CompanyReposetory;
import com.dansa.JBCouponProject.Repo.CouponReposetory;
import com.dansa.JBCouponProject.Repo.CustomerReposetory;
import com.dansa.JBCouponProject.exeptions.loginFailedExeption;

@Service
public abstract class ClientService {

	@Autowired
	protected CompanyReposetory companyRepo;
	@Autowired
	protected CustomerReposetory customerRepo;
	@Autowired
	protected CouponReposetory couponRepo;
	

	abstract boolean login(String email, String password) throws loginFailedExeption;
} 


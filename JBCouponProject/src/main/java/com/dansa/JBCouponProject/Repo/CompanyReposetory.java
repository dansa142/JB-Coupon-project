package com.dansa.JBCouponProject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dansa.JBCouponProject.Beans.Company;

@Repository
public interface CompanyReposetory extends JpaRepository<Company, Integer> {

	Company findByEmailAndPassword(String email, String password);
	
	Company findByName(String name); 
}

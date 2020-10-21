package com.dansa.JBCouponProject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dansa.JBCouponProject.Beans.Customer;

@Repository
public interface CustomerReposetory extends JpaRepository<Customer, Integer>{

	Customer findByEmailAndPassword(String email, String password);
	
	Customer findById (int id);
}

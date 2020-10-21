package com.dansa.JBCouponProject.exeptions;

public class loginFailedExeption extends Exception {

	public loginFailedExeption() {
		super("email and/or password are incorrect, please try again"); 
	}
}

package com.dansa.JBCouponProject.exeptions;

public class doesNotExistExeption extends Exception {

	public doesNotExistExeption(String object) {
		super("Sorry, this " + object + " does not exist"); 
	}
}

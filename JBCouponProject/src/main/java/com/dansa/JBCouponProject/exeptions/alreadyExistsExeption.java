package com.dansa.JBCouponProject.exeptions;

public class alreadyExistsExeption extends Exception {
	
	public alreadyExistsExeption(String object) {
		super("Sorry, this " + object + " already exists. Please try a difrent " + object +".");
	}
}

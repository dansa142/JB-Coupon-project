package com.dansa.JBCouponProject.util;

import java.sql.Date;

public class ConvertToSQLDate {

	public static java.sql.Date convertDateToSql(java.util.Date date) {
		java.sql.Date sqlDate = new Date(date.getYear() - 1900, date.getMonth() - 1, date.getDate() + 1);

		return sqlDate;
	}
}

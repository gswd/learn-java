package com.hm707.beans.beanutils;

import java.util.Date;

import lombok.Data;

@Data
public class Employee {
	private String firstName;
	private String lastName;
	private Date hireDate;
	private boolean isManager;


	public String getFullName() {
		return firstName + " " + lastName;
	}


}
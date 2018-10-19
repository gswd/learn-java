package com.hm707.beans.beanutils;

import java.util.List;

public class IndexedBean {
	private List<Employee> employeeList;
	private Integer[] intArr;

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public Integer[] getIntArr() {
		return intArr;
	}

	public void setIntArr(Integer[] intArr) {
		this.intArr = intArr;
	}
}
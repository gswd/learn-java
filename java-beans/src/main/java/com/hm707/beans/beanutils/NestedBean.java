package com.hm707.beans.beanutils;

import java.util.List;
import java.util.Map;

public class NestedBean {

	private List<Employee> listProperty;
	private Map<String, Employee> mapProperty;

	public List<Employee> getListProperty() {
		return listProperty;
	}

	public void setListProperty(List<Employee> listProperty) {
		this.listProperty = listProperty;
	}

	public Map<String, Employee> getMapProperty() {
		return mapProperty;
	}

	public void setMapProperty(Map<String, Employee> mapProperty) {
		this.mapProperty = mapProperty;
	}
}
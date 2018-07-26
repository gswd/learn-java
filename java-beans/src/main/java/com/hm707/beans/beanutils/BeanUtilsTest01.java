package com.hm707.beans.beanutils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

public class BeanUtilsTest01 {
	public static void main(String[] args) throws	Exception {
		//t1();
		//t2();
		//t3();
		t4();
	}

	/**
	 * 下面两个方法可以读写基础数据类型 以及String 类型
	 * PropertyUtils.getSimpleProperty(Object, String)
	 * PropertyUtils.setSimpleProperty(Object, String, Object)
	 *
	 * 下面方法可以读取引用类型的嵌套属性
	 * propertyUtils.getProperty(Object, String)
	 */
	private static void t1() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		Employee employee = new Employee();
		employee.setFirstName("firstName");
		employee.setLastName("lastName");
		employee.setHireDate(new Date());

		String fullName = BeanUtils.getSimpleProperty(employee, "fullName");
		System.out.println("employee fullName [modify before] -> " + fullName);

		PropertyUtils.setSimpleProperty(employee, "firstName", "Fn1");
		PropertyUtils.setSimpleProperty(employee, "lastName", "Ln1");

		System.out.println("employee fullName [modify after] -> " + employee.getFirstName());

		Object o = PropertyUtils.getProperty(employee, "hireDate.time");
		System.out.println("can read nested properties -> " + o);
	}

	/**
	 * 访问索引类型的Bean属性.
	 * 可索引的属性，如ArrayList, 数组等，可以通过下标索引来访问Bean属性的值, 同理可设置value；
	 *
	 * PropertyUtils.getIndexedProperty(Object, String)
	 * PropertyUtils.getIndexedProperty(Object, String, int)
	 * PropertyUtils.setIndexedProperty(Object, String, Object)
	 * PropertyUtils.setIndexedProperty(Object, String, int, Object)
	 */
	private static void t2() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		// 初始工作
		IndexedBean indexedBean = new IndexedBean();
		List<Employee> employeeList = new ArrayList<Employee>();
		Employee e1 = new Employee();
		e1.setFirstName("firstName");
		e1.setLastName("lastName");
		Employee e2 = new Employee();
		e2.setFirstName("Fn1");
		e2.setLastName("Ln1");

		employeeList.add(e1);
		employeeList.add(e2);
		indexedBean.setEmployeeList(employeeList);
		indexedBean.setIntArr(new Integer[]{ 0, 1, 2 });

		// api测试
		Employee Employee1 = (Employee)PropertyUtils.getIndexedProperty(indexedBean, "employeeList[0]");
		System.out.println("employee1 full name is [firstName lastName] -> " + Employee1.getFullName());
		Employee Employee2 = (Employee)PropertyUtils.getIndexedProperty(indexedBean, "employeeList", 1);
		System.out.println("employee1 full name is [Fn1 Ln1] -> " + Employee2.getFullName());

	}

	/**
	 * 访问Map映射类型的Bean属性
	 * 常见的HashMap，TreeMap等，可以通过key来访问Bean属性值，同理可设置value
	 *
	 * PropertyUtils.getMappedProperty(Object, String)
	 * PropertyUtils.getMappedProperty(Object, String, String)
	 * PropertyUtils.setMappedProperty(Object, String, Object)
	 * PropertyUtils.setMappedProperty(Object, String, String, Object)
	 */
	private static void t3() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		MappedBean employee = new MappedBean();
		Map<String, Object> map = new HashMap<>();
		PropertyUtils.setSimpleProperty(employee, "mapProperty", map);

		PropertyUtils.setMappedProperty(employee, "mapProperty", "testKey1", "testValue1");
		PropertyUtils.setMappedProperty(employee, "mapProperty(testKey2)", "testValue2");

		System.out.println(BeanUtils.getMappedProperty(employee, "mapProperty", "testKey1"));
		System.out.println(employee.getMapProperty().get("testKey2"));


		System.out.println(BeanUtils.getMappedProperty(employee, "mapProperty(testKey1)"));
	}

	/**
	 * 访问嵌套类型的Bean属性
	 *
	 * PropertyUtils.getNestedProperty(Object, String)
	 * PropertyUtils.setNestedProperty(Object, String, Object)
	 */
	private static void t4() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		Employee e = new Employee();
		e.setLastName("lastName");

		NestedBean nestedBean = new NestedBean();

		List<Employee> list = new ArrayList<>();
		list.add(e);

		Map<String, Employee> map = new HashMap<>();
		map.put("testKey", e);

		nestedBean.setListProperty(list);
		nestedBean.setMapProperty(map);

		String lastName = (String)PropertyUtils.getNestedProperty(nestedBean,"mapProperty(testKey).lastName");
		System.out.println(lastName);
		String lastName2 = (String)PropertyUtils.getNestedProperty(nestedBean,"listProperty[0].lastName");
		System.out.println(lastName2);
	}
}

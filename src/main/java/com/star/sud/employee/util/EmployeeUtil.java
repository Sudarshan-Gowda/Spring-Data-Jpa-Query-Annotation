package com.star.sud.employee.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.star.sud.employee.dto.Employee;

public class EmployeeUtil {

	private static Map<Integer, Employee> employeeMap = new HashMap<>();
	private static int counter = 1;

	static {
		employeeMap.put(++counter, new Employee(1, "John", new Date(), "sudarshan@gmail.com", 1000.00));
	}

	public static void setEmployees(Employee employee) {
		employeeMap.put(counter, employee);
		counter++;
	}

	public static Employee getEmployeeById(int empId) {
		return employeeMap.get(empId);
	}

	public static List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		for (Integer key : employeeMap.keySet()) {
			Employee employee = employeeMap.get(key);
			employees.add(employee);
		}

		return employees;
	}

	public static void save(Employee employee) {
		employeeMap.put(++counter, employee);
	}

}

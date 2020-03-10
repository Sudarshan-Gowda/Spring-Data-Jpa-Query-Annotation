/**
 * 
 */
package com.star.sud.employee.dto;

import java.util.List;

/**
 * @author sudarshan
 *
 */
public class EmployeeRequest {

	private List<Employee> employees;

	/**
	 * @return the employees
	 */
	public List<Employee> getEmployees() {
		return employees;
	}

	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}

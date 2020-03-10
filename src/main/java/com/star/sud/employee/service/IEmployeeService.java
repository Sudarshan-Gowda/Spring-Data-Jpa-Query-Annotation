/**
 * 
 */
package com.star.sud.employee.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.star.sud.employee.dto.Employee;
import com.star.sud.employee.dto.EmployeeRequest;
import com.star.sud.employee.dto.EmployeeResponse;

/**
 * @author sudarshan
 *
 */
public interface IEmployeeService {

	/**
	 * @param employee
	 * @return
	 * @throws Exception
	 */
	ResponseEntity<Object> createEmployee(Employee employee);

	/**
	 * @param empId
	 * @return
	 */
	Employee getEmployeeById(int empId);

	/**
	 * @return
	 */
	List<Employee> retrieveAllEmployees();

	/**
	 * @param employee
	 * @return
	 */
	EmployeeResponse getEmployeesByDob(Employee employee);

	/**
	 * @param employee
	 * @return
	 */
	EmployeeResponse validateEmailAndName(Employee employee);

	/**
	 * @param employee
	 * @return
	 */
	EmployeeResponse validateEmail(Employee employee);

	/**
	 * @param empId
	 */
	void deleteEmployee(Integer empId);

	/**
	 * @param request
	 * @return
	 */
	EmployeeResponse getEmployeesByEmailIds(EmployeeRequest request);

	/**
	 * @return
	 */
	EmployeeResponse findAllByNameAsc();

	/**
	 * @return
	 */
	EmployeeResponse findAllByLengthOfName();

	/**
	 * @return
	 */
	EmployeeResponse findAllByNameDesc();

	/**
	 * @param request
	 * @return
	 */
	EmployeeResponse findEmployeesByName(Employee request);

	/**
	 * @param request
	 * @return
	 */
	EmployeeResponse getEmployeesByNames(EmployeeRequest request);

	/**
	 * @param request
	 * @return
	 */
	ResponseEntity<Object> updateEmployeeExp(Employee request);

	/**
	 * @param request
	 * @return
	 */
	ResponseEntity<Object> insertEmployee(Employee request);

}

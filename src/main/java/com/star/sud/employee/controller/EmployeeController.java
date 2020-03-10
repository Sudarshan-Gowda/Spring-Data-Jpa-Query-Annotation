package com.star.sud.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.star.sud.employee.dto.Employee;
import com.star.sud.employee.dto.EmployeeRequest;
import com.star.sud.employee.dto.EmployeeResponse;
import com.star.sud.employee.service.IEmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	IEmployeeService service;

	@GetMapping(value = "/employees")
	public List<Employee> retrieveAllEmployees() {
		return service.retrieveAllEmployees();
	}

	@GetMapping(value = "/employees/{empId}")
	public Employee getEmployee(@PathVariable("empId") int empId) {
		return service.getEmployeeById(empId);
	}

	@PostMapping(value = "/employees")
	public @ResponseBody ResponseEntity<Object> createEmployee(@Valid @RequestBody Employee employee) {
		return service.createEmployee(employee);
	}

	@DeleteMapping(value = "/employees/{empId}")
	public void deleteEmployee(@PathVariable("empId") Integer empId) {
		service.deleteEmployee(empId);
	}

	@PostMapping(value = "/validateEmail")
	public @ResponseBody EmployeeResponse validateEmail(@RequestBody Employee employee) {
		return service.validateEmail(employee);
	}

	@PostMapping(value = "/validateEmailAndName")
	public @ResponseBody EmployeeResponse validateEmailAndName(@RequestBody Employee employee) {
		return service.validateEmailAndName(employee);
	}

	@PostMapping(value = "/employeesByDob")
	public @ResponseBody EmployeeResponse getEmployeeByDob(@RequestBody Employee employee) {
		return service.getEmployeesByDob(employee);
	}

	@PostMapping(value = "/employeesByEmailIds")
	public @ResponseBody EmployeeResponse getEmployeesByEmailIds(@RequestBody EmployeeRequest request) {
		return service.getEmployeesByEmailIds(request);
	}

	@GetMapping(value = "/findAllByNameAsc")
	public @ResponseBody EmployeeResponse getAllEmployeesByNameAsc() {
		return service.findAllByNameAsc();
	}

	@GetMapping(value = "/findAllByNameDesc")
	public @ResponseBody EmployeeResponse getAllEmployeesByNameDesc() {
		return service.findAllByNameDesc();
	}

	@GetMapping(value = "/findAllByLengthOfName")
	public @ResponseBody EmployeeResponse findAllByLengthOfName() {
		return service.findAllByLengthOfName();
	}

	@PostMapping(value = "/findAllEmployeesWithPagination")
	public @ResponseBody EmployeeResponse findEmployeesByName(@RequestBody Employee request) {
		return service.findEmployeesByName(request);
	}

	@PostMapping(value = "/employeesByNames")
	public @ResponseBody EmployeeResponse employeesByNames(@RequestBody EmployeeRequest request) {
		return service.getEmployeesByNames(request);
	}

	@PostMapping(value = "/updateEmployeeExp")
	public @ResponseBody ResponseEntity<Object> updateEmployeeExp(@RequestBody Employee request) {
		return service.updateEmployeeExp(request);
	}

	@PostMapping(value = "/insertEmployee")
	public @ResponseBody ResponseEntity<Object> insertEmployee(@RequestBody Employee request) {
		return service.insertEmployee(request);
	}

}

package com.star.sud.employee.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.star.sud.employee.dao.EmployeeRepository;
import com.star.sud.employee.dto.Employee;
import com.star.sud.employee.dto.EmployeeRequest;
import com.star.sud.employee.dto.EmployeeResponse;
import com.star.sud.employee.dto.ResponseBody;
import com.star.sud.employee.exception.EmployeeCreationException;
import com.star.sud.employee.exception.NoRecordsFoundException;
import com.star.sud.employee.exception.RequestParamException;
import com.star.sud.employee.model.TEmployee;
import com.star.sud.employee.util.DateUtil;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository repository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.star.sud.employee.service.IEmployeeService#findAllEmployees()
	 */
	@Override
	public List<Employee> retrieveAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		List<TEmployee> tEmployees = repository.findAll();
		if (null == tEmployees || tEmployees.size() <= 0)
			throw new NoRecordsFoundException("No Employee Records Found!");

		for (TEmployee tEmployee : tEmployees) {
			Employee employee = new Employee();
			BeanUtils.copyProperties(tEmployee, employee);
			employees.add(employee);
		}

		return employees;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.star.sud.employee.service.IEmployeeService#getEmployeeById(int)
	 */
	@Override
	public Employee getEmployeeById(int empId) {
		Optional<TEmployee> tEmployee = repository.findById(empId);

		if (!tEmployee.isPresent())
			throw new NoRecordsFoundException("No records found for the id - " + empId);

		Employee employee = new Employee();
		BeanUtils.copyProperties(tEmployee.get(), employee);

		return employee;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.star.sud.employee.service.IEmployeeService#createEmployee(com.star.sud.
	 * employee.dto.Employee)
	 */
	@Override
	public ResponseEntity<Object> createEmployee(Employee employee) {

		if (null == employee)
			throw new EmployeeCreationException("input param is null or empty");

		TEmployee tEmployee = null;

		tEmployee = repository.employeesByEmail(employee.getEmpEmail());
		if (tEmployee != null)
			throw new EmployeeCreationException("Email entered is already exists");

		TEmployee entity = new TEmployee();
		BeanUtils.copyProperties(employee, entity);
		tEmployee = repository.save(entity);
		if (null == tEmployee)
			throw new EmployeeCreationException("Failed to Create Employee Record");

		URI localtion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(tEmployee.getEmpId()).toUri();

		return ResponseEntity.created(localtion).build();
	}

	/**
	 * @param empId
	 * @return
	 */
	@Override
	public void deleteEmployee(Integer empId) {

		if (empId == null)
			throw new RequestParamException("request param is null or empty");

		Optional<TEmployee> tEmployee = repository.findById(empId);

		if (!tEmployee.isPresent())
			throw new NoRecordsFoundException("No records found for the id - " + empId);

		repository.delete(tEmployee.get());

	}

	/**
	 * @param employee
	 * @return
	 */
	@Override
	public EmployeeResponse validateEmail(Employee employee) {

		if (null == employee)
			throw new RequestParamException("request param is null or empty");

		if (null == employee.getEmpEmail())
			throw new RequestParamException("email id is null or empty");

		EmployeeResponse resBody = new EmployeeResponse();

		TEmployee tEmployee = repository.employeesByEmailWithParam(employee.getEmpEmail());
		if (tEmployee == null) {
			resBody.setMessage("Email id not exists");
			resBody.setStatus(HttpStatus.OK.name());
		} else {
			resBody.setMessage("Email id already exists");
			resBody.setStatus(HttpStatus.OK.name());
		}

		return resBody;
	}

	/**
	 * @param employee
	 * @return
	 */
	@Override
	public EmployeeResponse validateEmailAndName(Employee employee) {

		if (null == employee)
			throw new RequestParamException("request param is null or empty");

		if (null == employee.getEmpEmail())
			throw new RequestParamException("email id is null or empty");

		EmployeeResponse resBody = new EmployeeResponse();

		TEmployee tEmployee = repository.employeeByEmailAndNameWithParam(employee.getEmpEmail(), employee.getEmpName());
		if (tEmployee == null) {
			resBody.setMessage("No Record found for given params");
			resBody.setStatus(HttpStatus.OK.name());
		} else {
			resBody.setMessage("Record Exists");
			resBody.setStatus(HttpStatus.OK.name());
		}
		return resBody;
	}

	/**
	 * @param employee
	 * @return
	 */
	@Override
	public EmployeeResponse getEmployeesByDob(Employee employee) {

		if (null == employee)
			throw new RequestParamException("request param is null or empty");

		if (null == employee.getEmpDob())
			throw new RequestParamException("dob param is null or empty");

		EmployeeResponse resBody = new EmployeeResponse();

		Date empDob = employee.getEmpDob();

		List<TEmployee> tEmployees = repository.employeeByDob(DateUtil.getDate(empDob, Boolean.FALSE),
				DateUtil.getDate(empDob, Boolean.TRUE));
		if (tEmployees == null || tEmployees.isEmpty()) {
			resBody.setMessage("No Record found for given params");
			resBody.setStatus(HttpStatus.OK.name());
		} else {
			resBody.setMessage("Record Exists");
			resBody.setStatus(HttpStatus.OK.name());
			List<Employee> employees = new ArrayList<>();
			for (TEmployee tEmployee : tEmployees) {
				Employee empDto = new Employee();
				BeanUtils.copyProperties(tEmployee, empDto);
				employees.add(empDto);
			}

			resBody.setEmployees(employees);
		}

		return resBody;

	}

	/**
	 * @param request
	 * @return
	 */
	public EmployeeResponse getEmployeesByEmailIds(EmployeeRequest request) {

		if (null == request)
			throw new RequestParamException("request param is null or empty");

		EmployeeResponse response = new EmployeeResponse();

		Set<String> emails = new HashSet<>();
		for (Employee employee : request.getEmployees())
			emails.add(employee.getEmpEmail());

		List<Employee> employees = new ArrayList<>();
		List<TEmployee> tEmployees = repository.findEmployeesByEmails(emails);
		for (TEmployee tEmployee : tEmployees) {
			Employee employee = new Employee();
			BeanUtils.copyProperties(tEmployee, employee);
			employees.add(employee);
		}
		response.setEmployees(employees);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.star.sud.employee.service.IEmployeeService#getAllEmployeesByNameAsc()
	 */
	@Override
	public EmployeeResponse findAllByNameAsc() {
		EmployeeResponse response = new EmployeeResponse();

		try {

			List<Employee> employees = new ArrayList<>();
			List<TEmployee> tEmployees = repository.findAll(Sort.by(Direction.ASC, "empName"));
			for (TEmployee tEmployee : tEmployees) {
				Employee employee = new Employee();
				BeanUtils.copyProperties(tEmployee, employee);
				employees.add(employee);
			}

			response.setEmployees(employees);
			response.setStatus(HttpStatus.OK.name());
			response.setMessage("Success");

			return response;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public EmployeeResponse findAllByNameDesc() {
		EmployeeResponse response = new EmployeeResponse();

		try {

			List<Employee> employees = new ArrayList<>();
			List<TEmployee> tEmployees = repository.findAll(Sort.by(Direction.DESC, "empName"));
			for (TEmployee tEmployee : tEmployees) {
				Employee employee = new Employee();
				BeanUtils.copyProperties(tEmployee, employee);
				employees.add(employee);
			}

			response.setEmployees(employees);
			response.setStatus(HttpStatus.OK.name());
			response.setMessage("Success");

			return response;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public EmployeeResponse findAllByLengthOfName() {
		EmployeeResponse response = new EmployeeResponse();

		try {

			List<Employee> employees = new ArrayList<>();
			List<TEmployee> tEmployees = repository.findAllEmployees(JpaSort.unsafe("LENGTH(empName)"));
			for (TEmployee tEmployee : tEmployees) {
				Employee employee = new Employee();
				BeanUtils.copyProperties(tEmployee, employee);
				employees.add(employee);
			}

			response.setEmployees(employees);
			response.setStatus(HttpStatus.OK.name());
			response.setMessage("Success");

			return response;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.star.sud.employee.service.IEmployeeService#findEmployeesByName(com.star.
	 * sud.employee.dto.EmployeeRequest)
	 */
	@Override
	public EmployeeResponse findEmployeesByName(Employee request) {
		EmployeeResponse response = new EmployeeResponse();

		try {

			Employee employee = new Employee();
			TEmployee temployee = repository.findByEmpName(request.getEmpName());
			if (null == temployee)
				throw new NoRecordsFoundException("No record found for the name " + request.getEmpName());

			BeanUtils.copyProperties(temployee, employee);
			response.setEmployee(employee);
			response.setStatus(HttpStatus.OK.name());
			response.setMessage("Success");
			return response;

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.star.sud.employee.service.IEmployeeService#getEmployeesByNames(com.star.
	 * sud.employee.dto.EmployeeRequest)
	 */
	@Override
	public EmployeeResponse getEmployeesByNames(EmployeeRequest request) {

		if (null == request)
			throw new RequestParamException("request param is null or empty");

		EmployeeResponse response = new EmployeeResponse();

		Set<String> names = new HashSet<>();
		for (Employee employee : request.getEmployees())
			names.add(employee.getEmpName());

		List<Employee> employees = new ArrayList<>();
		List<TEmployee> tEmployees = repository.findEmployeeByNameList(names);
		for (TEmployee tEmployee : tEmployees) {
			Employee employee = new Employee();
			BeanUtils.copyProperties(tEmployee, employee);
			employees.add(employee);
		}
		response.setEmployees(employees);
		response.setStatus(HttpStatus.OK.name());
		response.setMessage("Success");

		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.star.sud.employee.service.IEmployeeService#updateEmployeeExp(com.star.sud
	 * .employee.dto.Employee)
	 */
	@Override
	public ResponseEntity<Object> updateEmployeeExp(Employee request) {
		try {

			if (null == request)
				throw new RequestParamException("request param is null or empty");

			int upadteCount = repository.updateEmployeeExperienceForName(request.getEmpExperience(),
					request.getEmpName());

			if (upadteCount != 0)
				return ResponseEntity.ok().body(request);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().body(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.star.sud.employee.service.IEmployeeService#insertEmployee(com.star.sud.
	 * employee.dto.Employee)
	 */
	@Override
	public ResponseEntity<Object> insertEmployee(Employee request) {
		ResponseBody response = new ResponseBody();
		try {

			if (null == request)
				throw new RequestParamException("request param is null or empty");

			repository.insertEmployee(request.getEmpName(), request.getEmpEmail(), request.getEmpDob(),
					request.getEmpExperience());

			response.setStatus(HttpStatus.OK.name());
			response.setMessage("Success");
			return ResponseEntity.ok().body(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setStatus(HttpStatus.BAD_REQUEST.name());
		response.setMessage("Failed to update");
		return ResponseEntity.badRequest().body(response);
	}

}

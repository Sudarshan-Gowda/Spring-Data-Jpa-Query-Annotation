package com.star.sud.employee.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class Employee implements Serializable {

	// Static Attributes
	///////////////////////
	private static final long serialVersionUID = 5032691023926707918L;

	// Attributes
	/////////////////
	private Integer empId;

	@Size(min = 2, message = "Name should be minimum of 2 character")
	private String empName;

	@Past(message = "DOB should be in past")
	private Date empDob;

	@Email(message = "Email  is not matching the critiria")
	private String empEmail;

	private Double empExperience;

	// Constructor
	////////////////
	/**
	 * @param empId
	 * @param empName
	 * @param empDob
	 * @param empEmail
	 * @param empSalary
	 */
	public Employee(Integer empId, String empName, Date empDob, String empEmail, Double empExperience) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empDob = empDob;
		this.empEmail = empEmail;
		this.empExperience = empExperience;
	}

	/**
	 * 
	 */
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Properties
	/////////////////
	/**
	 * @return the empId
	 */
	public Integer getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the empDob
	 */
	public Date getEmpDob() {
		return empDob;
	}

	/**
	 * @param empDob the empDob to set
	 */
	public void setEmpDob(Date empDob) {
		this.empDob = empDob;
	}

	/**
	 * @return the empEmail
	 */
	public String getEmpEmail() {
		return empEmail;
	}

	/**
	 * @param empEmail the empEmail to set
	 */
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	/**
	 * @return the empExperience
	 */
	public Double getEmpExperience() {
		return empExperience;
	}

	/**
	 * @param empExperience the empExperience to set
	 */
	public void setEmpExperience(Double empExperience) {
		this.empExperience = empExperience;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

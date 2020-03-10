package com.star.sud.employee.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_EMPLOYEE")
public class TEmployee implements Serializable {

	private static final long serialVersionUID = -7054493308539332932L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_ID", nullable = false, unique = true)
	private Integer empId;

	@Column(name = "EMP_NAME", nullable = false, length = 100)
	private String empName;

	@Column(name = "EMP_DOB", nullable = false)
	private Date empDob;

	@Column(name = "EMP_EMAIL", nullable = false, length = 100, unique = true)
	private String empEmail;

	@Column(name = "EMP_EXPERIENCE")
	private Double empExperience;

	public TEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param empId
	 * @param empName
	 * @param empDob
	 * @param empEmail
	 * @param empSalary
	 */
	public TEmployee(Integer empId, String empName, Date empDob, String empEmail, Double empExperience) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empDob = empDob;
		this.empEmail = empEmail;
		this.empExperience = empExperience;
	}

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

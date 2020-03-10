/**
 * 
 */
package com.star.sud.employee.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.star.sud.employee.model.TEmployee;

/**
 * @author sudarshan
 *
 */
public interface EmployeeRepository extends JpaRepository<TEmployee, Integer>, EmployeeRepositoryCustom {

	// Indexed Parameter
	@Query("from TEmployee o where o.empEmail= ?1")
	public TEmployee employeesByEmail(String name);

	@Query("from TEmployee o where o.empEmail= ?1 and o.empName= ?2")
	public TEmployee employeesByEmailAndNameWithIdxParam(String email, String name);

	// Query parameter
	@Query("from TEmployee o where o.empEmail= :empEmail")
	public TEmployee employeesByEmailWithParam(@Param("empEmail") String empEmail);

	@Query("from TEmployee o where o.empEmail= :empEmail and o.empName= :empName")
	public TEmployee employeeByEmailAndNameWithParam(@Param("empEmail") String empEmail,
			@Param("empName") String empName);

	@Query("from TEmployee o where o.empDob between :fromDate and :toDate")
	public List<TEmployee> employeeByDob(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

	// Sorting
	@Query("select o from TEmployee o")
	public List<TEmployee> findAllEmployees(Sort sort);

	// Pagination
	@Query(value = "select o from TEmployee o order by empId")
	public List<TEmployee> findAllEmployeesWithPagination(Pageable pageable);

	// Find Employee by Name
	public TEmployee findByEmpName(String name);

	// Find Employee by Name List
	@Query(value = "select o from TEmployee o where o.empName IN :empNames")
	public List<TEmployee> findEmployeeByNameList(@Param("empNames") Collection<String> names);

	// Update Query
	@Modifying
	@Transactional
	@Query("update TEmployee o set o.empExperience= :empExperience where o.empName= :empName")
	public int updateEmployeeExperienceForName(@Param("empExperience") Double empExperience,
			@Param("empName") String empName);

	// Insert Query
	@Modifying
	@Transactional
	@Query(value = "insert into T_EMPLOYEE(EMP_NAME, EMP_EMAIL, EMP_DOB, EMP_EXPERIENCE) "
			+ "values(:name, :email, :dob, :experience)", nativeQuery = true)
	public void insertEmployee(@Param("name") String name, @Param("email") String email, @Param("dob") Date dob,
			@Param("experience") Double experience);

}

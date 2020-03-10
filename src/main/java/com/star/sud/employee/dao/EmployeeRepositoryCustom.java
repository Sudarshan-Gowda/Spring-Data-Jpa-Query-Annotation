/**
 * 
 */
package com.star.sud.employee.dao;

import java.util.List;
import java.util.Set;

import com.star.sud.employee.model.TEmployee;

/**
 * @author sudarshan
 *
 */
public interface EmployeeRepositoryCustom {

	/**
	 * @param emails
	 * @return
	 */
	List<TEmployee> findEmployeesByEmails(Set<String> emails);

}

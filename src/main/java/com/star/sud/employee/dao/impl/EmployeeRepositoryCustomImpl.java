/**
 * 
 */
package com.star.sud.employee.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.star.sud.employee.dao.EmployeeRepositoryCustom;
import com.star.sud.employee.model.TEmployee;

/**
 * @author sudarshan
 *
 */
public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<TEmployee> findEmployeesByEmails(Set<String> emails) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TEmployee> query = cb.createQuery(TEmployee.class);
		Root<TEmployee> tEmployee = query.from(TEmployee.class);

		Path<String> emailPath = tEmployee.get("empEmail");

		List<Predicate> predicates = new ArrayList<>();
		for (String email : emails) {
			predicates.add(cb.like(emailPath, email));
		}
		query.select(tEmployee).where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

		return entityManager.createQuery(query).getResultList();
	}

}

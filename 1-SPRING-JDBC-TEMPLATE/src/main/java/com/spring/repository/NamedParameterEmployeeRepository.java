package com.spring.repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NameParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.spring.model.Employee;

@Repository
public class NamedParameterEmployeeRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public Employee getEmployeeById(int id) {

		SqlParameterSource namedParameters = new MapSqlParameterSource("eid",
				Integer.valueOf(id));

		Employee employee = (Employee) namedParameterJdbcTemplate
				.queryForObject(QueryBuilder.EMPLOYEE_SQL_SELECT_BY_ID,
						namedParameters, new BeanPropertyRowMapper<>(
								Employee.class));
		return employee;

	}

	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployee(int deptNo) {

		SqlParameterSource namedParameters = new MapSqlParameterSource("DNO",
				Integer.valueOf(deptNo));

		return namedParameterJdbcTemplate.query(
				QueryBuilder.EMPLOYEE_SQL_SELECT_ALL_BY_DEPT, namedParameters,
				new BeanPropertyRowMapper(Employee.class));
	}

	public int insertEmployee(Employee employee) {
		Map<String, Object> dataMap = new LinkedHashMap<>();
		dataMap.put("FN", employee.getFirst_Name());
		dataMap.put("LN", employee.getLast_Name());
		dataMap.put("AGE", employee.getAge());
		dataMap.put("SAL", employee.getSal());
		dataMap.put("DOJ", employee.getDoj());
		dataMap.put("DNO", employee.getDeptNo());

		SqlParameterSource namedParameters = null;
		// process-1
		// namedParameters = new MapSqlParameterSource(dataMap);

		// process-2
		namedParameters = new MapSqlParameterSource()
				.addValue("FN", employee.getFirst_Name())
				.addValue("LN", employee.getLast_Name())
				.addValue("AGE", employee.getAge())
				.addValue("SAL", employee.getSal())
				.addValue("DOJ", employee.getDoj())
				.addValue("DNO", employee.getDeptNo());

		/*
		 * return namedParameterJdbcTemplate.update(
		 * QueryBuilder.EMPLOYEE_SQL_INSERT, namedParameters);
		 */

		// process-3
		return namedParameterJdbcTemplate.update(
				QueryBuilder.EMPLOYEE_SQL_INSERT, dataMap);

	}

	public int updateEmployee(String firstName, String lastName, int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("first_Name", firstName).addValue("last_Name", lastName)
				.addValue("empId", id);

		return namedParameterJdbcTemplate.update(
				QueryBuilder.EMPLOYEE_SQL_UPDATE, namedParameters);

	}
	
	public int updateEmployee1(Employee e) {
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(e);
				

		return namedParameterJdbcTemplate.update(
				QueryBuilder.EMPLOYEE_SQL_UPDATE, namedParameters);

	}

	public int deleteEmployee(int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("EID", id);
		return namedParameterJdbcTemplate.update(
				QueryBuilder.EMPLOYEE_SQL_DELETE, namedParameters);
	}

	
}

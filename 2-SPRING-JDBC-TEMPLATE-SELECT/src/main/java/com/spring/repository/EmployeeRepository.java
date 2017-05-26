package com.spring.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Component;

import com.spring.model.Employee;

@Component
public class EmployeeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// ****************** row mapper ******************/

	// 1.1 selecting one-object using row mapper

	public Employee getEmployeeById(int id) {
		return this.jdbcTemplate.queryForObject(
				QueryBuilder.EMPLOYEE_SQL_SELECT_BY_ID, new Object[] { id },
				new EmployeeRowMapper());
	}

	// 1.2 selecting list-of objects using row mapper

	public List<Employee> getEmployeeList1(int deptno) {

		return this.jdbcTemplate.query(
				QueryBuilder.EMPLOYEE_SQL_SELECT_ALL_BY_DEPT,
				new Object[] { deptno }, new EmployeeRowMapper());

	}

	// ********************** result set extractor ****************/

	// 2.1.1 using result set extractor in a method
	public Employee getEmployeeById1(int id) {
		return this.jdbcTemplate.query(QueryBuilder.EMPLOYEE_SQL_SELECT_BY_ID,
				new Object[] { id }, new EmployeeResultSetExtractor());
	}

	// 2.2.1 using result set extractor to get list of employee object
	public List<Employee> getEmployeeList2(int deptno) {
		return this.jdbcTemplate.query(
				QueryBuilder.EMPLOYEE_SQL_SELECT_ALL_BY_DEPT,
				new Object[] { deptno }, new ListEmployeeResultSetExtractor());
	}

	// *************** row call back handler ************************/

	// 3.1.1 using EmployeeRowCallBackHandler
	public Employee getEmployeeById3(int id) {
		EmployeeRowCallBackHandler employeeRowCallBackHandler = new EmployeeRowCallBackHandler();
		this.jdbcTemplate.query(QueryBuilder.EMPLOYEE_SQL_SELECT_BY_ID,
				new Object[] { id }, employeeRowCallBackHandler);
		return employeeRowCallBackHandler.emp;
	}

	// 3.2.1 using ListEmployeeRowCallBackHandler
	public List<Employee> getEmployeeList3(int deptno) {
		ListEmployeeRowCallBackHandler listEmployeeRowCallBackHandler = new ListEmployeeRowCallBackHandler();
		this.jdbcTemplate.query(QueryBuilder.EMPLOYEE_SQL_SELECT_ALL_BY_DEPT,
				new Object[] { deptno }, listEmployeeRowCallBackHandler);
		return listEmployeeRowCallBackHandler.employees;
	}

	// ***************** bean property row mapper ***************/
	public List<Employee> getEmployeeList4(int deptno) {
		List<Employee> employees = this.jdbcTemplate.query(
				QueryBuilder.EMPLOYEE_SQL_SELECT_ALL_BY_DEPT,
				new Object[] { deptno }, new BeanPropertyRowMapper(
						Employee.class));

		return employees;
	}

	// ***************** column map row mapper ***********************/
	// 3.3.1
	public List<Map<String, Object>> getAllEmployeesWithColumnRowMapper(
			int deptno) {
		return this.jdbcTemplate.queryForList(
				QueryBuilder.EMPLOYEE_SQL_SELECT_ALL_BY_DEPT,
				new Object[] { deptno });
	}

	// 3.3.2
	public List<Employee> getEmployeeList5(int deptno) {
		// map it to employee bean manually
		List<Map<String, Object>> listMap = null;
		listMap = getAllEmployeesWithColumnRowMapper(deptno);

		List<Employee> empList = null;
		empList = new ArrayList<>();
		for (Map map : listMap) {
			Employee employee = new Employee(map.get("first_name").toString(),
					map.get("last_name").toString(), (Date) map.get("doj"),
					Double.valueOf(map.get("sal").toString()),
					Integer.valueOf(map.get("age").toString()),
					Integer.valueOf(map.get("deptno").toString()));
			employee.setEmpId(Integer.valueOf(map.get("emp_id").toString()));
			empList.add(employee);
		}

		return empList;
	}

	// ***************** single column mapper **********************/
	public List<String> getEmployeesFirstName() {

		
		return this.jdbcTemplate.query(
				QueryBuilder.EMPLOYEE_SQL_SELECT_FIRST_NAME,
				new SingleColumnRowMapper<String>());

	}

}

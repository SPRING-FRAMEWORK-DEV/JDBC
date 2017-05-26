package com.spring.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.model.Employee;

@Repository
public class EmployeeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String INSERT_EMPLOYEE = "INSERT INTO TBL_EMPLOYEE(FIRST_NAME,LAST_NAME,DOJ,AGE,SAL,DEPTNO) VALUES (?,?,?,?,?,?)";

	// INSERT

	public int insertEmployee(Employee employee) {
		
		
		return this.jdbcTemplate.update(INSERT_EMPLOYEE,
				employee.getFirst_Name(), employee.getLast_Name(),
				employee.getDoj(), employee.getAge(), employee.getSal(),
				employee.getDeptNo());
	}

	// SELECT ALL ROWS and ALL COLUMNS USING QUERYFORLIST
	private final String SELECT_ALL_EMP_LIST = "SELECT * FROM TBL_EMPLOYEE";

	public List<Map<String, Object>> getAllEmployees1() {
		return this.jdbcTemplate.queryForList(SELECT_ALL_EMP_LIST);
	}

	// SELECT ALL ROWS and ALL COLUMNS USING BeanPropertyRowMapper
	public List<Employee> getAllEmployees2() {
		List<Employee> employees = this.jdbcTemplate.query(SELECT_ALL_EMP_LIST,
				new BeanPropertyRowMapper(Employee.class));

		return employees;
	}

	// SELECT ALL ROWS AND ALL COLUMNS / SPECIFIC COLS USING CUSTOM ROWMAPPER
	private final String SELECT_FIRST_NAME_AND_LAST_NAME = "SELECT FIRST_NAME,LAST_NAME FROM TBL_EMPLOYEE";

	private static final class EmployeeMapper implements RowMapper<Employee> {

		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee employee = new Employee();
			employee.setFirst_Name(rs.getString("first_name"));
			employee.setLast_Name(rs.getString("last_name"));
			return employee;
		}
	}

	public List<Employee> getAllEmployees3() {
		return this.jdbcTemplate.query(SELECT_FIRST_NAME_AND_LAST_NAME,
				new EmployeeMapper());
	}

	// select a single row with bean property row mapper
	private final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM TBL_EMPLOYEE WHERE EMP_ID=?";

	public Employee findEmployeeById1(int id) {
		return this.jdbcTemplate.queryForObject(SELECT_EMPLOYEE_BY_ID,
				new Object[] { id }, new BeanPropertyRowMapper(Employee.class));
	}

	// select a single row / all / specific columns with custom row mapper
	public Employee findEmployeeById2(int id) {
		return this.jdbcTemplate.queryForObject(SELECT_EMPLOYEE_BY_ID,
				new Object[] { id }, new EmployeeMapper());
	}

	// select single row/ single column
	private final String SELECT_EMPLOYEE_AGE_BY_ID = "SELECT AGE FROM TBL_EMPLOYEE WHERE EMP_ID=?";

	public int getEmployeeAge(int id) {
		return this.jdbcTemplate.queryForObject(SELECT_EMPLOYEE_AGE_BY_ID,
				new Object[] { id }, Integer.class);
	}

	// select count(*) from tbl_employee
	private final String COUNT_EMPLOYEE="SELECT COUNT(*) FROM TBL_EMPLOYEE";
	public int employeeCount() {
		return this.jdbcTemplate.queryForInt(COUNT_EMPLOYEE);
	}
	
	// updating a record 
	private final String UPDATE_EMPLOYEE="UPDATE TBL_EMPLOYEE SET FIRST_NAME=? ,LAST_NAME=? WHERE EMP_ID=?";
	public int updateEmployee(String firstName,String lastName,int id)
	{
		
		return this.jdbcTemplate.update(
		        UPDATE_EMPLOYEE,
		        firstName,lastName,id);
	}
	
	// deleting a record
	private final String DELETE_EMPLOYEE="DELETE FROM TBL_EMPLOYEE WHERE EMP_ID=?";
	public int deleteEmployee(int id)
	{
		return this.jdbcTemplate.update(
				DELETE_EMPLOYEE,id);
	}

}

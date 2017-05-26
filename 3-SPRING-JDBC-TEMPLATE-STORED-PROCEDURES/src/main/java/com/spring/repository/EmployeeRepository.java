package com.spring.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Component;

import com.spring.model.Employee;

@Component
public class EmployeeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// insert
	public int insertEmployee(final Employee e) {
		SqlParameter empName = new SqlParameter(Types.VARCHAR);
		SqlParameter empCity = new SqlParameter(Types.VARCHAR);
		SqlParameter empId = new SqlParameter(Types.NUMERIC);

		SqlOutParameter outParameter = new SqlOutParameter("V_OUTPUT",
				Types.VARCHAR);

		List paramList = new ArrayList();
		paramList.add(empId);
		paramList.add(empName);
		paramList.add(empCity);
		Map<String, Object> resultMap = jdbcTemplate.call(
				new CallableStatementCreator() {

					@Override
					public CallableStatement createCallableStatement(
							Connection connection) throws SQLException {

						CallableStatement callableStatement = connection
								.prepareCall(QueryBuilder.EMPLOYEE_INSERT_PROC);
						callableStatement.setInt(1, e.getEmpId());
						callableStatement.setString(2, e.getEmpName());
						callableStatement.setString(3, e.getEmpCity());

						return callableStatement;

					}
				}, paramList);
		return 0;
	}

	// select all
	// get list of employees by city
	public List<Employee> getEmployeeListByCity(final String city) {
		// part -1
		RowMapper<Employee> rowMapper = new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Employee e = new Employee(rs.getInt("EMPID"),
						rs.getString("EMP_NAME"), rs.getString("EMP_CITY"));
				return e;
			}

		};

		// part -2
		List paramList = new ArrayList<>();

		paramList.add(new SqlParameter(Types.VARCHAR));
		paramList.add(new SqlOutParameter("employeeList", OracleTypes.CURSOR,
				rowMapper));

		// part -3
		CallableStatementCreator callableStatementCreator = new CallableStatementCreator() {

			@Override
			public CallableStatement createCallableStatement(
					Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				CallableStatement callableStatement = connection
						.prepareCall(QueryBuilder.EMPLOYEE_SELECT_ALL_BY_CITY_PROC);

				callableStatement.setString(1, city);
				callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
				return callableStatement;
			}
		};

		// part -4
		Map employeeMap = this.jdbcTemplate.call(callableStatementCreator,
				paramList);

		return (List<Employee>) employeeMap.get("employeeList");

	}

	// select one
	// get an employee by id

	public Object getEmployeeById(final int empId) {
		// part -1
		RowMapper<Employee> rowMapper = new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Employee e = new Employee(rs.getInt("EMPID"),
						rs.getString("EMP_NAME"), rs.getString("EMP_CITY"));
				return e;
			}

		};

		// part -2
		List paramList = new ArrayList<>();

		paramList.add(new SqlParameter(Types.VARCHAR));
		paramList.add(new SqlOutParameter("employee", OracleTypes.CURSOR,
				rowMapper));

		// part -3
		CallableStatementCreator callableStatementCreator = new CallableStatementCreator() {

			@Override
			public CallableStatement createCallableStatement(
					Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				CallableStatement callableStatement = connection
						.prepareCall(QueryBuilder.EMPLOYEE_SELECT_BY_ID_PROC);

				callableStatement.setInt(1, empId);
				callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
				return callableStatement;
			}
		};

		// part -4
		Map employeeMap = this.jdbcTemplate.call(callableStatementCreator,
				paramList);

		return employeeMap.get("employee");

	}

	// update
	public int update(final int id, final String city) {
		SqlParameter empCity = new SqlParameter(Types.VARCHAR);
		SqlParameter empId = new SqlParameter(Types.NUMERIC);

		List paramList = new ArrayList();
		paramList.add(empId);
		paramList.add(empCity);

		CallableStatementCreator callableStatementCreator = new CallableStatementCreator() {

			@Override
			public CallableStatement createCallableStatement(
					Connection connection) throws SQLException {

				CallableStatement callableStatement = connection
						.prepareCall(QueryBuilder.EMPLOYEE_UPDATE_BY_ID_PROC);
				callableStatement.setInt(1, id);
				callableStatement.setString(2, city);

				return callableStatement;

			}
		};

		Map<String, Object> resultMap = jdbcTemplate.call(
				callableStatementCreator, paramList);

		return 0;
	}

	// delete
	public int delete(final int id) {
		SqlParameter empId = new SqlParameter(Types.NUMERIC);

		List paramList = new ArrayList();
		paramList.add(empId);

		CallableStatementCreator callableStatementCreator = new CallableStatementCreator() {

			@Override
			public CallableStatement createCallableStatement(
					Connection connection) throws SQLException {

				CallableStatement callableStatement = connection
						.prepareCall(QueryBuilder.EMLOYEE_DELETE_BY_ID_PROC);
				callableStatement.setInt(1, id);

				return callableStatement;

			}
		};
		Map<String, Object> resultMap = jdbcTemplate.call(
				callableStatementCreator, paramList);

		return 0;
	}

}

package com.spring.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.model.Employee;

// 1 working with custom row mapper
	public class EmployeeRowMapper implements RowMapper<Employee> {

		@Override
		public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			Employee employee = new Employee();
			employee.setFirst_Name(rs.getString("first_name"));
			employee.setLast_Name(rs.getString("last_name"));
			return employee;
		}

	}
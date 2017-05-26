package com.spring.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.spring.model.Employee;
// 2.1 working with result set extractor to get single object

	public class EmployeeResultSetExtractor implements ResultSetExtractor<Employee> {

		@Override
		public Employee extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			// TODO Auto-generated method stub
			Employee emp = null;
			if (rs.next()) {
				emp = new Employee(rs.getString("first_name"),
						rs.getString("last_name"), rs.getDate("doj"),
						rs.getDouble("sal"), rs.getInt("age"),
						rs.getInt("deptno"));
				emp.setEmpId(rs.getInt("emp_id"));
				
			}
			return emp;
		}

	}
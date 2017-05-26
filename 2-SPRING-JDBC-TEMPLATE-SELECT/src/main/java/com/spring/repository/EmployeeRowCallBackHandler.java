package com.spring.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.spring.model.Employee;

// 3.1 it is used to get single object wityh employee id
	public class EmployeeRowCallBackHandler implements RowCallbackHandler {
		Employee emp = null;

		@Override
		public void processRow(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub

			emp = new Employee(rs.getString("first_name"),
					rs.getString("last_name"), rs.getDate("doj"),
					rs.getDouble("sal"), rs.getInt("age"), rs.getInt("deptno"));
			emp.setEmpId(rs.getInt("emp_id"));
		}

	}
package com.spring.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.spring.model.Employee;

// 3.2 it is used to get list of employee object
	public class ListEmployeeRowCallBackHandler implements RowCallbackHandler {
		List<Employee> employees = new ArrayList<>();

		@Override
		public void processRow(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			Employee emp = null;
			emp = new Employee(rs.getString("first_name"),
					rs.getString("last_name"), rs.getDate("doj"),
					rs.getDouble("sal"), rs.getInt("age"), rs.getInt("deptno"));
			emp.setEmpId(rs.getInt("emp_id"));
			employees.add(emp);

		}

	}

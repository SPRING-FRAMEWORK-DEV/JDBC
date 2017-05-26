package com.spring.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.spring.model.Employee;

// 2.2 working with result set extractor to get list of employee object
	public class ListEmployeeResultSetExtractor implements
			ResultSetExtractor<List<Employee>> {

		@Override
		public List<Employee> extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			// TODO Auto-generated method stub
			List<Employee> empList = null;
			empList = new ArrayList<>();
			Employee emp = null;
			while (rs.next()) {
				emp = new Employee(rs.getString("first_name"),
						rs.getString("last_name"), rs.getDate("doj"),
						rs.getDouble("sal"), rs.getInt("age"),
						rs.getInt("deptno"));
				emp.setEmpId(rs.getInt("emp_id"));
				empList.add(emp);
			}
			return empList;
		}

	}

package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.model.Employee;
import com.spring.repository.NamedParameterEmployeeRepository;

@Service
public class NamedParameterEmployeeService {

	@Autowired
	private NamedParameterEmployeeRepository employeeRepository;
	
	public Employee getEmployeeById(int id)
	{
		return this.employeeRepository.getEmployeeById(id);
	}
	public List<Employee> getAllEmployee(int deptNo)
	{
		return this.employeeRepository.getAllEmployee(deptNo);
	}
	public int insertEmployee(Employee employee)
	{
		return this.employeeRepository.insertEmployee(employee);
	}
	
	public int updateEmployee(String firstName,String lastName,int id)
	{
		return this.employeeRepository.updateEmployee(firstName, lastName, id);
	}
	
	public int deleteEmployee(int id)
	{
		return this.employeeRepository.deleteEmployee(id);
	}
}

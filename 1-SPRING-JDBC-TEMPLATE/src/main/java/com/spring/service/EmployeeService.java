package com.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.model.Employee;
import com.spring.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public int insertEmployee(Employee employee)
	{
		return employeeRepository.insertEmployee(employee);
	}
	
	public List<Employee> getEmployeeList1()
	{
		// map it to employee bean manually
		List<Map<String,Object>>listMap=null;
		listMap=this.employeeRepository.getAllEmployees1();
		
		List<Employee> empList=null;
		empList=new ArrayList<>();
		for(Map map:listMap)
		{
			Employee employee=new Employee(map.get("first_name").toString(), map.get("last_name").toString(), (Date)map.get("doj"), Double.valueOf(map.get("sal").toString()), Integer.valueOf(map.get("age").toString()), Integer.valueOf(map.get("deptno").toString()));
			employee.setEmpId(Integer.valueOf(map.get("emp_id").toString()));
			empList.add(employee);
		}
		
		return empList ;
	}
	
	public List<Employee> getEmployeeList2()
	{
		return this.employeeRepository.getAllEmployees2();
	}
	//select all rows but all/ specific columns using custom row mapper
	public List<Employee> getEmployeeList3()
	{
		return this.employeeRepository.getAllEmployees3();
	}
	
	public Employee findEmployeeById1(int id) 
	{
		return this.employeeRepository.findEmployeeById1(id);
	}
	public Employee findEmployeeById2(int id) 
	{
		return this.employeeRepository.findEmployeeById2(id);
	}
	public int getEmployeeAge(int id)
	{
		return this.employeeRepository.getEmployeeAge(id);
	}
	public int employeeCount()
	{
		return this.employeeRepository.employeeCount();
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

package com.app.main;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.spring.model.Employee;
import com.spring.repository.EmployeeRepository;

@Component
public class Test {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = null;

		context = new AnnotationConfigApplicationContext(new String[] {
				"com.app", "com.spring" });
		Test t = context.getBean("test", Test.class);
		// 1 insert employee data - repeat this operation with dfnt data
		//t.insertEmployee();
		
		// 2 get employee list by city
		//t.getEmployeeCity("noida");
		
		// 3 get employee by id
		//t.getEmployeeById(54657);
		
		// 4 update city by employee id
		//t.updateEmployeeById("BLR", 54657);
		
		// 5 delete employee by id
		t.deleteEmployeeById(54657);
		
	}

	public void insertEmployee()
	{
		Employee e=new Employee(new Random().nextInt(999999), "rahul", "noida");
		employeeRepository.insertEmployee(e);
	}
	
	public void getEmployeeCity(String city)
	{
		System.out.println("List Employee: "+employeeRepository.getEmployeeListByCity(city));
	}
	
	public void getEmployeeById(int empId)
	{
		System.out.println("Employee data: "+employeeRepository.getEmployeeById(empId));
	}
	
	public void updateEmployeeById(String city,int empId)
	{
		employeeRepository.update(empId,city);
	}
	
	public void deleteEmployeeById(int empId)
	{
		employeeRepository.delete(empId);
	}
}

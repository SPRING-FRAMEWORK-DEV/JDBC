package com.app.main;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.spring.model.Employee;
import com.spring.repository.NamedParameterEmployeeRepository;
import com.spring.service.NamedParameterEmployeeService;

@Component
public class NamedParameterJdbcTemplateTest {

	@Autowired
	private NamedParameterEmployeeService employeeService;
	@Autowired
	private NamedParameterEmployeeRepository employeeRepository;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = null;

		context = new AnnotationConfigApplicationContext(new String[] {
				"com.app", "com.spring" });

		NamedParameterJdbcTemplateTest t = context.getBean(
				"namedParameterJdbcTemplateTest",
				NamedParameterJdbcTemplateTest.class);

		// insert employee
		//t.insertEmployee();

		// get employee by id
		// t.getEmployeeById(3);

		// get employee list by dept id
		// t.getAllEmployee(10);
		
		// update employee 
		//t.updateEmployee("AROHI", "SHARMA", 5);
		  t.updateEmployee1();
		// delete employee
		//t.deleteEmployee(8);

	}

	public void getEmployeeById(int id) {
		System.out.println(employeeService.getEmployeeById(id));
	}

	public void getAllEmployee(int deptNo) {
		System.out.println(employeeService.getAllEmployee(deptNo));
	}

	public void insertEmployee() {

		Employee employee = new Employee("ANISH", "DASH", new Date(
				System.currentTimeMillis()), 2345.67, 26, 10);

		int result = employeeService.insertEmployee(employee);

		System.out.println(result + " Record inserted");
	}

	public void updateEmployee(String firstName, String lastName, int id) {
		System.out.println("Rows updated: "
				+ employeeService.updateEmployee(firstName, lastName, id));
	}
	
	public void updateEmployee1() {
		
		Employee e=new Employee();
		e.setFirst_Name("AMIT");
		e.setLast_Name("SAHA");
		e.setEmpId(5);
		System.out.println("Rows updated: "
				+ employeeRepository.updateEmployee1(e));
	}


	public void deleteEmployee(int id) {
		System.out.println("Rows Deleted: "
				+ employeeService.deleteEmployee(id));
	}

}

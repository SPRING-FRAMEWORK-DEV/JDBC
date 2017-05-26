package com.app.main;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.spring.model.Employee;
import com.spring.service.EmployeeService;

@Component
public class Test {
	
	@Autowired
	private EmployeeService employeeService;
	public static void main(String[] args) {
		ApplicationContext context = null;

		context = new AnnotationConfigApplicationContext(new String[] {
				"com.app", "com.spring" });
		
		Test t=context.getBean("test",Test.class);
		// case 1: insert a record
		//t.insertEmployee();// call this 4 times with dfnt data so that we have 4 records in our database
		
		// case 2: select all record using query for list
		//t.getAllEmployee1();
		
		// case 3: select all record using bean property row mapper
		//t.getAllEmployee2();

		// case 4: select all rows but all/ specific columns using custom row mapper
		//t.getAllEmployee3();
		
		// case 5: selecting a single row using bean property row mapper
		//t.findEmployeeById1(2);
		
		// case 6: select a single row / all / specific columns with custom row mapper
		//t.findEmployeeById2(2);
		
		// case 7: select a single row/single column 
		//t.findEmpAgeBydId(2);
		
		// case 8: counting total employee using count
		//t.employeeCount();
		
		// case 9: updating a record
		//t.updateEmployee("ASOK", "MALHOTRA", 2);
		
		// case 10: deleting a record
		t.deleteEmployee(4);
	}

	public  void insertEmployee() {
		
		Employee employee=new Employee("SATISH", "NAYAK", new Date(System.currentTimeMillis()), 2345.67, 24, 20);
		
		int result=employeeService.insertEmployee(employee);
		
		System.out.println(result+" Record inserted");
	}
	
	public void getAllEmployee1()
	{
		System.out.println(employeeService.getEmployeeList1());
	}
	
	public void getAllEmployee2()
	{
		System.out.println(employeeService.getEmployeeList2());
	}
	
	public void getAllEmployee3()
	{
		System.out.println(employeeService.getEmployeeList3());
	}
	
	public void findEmployeeById1(int id)
	{
		System.out.println(employeeService.findEmployeeById1(id));
	}
	
	public void findEmployeeById2(int id)
	{
		System.out.println(employeeService.findEmployeeById2(id));
	}
	
	public void findEmpAgeBydId(int id)
	{
		System.out.println("Employee Age: "+employeeService.getEmployeeAge(id));
	}
	
	public void employeeCount()
	{
		System.out.println("Total EMployee: "+employeeService.employeeCount());
	}
	
	public void updateEmployee(String firstName,String lastName,int id)
	{
		System.out.println("Rows updated: "+employeeService.updateEmployee(firstName, lastName, id));
	}
	public void deleteEmployee(int id)
	{
		System.out.println("Rows Deleted: "+employeeService.deleteEmployee(id));
	}
	
}

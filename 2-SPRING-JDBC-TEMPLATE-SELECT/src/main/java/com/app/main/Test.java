package com.app.main;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.spring.repository.EmployeeRepository;

@Component
public class Test {

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {

		Map m=new HashMap<>();
		
		System.out.println(m);
		ApplicationContext context = null;

		context = new AnnotationConfigApplicationContext(new String[] {
				"com.app", "com.spring" });
		Test t = context.getBean("test", Test.class);
		//1 ****row mapper

		// get employee by id
		// t.getEmployeeBydId1(5);
		// get employee list
		// t.getEmployeeList1(10);

		//2 ****result set extractor

		// get employee by id
		// t.getEmployeeBydId2(5);
		// get employee list
		// t.getEmployeeList2(10);

		//3 ****row call back handler
		// get employee by id
		// t.getEmployeeBydId3(5);
		// get employee list
		//t.getEmployeeList3(10);
		
		//4 ***bean property row mapper
		//t.getEmployeeList4(10);
		
		//5 ***column map row mapper
		//t.getEmployeeList5(10);
		
		//6 ** get all employee first name
		t.getEmployeesFirstName();

	}

	private void getEmployeeBydId1(int id) {
		System.out.println("emp with id " + id + "--"
				+ employeeRepository.getEmployeeById(id));
	}

	private void getEmployeeList1(int deptno) {
		System.out.println("emp list: "
				+ employeeRepository.getEmployeeList1(deptno));
	}

	private void getEmployeeBydId2(int id) {
		System.out.println("emp with id " + id + "--"
				+ employeeRepository.getEmployeeById1(id));
	}

	private void getEmployeeList2(int deptno) {
		System.out.println("emp list: "
				+ employeeRepository.getEmployeeList2(deptno));
	}

	private void getEmployeeBydId3(int id) {
		System.out.println("emp with id " + id + "--"
				+ employeeRepository.getEmployeeById3(id));
	}

	private void getEmployeeList3(int deptno) {
		System.out.println("emp list: "
				+ employeeRepository.getEmployeeList3(deptno));
	}
	
	private void getEmployeeList4(int deptno) {
		System.out.println("emp list: "
				+ employeeRepository.getEmployeeList4(deptno));
	}
	
	private void getEmployeeList5(int deptno) {
		System.out.println("emp list: "
				+ employeeRepository.getEmployeeList5(deptno));
	}
	
	private void getEmployeesFirstName()
	{
		System.out.println("first names: "+employeeRepository.getEmployeesFirstName());
	}
}

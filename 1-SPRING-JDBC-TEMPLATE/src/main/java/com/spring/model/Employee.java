package com.spring.model;
import java.util.Date;

public class Employee {
	
	private int empId;
	private String first_Name;
	private String last_Name;
	private Date doj;
	private double sal;
	private int age;
	private int deptNo;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String first_Name, String last_Name, Date doj, double sal,
			int age, int deptNo) {
		super();
		this.first_Name = first_Name;
		this.last_Name = last_Name;
		this.doj = doj;
		this.sal = sal;
		this.age = age;
		this.deptNo = deptNo;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getFirst_Name() {
		return first_Name;
	}
	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}
	public String getLast_Name() {
		return last_Name;
	}
	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public double getSal() {
		return sal;
	}
	public void setSal(double sal) {
		this.sal = sal;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	@Override
	public String toString() {
		return "Employee\n [empId=" + empId + ", first_Name=" + first_Name
				+ ", last_Name=" + last_Name + ", doj=" + doj + ", sal=" + sal
				+ ", age=" + age + ", deptNo=" + deptNo + "]\n";
	}
	
	
	

}

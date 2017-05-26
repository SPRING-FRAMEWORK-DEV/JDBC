package com.spring.repository;

public class QueryBuilder {
	
	public final static String EMPLOYEE_INSERT_PROC="{call P_INSERTEMPLOYEE(?,?,?)}";
	
	public final static String EMPLOYEE_SELECT_ALL_BY_CITY_PROC="{call P_GETALLEMPLOYEES(?,?)}";
	
	public final static String EMPLOYEE_SELECT_BY_ID_PROC="{call P_GETEMPLOYEEBYID(?,?)}";
	
	public final static String EMPLOYEE_UPDATE_BY_ID_PROC="{call P_UPDATEEMPLOYEE(?,?)}";
	
	public final static String EMLOYEE_DELETE_BY_ID_PROC="{call P_DELETEEMPLOYEE(?)}";
	
	

	}

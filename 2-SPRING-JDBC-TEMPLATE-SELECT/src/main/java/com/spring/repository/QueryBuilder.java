package com.spring.repository;

public class QueryBuilder {

	public static final String EMPLOYEE_SQL_SELECT_ALL_BY_DEPT = "SELECT * FROM TBL_EMPLOYEE WHERE DEPTNO=?";

	public static final String EMPLOYEE_SQL_SELECT_BY_ID = "SELECT * FROM TBL_EMPLOYEE WHERE EMP_ID = ?";

	public static final String EMPLOYEE_SQL_SELECT_FIRST_NAME = "SELECT FIRST_NAME FROM TBL_EMPLOYEE";
}

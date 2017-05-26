package com.spring.repository;

public class QueryBuilder {

	public static final String EMPLOYEE_SQL_SELECT_ALL_BY_DEPT = "SELECT * FROM TBL_EMPLOYEE WHERE DEPTNO=:DNO";

	public static final String EMPLOYEE_SQL_SELECT_BY_ID = "SELECT * FROM TBL_EMPLOYEE WHERE EMP_ID = :eid";

	public static final String EMPLOYEE_SQL_INSERT = "INSERT INTO TBL_EMPLOYEE(FIRST_NAME,LAST_NAME,DOJ,AGE,SAL,DEPTNO) VALUES(:FN,:LN,:DOJ,:AGE,:SAL,:DNO)";

	public static final String EMPLOYEE_SQL_UPDATE = "UPDATE TBL_EMPLOYEE SET FIRST_NAME=:first_Name,LAST_NAME=:last_Name WHERE EMP_ID=:empId";

	public static final String EMPLOYEE_SQL_DELETE = "DELETE FROM TBL_EMPLOYEE WHERE EMP_ID=:EID";
}

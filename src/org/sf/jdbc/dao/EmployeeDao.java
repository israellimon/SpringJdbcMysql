package org.sf.jdbc.dao;

import org.sf.jdbc.model.Employee;

public interface EmployeeDao {
	
	public Employee getEmployeeById(int id);
	public void createEmployee();
	public void insertEmployee(Employee employee);

}

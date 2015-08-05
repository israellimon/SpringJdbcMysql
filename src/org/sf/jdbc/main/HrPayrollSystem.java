package org.sf.jdbc.main;

import org.sf.jdbc.dao.EmployeeDao;
import org.sf.jdbc.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HrPayrollSystem {
	
	public static void main(String [] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
		EmployeeDao employeeDao = context.getBean("employeeDaoImpl", EmployeeDao.class);
		employeeDao.createEmployee();
		employeeDao.insertEmployee(new Employee(1, "Emilio"));
		Employee employee = employeeDao.getEmployeeById(1);
		System.out.println("Employee: "+employee.getName());
	}

}

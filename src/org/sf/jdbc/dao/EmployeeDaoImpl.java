package org.sf.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.sf.jdbc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired
	DataSource dataSource;

	@Override
	public Employee getEmployeeById(int id) {
		Employee employee = null;
		Connection con = null;
		
		try {
			con = dataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement("select * from employee where id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				employee = new Employee(id,rs.getString("name"));
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					
				}
			}
		}
		
		
		return employee;
	}

	@Override
	public void createEmployee() {
		Connection con = null;
		
		try {
			con = dataSource.getConnection();
			Statement st = con.createStatement();
			st.executeUpdate("create table employee(id int, name varchar(250))");
			st.close();
		} catch (SQLException e) {
			throw new RuntimeException("e");
		}finally {
			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					
				}
			}
		}
		
	}

	@Override
	public void insertEmployee(Employee employee) {
		Connection con = null;
		
		try {
			con = dataSource.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("insert into employee values("+employee.getId()+",'"+employee.getName()+"')");
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					
				}
			}
		}
		
	}

}

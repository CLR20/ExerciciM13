package com.admin.ExerciciJavaM13.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.admin.ExerciciJavaM13.bean.Employee;

public class EmployeeDDBBJDBC {
	
	private Connection cnt;
	
	public EmployeeDDBBJDBC() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String connect = "jdbc:mysql://localhost:3306/exercicim13";
			this.cnt = DriverManager.getConnection(connect, "root", "lsdKJcnw34394xmmn903x4,.,.,");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Employee getEmployee(int id) {
		Employee employee = null;
		try {
			Statement s = cnt.createStatement();
			String query = "SELECT * FROM employees WHERE id = " + id;
			s.execute(query);
			ResultSet rs = s.getResultSet();
			rs.next();
			employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		}
		return employee;		
	}
	
	public ArrayList<Employee> getEmployees() {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			Statement s = cnt.createStatement();
			String query = "SELECT * FROM employees";
			s.execute(query);
			ResultSet rs = s.getResultSet();
			while (rs.next()) {
				Employee employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getDouble(4));
				employees.add(employee);
			}
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		}
		return employees;
	}
	
	public ArrayList<Employee> getSearch(String job) {
		ArrayList<Employee> employees = new ArrayList<Employee>();		
		if (job.equals("ALL")) {
			try {
				String query = "SELECT * FROM employees";
				Statement s = cnt.createStatement();
				s.execute(query);
				ResultSet rs = s.getResultSet();
				while (rs.next()) {
					Employee employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3),
							rs.getDouble(4));
					employees.add(employee);
				}
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		else {
			String query = "SELECT * FROM employees WHERE job = '" + job + "'";
			try {
				Statement s = cnt.createStatement();
				s.execute(query);
				ResultSet rs = s.getResultSet();
				while (rs.next()) {
					Employee employee= new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), 
							rs.getDouble(4));
					employees.add(employee);
				}
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		return employees;
	}	

	public void updateEmployee(Employee employee) {
		String query = "UPDATE employees SET name = ?, job = ?, salary = ? WHERE id = ?";
		try {
			PreparedStatement ps = cnt.prepareStatement(query);
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getJob());
			ps.setDouble(3, employee.setSalarySQL(employee.getJob()));
			ps.setInt(4, employee.getId());
			System.out.print(ps.toString());
			
			ps.executeUpdate();
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		}
	}
		
	public void deleteEmployee(int id) {
		String query = "DELETE FROM employees WHERE id =" + id;
		try {
			PreparedStatement ps = cnt.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		}
	}
	
	public void createEmployee(String name, String job) {
		String query = "INSERT INTO employees (id, name, job, salary) VALUES (?, ?, ?, ?)";
		Employee employee = new Employee(name, job);
		try {
			PreparedStatement ps = cnt.prepareStatement(query);
			ps.setInt(1, employee.getId());
			ps.setString(2, employee.getName());
			ps.setString(3, employee.getJob());
			ps.setDouble(4, employee.setSalarySQL(employee.getJob()));
			ps.executeUpdate();
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		}
	}

}

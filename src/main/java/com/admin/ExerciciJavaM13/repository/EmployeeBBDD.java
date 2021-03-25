package com.admin.ExerciciJavaM13.repository;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.admin.ExerciciJavaM13.bean.Employee;
import com.admin.ExerciciJavaM13.bean.Employee.Category;

public class EmployeeBBDD {
	
	ArrayList<Employee> employees = new ArrayList<Employee>();
	
	public EmployeeBBDD () {
		employees.add(new Employee("Anna Grau", Category.MANAGER));
		employees.add(new Employee("Pere Pou", Category.SENIOR_PROGRAMMER));
		employees.add(new Employee("Laura Abat", Category.SENIOR_PROGRAMMER));
		employees.add(new Employee("Joan Lluna", Category.JUNIOR_PROGRAMMER));
		employees.add(new Employee("Berta Sol", Category.JUNIOR_PROGRAMMER));
		employees.add(new Employee("Jordi Pont", Category.ADMINISTRATIVE));
	}
	
	public void setEmployees (ArrayList<Employee> employees) {
		this.employees = employees;
	}
	
	public ArrayList<Employee> getEmployees () {
		return employees;
	}
	
	public Employee getEmployee (int id) {
		Employee finalEmployee = null;
		for (Employee employee: employees) {
			if (employee.getId() == id) {
				finalEmployee = employee;
				break;
			}
		}
		return finalEmployee;
	}
	
	public void createEmployee(String name, String job) {
		employees.add(new Employee(name, job));
	}
	
	public void deleteEmployee(int id) {		
		for (Employee employee: employees) {
			int actualId = employee.getId();
			if (actualId == id) {
				employees.remove(employee);
				break;
			}
		}
	}
	
	public void update(Employee employee) {
		for (Employee actualEmployee: employees) {
			if (actualEmployee.getId() == employee.getId()) {
				actualEmployee.setName(employee.getName());
				actualEmployee.setJob(employee.getJob());
				actualEmployee.setSalary(employee.getJob());
			}
		}
	}
	
	public ArrayList<Employee> getSearch(String job) {
		ArrayList<Employee> filtered = new ArrayList<Employee>();
		for (Employee employee: employees) {
			String actualJob = employee.getJob();
			if (actualJob.equals(job)) {
				filtered.add(employee);
			} else if (job.equals("ALL")) {
				filtered.add(employee);
			}
		}		
		return filtered;
	}

}

package com.admin.ExerciciJavaM13.bean;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Employee {
	
	public int id;
	protected static int setId = 1;
	public String name;
	public String job;
	public double salary;
	
	public enum Category {
		ALL,
		MANAGER,
		SENIOR_PROGRAMMER,
		MEDIUM_PROGRAMMER,
		JUNIOR_PROGRAMMER,
		ADMINISTRATIVE
	}
	
	public enum Salaries {
		MANAGER_SALARY(100000),
		SENIOR_PROGRAMMER_SALARY(70000),
		MEDIUM_PROGRAMMER_SALARY(50000),
		JUNIOR_PROGRAMMER_SALARY(30000),
		ADMINISTRATIVE_SALARY(25000);
		
		private int salaries;
		Salaries (int salaries) {
			this.salaries = salaries;
		}
		
		public static void getSalaries(int category) {
			Salaries salaries[] = Salaries.values();
			System.out.print(salaries[category].salaries);
		}
	}	

	public Employee() {
		super();
	}
	
	public Employee (String name, Category j) {
		id = setId;
		this.name = name;
		this.job = j.toString();
		if (job.equals("MANAGER")) {
			salary = 100000;
			setId++;
		}
		else if (job.equals("SENIOR_PROGRAMMER")) {
			salary = 70000;
			setId++;
		}
		else if (job.equals("MEDIUM_PROGRAMMER")) {
			salary = 50000;
			setId++;
		}
		else if (job.equals("JUNIOR_PROGRAMMER")) {
			salary = 30000;
			setId++;
		}
		else if (job.equals("ADMINISTRATIVE")) {
			salary = 25000;
			setId++;
		}  else { 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid field or fields");			
		}
	}
	
	public Employee (String name, String job) {
		id = setId;	
		this.name = name;		
		this.job = job;					
		if (job.equals("MANAGER")) {
			salary = 100000;
			setId++;
		} 
		else if (job.equals("SENIOR_PROGRAMMER")) {
			salary = 70000;
			setId++;
		}
		else if (job.equals("MEDIUM_PROGRAMMER")) {
			salary = 50000;
			setId++;
		}
		else if (job.equals("JUNIOR_PROGRAMMER")) {
			salary = 30000;
			setId++;
		}
		else if (job.equals("ADMINISTRATIVE")) {
			salary = 25000;
			setId++;
		} 
		else { 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid field or fields");			
		}
	}	

	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}	
	public String getName() {
		return name;
	}
	
	public void setJob(String job) {
		this.job = job;
	}	
	public String getJob() {
		return job;
	}
	
	public void setSalary(String job) {			
		if (job.equals("MANAGER")) {
			salary = 100000;
		}
		else if (job.equals("SENIOR_PROGRAMMER")) {
			salary = 70000;
		}
		else if (job.equals("MEDIUM_PROGRAMMER")) {
			salary = 50000;
		}
		else if (job.equals("JUNIOR_PROGRAMMER")) {
			salary = 30000;
		}
		else if (job.equals("ADMINISTRATIVE")) {
			salary = 25000;
		} else { 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect job category");			
		}		
	}	
	public double getSalary() {
		return salary;
	}

}

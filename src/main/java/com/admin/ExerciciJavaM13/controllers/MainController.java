package com.admin.ExerciciJavaM13.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.admin.ExerciciJavaM13.bean.*;
import com.admin.ExerciciJavaM13.repository.EmployeeDDBBJDBC;
import com.admin.ExerciciJavaM13.repository.EmployeeDDBBJPA;

@Controller
@RequestMapping("")
public class MainController {
	
	EmployeeDDBBJDBC dbjdbc = new EmployeeDDBBJDBC();
	@Autowired
	EmployeeDDBBJPA dbjpa;
		
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public String start(Model model) {
		return "start";
	}
	
	@PostMapping("/employees")
	@ResponseStatus(HttpStatus.OK)
	public String startBBDD(Model model) {
		ArrayList<Employee> employees = (ArrayList<Employee>) dbjpa.findAll();
		model.addAttribute("employees", employees);
		return "ddbbmanagement";
	}	

	@GetMapping("/employees_filtered/job")
	@ResponseStatus(HttpStatus.OK)
	public String search(String job, Model model) {	
		ArrayList<Employee> employees = dbjdbc.getSearch(job);
		model.addAttribute("employees", employees);
		model.addAttribute("button", "Search");
		model.addAttribute("action", "/employees_filtered/job");
		return "ddbbmanagement";
	}
	
	@PostMapping("/employees_created")
	@ResponseStatus(HttpStatus.OK)
	public String create(String name, String job, Model model) {
		Employee employee = new Employee(name, job);
		dbjpa.save(employee);
		ArrayList<Employee> employees = (ArrayList<Employee>) dbjpa.findAll();
		model.addAttribute("employees", employees);
		model.addAttribute("employee", null);
		return "ddbbmanagement";
	}
	
	@GetMapping("/employees_deleted/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String delete(@PathVariable int id, Model model) {
		dbjpa.deleteById(id);
		ArrayList<Employee> employees = (ArrayList<Employee>) dbjpa.findAll();
		model.addAttribute("employees", employees);
		return "ddbbmanagement";
	}
	
	@GetMapping("/employees_updated/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String update (@PathVariable int id, Model model) {
		ArrayList<Employee> employees = (ArrayList<Employee>) dbjpa.findAll();
		Employee employee = dbjpa.findById(id).get();
		model.addAttribute("employees", employees);
		model.addAttribute("employee", employee);
		model.addAttribute("button", "Update Employee");
		model.addAttribute("action", "/employees_updated");
		return "ddbbmanagement";			
	}
	
	@PostMapping("/employees_updated")
	@ResponseStatus(HttpStatus.OK)
	public String alreadyUpdated(Employee employee, Model model) {
		dbjdbc.updateEmployee(employee);
		dbjpa.save(employee);
		ArrayList<Employee> employees = (ArrayList<Employee>)dbjpa.findAll();
		model.addAttribute("employees", employees);
		return "ddbbmanagement";
	}
	
}

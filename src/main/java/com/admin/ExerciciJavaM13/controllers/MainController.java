package com.admin.ExerciciJavaM13.controllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.admin.ExerciciJavaM13.bean.*;
import com.admin.ExerciciJavaM13.repository.EmployeeBBDD;

@Controller
@RequestMapping("")
public class MainController {
	
	EmployeeBBDD ddbb = new EmployeeBBDD();
		
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public String start(Model model) {
		return "start";
	}
	
	@PostMapping("/employees")
	@ResponseStatus(HttpStatus.OK)
	public String startBBDD(Model model) {
		ArrayList<Employee> employees = ddbb.getEmployees();
		model.addAttribute("employees", employees);
		return "ddbbmanagement";
	}
	

	@GetMapping("/employees_filtered/job")
	@ResponseStatus(HttpStatus.OK)
	public String search(String job, Model model) {	
		ArrayList<Employee> employees = ddbb.getSearch(job);
		model.addAttribute("employees", employees);
		return "ddbbmanagement";
	}
	
	@PostMapping("/employees_created")
	@ResponseStatus(HttpStatus.OK)
	public String create(String name, String job, Model model) {
		ddbb.createEmployee(name, job);
		ArrayList<Employee> employees = ddbb.getEmployees();
		model.addAttribute("employees", employees);
		model.addAttribute("employee", null);
		return "ddbbmanagement";
	}
	
	@GetMapping("/employees_deleted/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String delete(@PathVariable int id, Model model) {
		ddbb.deleteEmployee(id);
		ArrayList<Employee> employees = ddbb.getEmployees();
		model.addAttribute("employees", employees);
		return "ddbbmanagement";
	}
	
	@GetMapping("/employees_updated/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String update (@PathVariable int id, Model model) {
		ArrayList<Employee> employees = ddbb.getEmployees();
		Employee employee = ddbb.getEmployee(id);
		model.addAttribute("employees", employees);
		model.addAttribute("employee", employee);
		model.addAttribute("button", "Update Employee");
		model.addAttribute("action", "/employees_updated");
		return "ddbbmanagement";			
	}
	
	@PostMapping("/employees_updated")
	@ResponseStatus(HttpStatus.OK)
	public String alreadyUpdated(Employee employee, Model model) {
		ddbb.update(employee);
		ArrayList<Employee> employees = ddbb.getEmployees();
		model.addAttribute("employees", employees);
		return "ddbbmanagement";
	}
	
}

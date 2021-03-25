package com.admin.ExerciciJavaM13.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admin.ExerciciJavaM13.bean.Employee;

public interface EmployeeDDBBJPA extends JpaRepository<Employee, Integer>{

}

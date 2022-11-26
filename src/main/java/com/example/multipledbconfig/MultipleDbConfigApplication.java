package com.example.multipledbconfig;

import com.example.multipledbconfig.model.department.Department;
import com.example.multipledbconfig.model.user.User;
import com.example.multipledbconfig.repository.department.DepartmentRepo;
import com.example.multipledbconfig.repository.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class MultipleDbConfigApplication {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private DepartmentRepo departmentRepo;

	@PostConstruct
	public void addDataToDB(){
		userRepo.saveAll(Stream.of(new User(1L, "abhishek"), new User(2L, "chums")).collect(Collectors.toList()));
		departmentRepo.saveAll(Stream.of(new Department(1L, "abhiDepartment"),new Department(2L, "chumsDepartment")).collect(Collectors.toList()));
	}
	public static void main(String[] args) {
		SpringApplication.run(MultipleDbConfigApplication.class, args);
	}

}

package com.example.multipledbconfig.controller;

import com.example.multipledbconfig.model.department.Department;
import com.example.multipledbconfig.model.user.User;
import com.example.multipledbconfig.repository.department.DepartmentRepo;
import com.example.multipledbconfig.repository.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetDataController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private DepartmentRepo departmentRepo;

    @GetMapping("/user")
    public List<User> getUserData(){
        return userRepo.findAll();
    }
    @GetMapping("/department")
    public List<Department> getDepartmentData(){
        return departmentRepo.findAll();
    }


}

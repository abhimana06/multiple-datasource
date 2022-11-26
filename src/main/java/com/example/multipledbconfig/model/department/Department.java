package com.example.multipledbconfig.model.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="departments")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "department_id")
    private Long departmentId;
    @Column(name="department_name",length = 255, nullable = false)
    private String departmentName;


}

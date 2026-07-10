package com.vti.service;

import com.vti.dto.DepartmentDTO;
import com.vti.entity.Department;

import java.util.List;

public interface IDepartmentService {
    List<DepartmentDTO> findAll();

    DepartmentDTO findById(Integer id);

    void deleteById(Integer id);

    void create(Department department);

    void update(Department department, Integer id);
}

package com.vti.service.impl;

import com.vti.dto.AccountDTO;
import com.vti.dto.DepartmentDTO;
import com.vti.entity.Department;
import com.vti.repository.IDepartmentRepository;
import com.vti.service.IDepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private IDepartmentRepository departmentRepository;// = new

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DepartmentDTO> findAll() {
        List<Department> departments = departmentRepository.findAll();

        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        for (Department department : departments) {

            DepartmentDTO dto = modelMapper.map(department, DepartmentDTO.class);
            departmentDTOS.add(dto);
        }
        return departmentDTOS;
    }

    @Override
    public DepartmentDTO findById(Integer id) {
        Department department = departmentRepository.findById(id).orElse(null);
        DepartmentDTO dto =null;
        if (Objects.nonNull(department)) {
            dto = modelMapper.map(department, DepartmentDTO.class);
        }
        //orElse(null)   : nếu optional ko có gtrri thì sẽ gán luôn = gtri null
        // DB   Ko có id 20,   coos tình tìm id = 20
        return dto;
    }

    @Override
    public void deleteById(Integer id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public void create(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void update(Department department, Integer id) {
        //  tìm department can update theo id
        Department departmentUpdate = departmentRepository.findById(id).orElse(null);
        if (Objects.isNull(departmentUpdate)) {
            throw new RuntimeException("ID not found!");
        } else {
            // lưu lại thông tin update
            departmentUpdate.setName(department.getName());
            departmentRepository.save(departmentUpdate);
        }
    }
}
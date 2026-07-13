package com.vti.controller;


import com.vti.dto.PositionDTO;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.service.IDepartmentService;
import com.vti.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/positions")
@CrossOrigin("*")
public class PositionController {
    @Autowired
    private IPositionService positionService;

    @GetMapping
    public ResponseEntity<List<PositionDTO>> findAll() {
        return new ResponseEntity<>(positionService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public String create(@RequestBody Position position) {
        positionService.create(position);
        return "Create Success";
    }
}

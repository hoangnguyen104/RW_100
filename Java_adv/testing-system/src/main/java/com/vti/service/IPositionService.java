package com.vti.service;

import com.vti.dto.PositionDTO;
import com.vti.entity.Department;
import com.vti.entity.Position;

import java.util.List;

public interface IPositionService {
    List<PositionDTO> findAll();
    void create(Position position);
}

package com.vti.service;

import com.vti.entity.Department;
import com.vti.entity.Position;

import java.util.List;

public interface IPositionService {
    List<Position> findAll();
    void create(Position position);
}

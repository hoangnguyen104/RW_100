package com.vti.service.impl;

import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.repository.IDepartmentRepository;
import com.vti.repository.IPositionRepository;
import com.vti.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements IPositionService {

    @Autowired
    private IPositionRepository iPositionRepository;

    @Override
    public List<Position> findAll() {
        List<Position> positions = iPositionRepository.findAll();
        return positions;
    }

    @Override
    public void create(Position position) {
       iPositionRepository.save(position);
    }
}

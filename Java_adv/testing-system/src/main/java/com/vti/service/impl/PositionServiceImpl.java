package com.vti.service.impl;

import com.vti.dto.PositionDTO;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.repository.IDepartmentRepository;
import com.vti.repository.IPositionRepository;
import com.vti.service.IPositionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionServiceImpl implements IPositionService {

    @Autowired
    private IPositionRepository PositionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PositionDTO> findAll() {
        List<Position> positions = PositionRepository.findAll();
        List<PositionDTO> positionDTOS = new ArrayList<>();
        for (Position position : positions) {
            PositionDTO dto = modelMapper.map(position, PositionDTO.class);
           positionDTOS.add(dto);
        }
        return positionDTOS;
    }

    @Override
    public void create(Position position) {
       PositionRepository.save(position);
    }
}

package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Position;
import com.ua.itclusterjava2024.repository.PositionRepository;
import com.ua.itclusterjava2024.service.interfaces.PositionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Position create(Position position) {
        return positionRepository.save(position);
    }

    @Override
    public Optional<Position> readById(long id) {
        return positionRepository.findById(id);
    }

    @Override
    public Position update(long id, Position position) {
        position.setId(id);
        return positionRepository.save(position);
    }

    @Override
    public void delete(long id) {
        positionRepository.deleteById(id);
    }

    @Override
    public Page<Position> getAll(Pageable pageable) {
        return positionRepository.findAll(pageable);
    }

    @Override
    public List<Position> getAll() {
        return positionRepository.findAll();
    }
}

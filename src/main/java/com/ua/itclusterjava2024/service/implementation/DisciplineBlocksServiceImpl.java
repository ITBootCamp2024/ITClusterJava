package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.DisciplineBlocks;
import com.ua.itclusterjava2024.repository.DisciplineBlockRepository;
import com.ua.itclusterjava2024.service.interfaces.DisciplineBlocksService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplineBlocksServiceImpl implements DisciplineBlocksService {

    private final DisciplineBlockRepository disciplineBlockRepository;

    public DisciplineBlocksServiceImpl(DisciplineBlockRepository disciplineBlockRepository) {
        this.disciplineBlockRepository = disciplineBlockRepository;
    }

    @Override
    public DisciplineBlocks create(DisciplineBlocks disciplineBlocks) {
        return disciplineBlockRepository.save(disciplineBlocks);
    }

    @Override
    public Optional<DisciplineBlocks> readById(long id) {
        return disciplineBlockRepository.findById(id);
    }

    @Override
    public DisciplineBlocks update(long id, DisciplineBlocks disciplineBlocks) {
        disciplineBlocks.setId(id);
        return disciplineBlockRepository.save(disciplineBlocks);
    }

    @Override
    public void delete(long id) {
        disciplineBlockRepository.deleteById(id);
    }

    @Override
    public List<DisciplineBlocks> getAll() {
        return disciplineBlockRepository.findAll();
    }

    @Override
    public Page<DisciplineBlocks> getAll(Pageable pageable) {
        return disciplineBlockRepository.findAll(pageable);
    }

}

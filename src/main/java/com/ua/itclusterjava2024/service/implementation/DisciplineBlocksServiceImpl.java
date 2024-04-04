package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.DisciplineBlocks;
import com.ua.itclusterjava2024.repository.DisciplineBlocksRepository;
import com.ua.itclusterjava2024.service.interfaces.DisciplineBlocksService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplineBlocksServiceImpl implements DisciplineBlocksService {

    private final DisciplineBlocksRepository disciplineBlocksRepository;

    public DisciplineBlocksServiceImpl(DisciplineBlocksRepository disciplineBlocksRepository) {
        this.disciplineBlocksRepository = disciplineBlocksRepository;
    }

    @Override
    public DisciplineBlocks create(DisciplineBlocks disciplineBlocks) {
        return disciplineBlocksRepository.save(disciplineBlocks);
    }

    @Override
    public Optional<DisciplineBlocks> readById(long id) {
        return disciplineBlocksRepository.findById(id);
    }

    @Override
    public DisciplineBlocks update(long id, DisciplineBlocks disciplineBlocks) {
        disciplineBlocks.setId(id);
        return disciplineBlocksRepository.save(disciplineBlocks);
    }

    @Override
    public void delete(long id) {
        disciplineBlocksRepository.deleteById(id);
    }

    @Override
    public List<DisciplineBlocks> getAll() {
        return disciplineBlocksRepository.findAll();
    }

    @Override
    public Page<DisciplineBlocks> getAll(Pageable pageable) {
        return disciplineBlocksRepository.findAll(pageable);
    }

}

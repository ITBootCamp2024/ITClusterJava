package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Role;
import com.ua.itclusterjava2024.repository.RoleRepository;
import com.ua.itclusterjava2024.service.interfaces.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> readById(long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role update(long id, Role role) {
        role.setId(id);
        return roleRepository.save(role);
    }

    @Override
    public void delete(long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Page<Role> getAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }
}

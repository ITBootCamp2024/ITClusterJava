package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.User;
import com.ua.itclusterjava2024.service.interfaces.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User create(User specialty) {
        return null;
    }

    @Override
    public Optional<User> readById(long id) {
        return Optional.empty();
    }

    @Override
    public User update(long id, User user) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return null;
    }
}

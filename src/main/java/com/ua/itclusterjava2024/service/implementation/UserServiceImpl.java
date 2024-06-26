package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.User;
import com.ua.itclusterjava2024.exceptions.EmailAlreadyExistsException;
import com.ua.itclusterjava2024.repository.UserRepository;
import com.ua.itclusterjava2024.service.interfaces.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        }
        return userRepository.save(user);
    }


    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " does not exist"));
    }

    // Get user by email
    public UserDetailsService userDetailsService() {
        return this::getByEmail;
    }


    @Override
    public Optional<User> readById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User update(long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}

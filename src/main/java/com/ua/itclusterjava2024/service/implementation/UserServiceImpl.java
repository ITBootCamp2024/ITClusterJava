package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.User;
import com.ua.itclusterjava2024.repository.RoleRepository;
import com.ua.itclusterjava2024.repository.UserRepository;
import com.ua.itclusterjava2024.service.interfaces.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    @Override
    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            // Must be custom exception
            throw new RuntimeException("Registration failed: email already exists");
        }
        return userRepository.save(user);
    }


    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    // Get user by email
    public UserDetailsService userDetailsService() {
        return this::getByEmail;
    }

    // Get current user
    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByEmail(email);
    }


    // Видача прав адміністратора для поточного користувача (для тестування)
    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(roleRepository.findByName("admin")
                .orElseThrow(() -> new RuntimeException("Роль не знайдена")));
        userRepository.save(user);
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

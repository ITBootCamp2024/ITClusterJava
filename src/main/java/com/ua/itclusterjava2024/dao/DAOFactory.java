package com.ua.itclusterjava2024.dao;

import com.ua.itclusterjava2024.dao.EntityDAO.TeacherDAO;
import com.ua.itclusterjava2024.dao.EntityDAOImpl.TeacherDAOImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DAOFactory {
    DAOConfig config;
    @Bean
    public TeacherDAO getUserDAOInstance(DAOConfig config) {
        return new TeacherDAOImpl(config);
    }
}

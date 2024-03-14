package com.ua.itclusterjava2024.dao.EntityDAOImpl;

import com.ua.itclusterjava2024.dao.ConnectionManager;
import com.ua.itclusterjava2024.dao.DAOConfig;
import com.ua.itclusterjava2024.dao.EntityDAO.TeacherDAO;
import com.ua.itclusterjava2024.entity.Teacher;
import com.ua.itclusterjava2024.service.TeacherService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements TeacherDAO {
    ConnectionManager connectionManager;
    private static final String FIND_ALL_TEACHERS = "SELECT * FROM teachers";

    public TeacherDAOImpl(DAOConfig config) {
        connectionManager = new ConnectionManager(config);
    }

    @Override
    public long add(Teacher teacher) {
        return 0;
    }

    @Override
    public void update(Teacher teacher) {

    }

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection con = connectionManager.getConnection()) {
            try (Statement st = con.createStatement()) {
                try (ResultSet rs = st.executeQuery(FIND_ALL_TEACHERS)) {
                    while (rs.next()) {
                        teachers.add(mapTeachers(rs));
                    }
                    return teachers;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Teacher mapTeachers(ResultSet rs) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(rs.getLong("id"));
        return teacher;
    }

    @Override
    public void delete(long id) {

    }
}

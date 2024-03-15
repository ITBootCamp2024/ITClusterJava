package com.ua.itclusterjava2024.dao.EntityDAOImpl;

import com.ua.itclusterjava2024.dao.ConnectionManager;
import com.ua.itclusterjava2024.dao.DAOConfig;
import com.ua.itclusterjava2024.dao.EntityDAO.TeacherDAO;
import com.ua.itclusterjava2024.entity.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements TeacherDAO {
    ConnectionManager connectionManager;
    private static final String FIND_ALL_TEACHERS = "SELECT * FROM teachers";
    private static final String FIND_BY_ID = "SELECT * FROM teachers WHERE id = ?";
    private static final String DELETE = "DELETE * FROM teachers WHERE id = ?";
    private static final String ADD = "INSERT INTO teachers (name, role, status, email, details) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE teachers SET name = ?, role = ?, status = ?, email = ?, details = ? WHERE id = ?";

    public TeacherDAOImpl(DAOConfig config) {
        connectionManager = new ConnectionManager(config);
    }

    @Override
    public Teacher add(Teacher teacher) {
        try (Connection con = connectionManager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS)) {
                int k = 0;
                ps.setString(++k, teacher.getName());
                ps.setString(++k, teacher.getRole());
                ps.setString(++k, teacher.getStatus());
                ps.setString(++k, teacher.getEmail());
                ps.setString(++k, teacher.getDetails());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        teacher.setId(keys.getLong(1));
                    }
                }
            }
            return teacher;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Teacher update(Teacher teacher) {
        try (Connection con = connectionManager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(UPDATE)) {
                int k = 0;
                ps.setString(++k, teacher.getName());
                ps.setString(++k, teacher.getRole());
                ps.setString(++k, teacher.getStatus());
                ps.setString(++k, teacher.getEmail());
                ps.setString(++k, teacher.getDetails());
                ps.setLong(++k, teacher.getId());
                ps.executeUpdate();
            }
            return findById(teacher.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        teacher.setName(rs.getString("name"));
        teacher.setRole(rs.getString("role"));
        teacher.setStatus(rs.getString("status"));
        teacher.setEmail(rs.getString("email"));
        teacher.setDetails(rs.getString("details"));
        return teacher;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Teacher findById(long id) {
        Teacher teacher = new Teacher();
        try (Connection con = connectionManager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(FIND_BY_ID)) {
                int k = 0;
                ps.setLong(++k, id);
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        teacher = mapTeachers(resultSet);
                    }
                    return teacher;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.ua.itclusterjava2024.service.interfaces;

import com.ua.itclusterjava2024.entity.Teachers;
import com.ua.itclusterjava2024.service.interfaces.Service;

import java.util.List;

public interface TeachersService extends Service<Teachers> {
    void setVerified(Long teacher_id, Boolean verified);
}

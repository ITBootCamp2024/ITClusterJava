package com.ua.itclusterjava2024.service.interfaces;

import com.ua.itclusterjava2024.dto.SpecialistDTO;
import com.ua.itclusterjava2024.entity.Specialist;

import java.util.List;

public interface SpecialistService extends  Service<Specialist>{
    void setVerified(Long specialistId, Boolean verified);
}

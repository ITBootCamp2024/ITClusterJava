package com.ua.itclusterjava2024.service.interfaces;

import com.ua.itclusterjava2024.entity.Specialist;

public interface SpecialistService extends  Service<Specialist>{
    void setVerified(Long specialistId, Boolean verified);
}

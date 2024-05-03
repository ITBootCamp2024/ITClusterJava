package com.ua.itclusterjava2024.service.interfaces;

import com.ua.itclusterjava2024.entity.BaseInformationSyllabus;

import java.util.List;
import java.util.Optional;

public interface SyllabusesBaseInfoService extends Service<BaseInformationSyllabus>{
    public Optional<BaseInformationSyllabus> getBaseInfoBySyllabus(long syllabusId);

}

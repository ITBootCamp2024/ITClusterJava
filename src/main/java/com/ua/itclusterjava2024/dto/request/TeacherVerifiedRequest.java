package com.ua.itclusterjava2024.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherVerifiedRequest {
    private Long teacher_id;
    private Boolean verified;
}

package com.ua.itclusterjava2024.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDTO {
    @NotEmpty
    @Size(max = 100, message = "Name of course have to contain up to 100 symbols")
    private String name;

    private Integer type_id;

    private Integer numInType;

    @Size(max = 200, message = "Syllabus Link have to contain up to 200 symbols")
    @Pattern(regexp = "https?://.", message = "Incorrect format URL")
    private String syllabusLink;

    @Size(max = 200, message = "Work program link have to contain up to 200 symbols")
    private String workProgramLink;

    private Integer program_id;

    private Integer teacher_id;

    @Size(max = 100, message = "Review Link have to contain up to 100 symbols")
    private String reviewLink;

    private Integer courseStatus;
}

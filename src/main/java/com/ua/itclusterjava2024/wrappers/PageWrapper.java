package com.ua.itclusterjava2024.wrappers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ua.itclusterjava2024.dto.ServiceInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageWrapper<T> {
    private List<T> content;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("service_info")
    private ServiceInfoDTO serviceInfo;

    private long totalElements;

    public PageWrapper(List<T> content, long totalElements) {
        this.content = content;
        this.totalElements = totalElements;
    }
}


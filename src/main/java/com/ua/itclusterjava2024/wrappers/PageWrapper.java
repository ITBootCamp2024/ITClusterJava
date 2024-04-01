package com.ua.itclusterjava2024.wrappers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageWrapper<T> {

    private List<T> content;
    private int pageNumber;
    private long totalElements;
}


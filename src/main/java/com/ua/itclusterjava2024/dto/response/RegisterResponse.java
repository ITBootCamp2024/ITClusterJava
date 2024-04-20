package com.ua.itclusterjava2024.dto.response;

import lombok.Data;


@Data
public class RegisterResponse {

    Long id;

    String firstName;

    String lastName;

    String parentName;

    String email;

    String phone;

}
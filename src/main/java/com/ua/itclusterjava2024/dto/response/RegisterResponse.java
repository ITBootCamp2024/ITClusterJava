package com.ua.itclusterjava2024.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ua.itclusterjava2024.entity.User;
import lombok.Data;


@Data
public class RegisterResponse {

    @JsonProperty(required = true)
    Long id;

    @JsonProperty(value = "first_name", required = true)
    String firstName;

    @JsonProperty(value = "last_name", required = true)
    String lastName;

    @JsonProperty(value = "parent_name")
    String parentName;

    @JsonProperty(required = true)
    String email;

    String phone;

    public RegisterResponse(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.parentName = user.getParentName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }

}
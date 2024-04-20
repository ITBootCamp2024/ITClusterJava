package com.ua.itclusterjava2024.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    // TODO: Додати правильні анотації для валідації полів

    @NotBlank(message = "Email адреса не може бути порожньою")
    @Email(message = "Email адреса повинна бути дійсною")
    @Size(max = 255, message = "Довжина email адреси повинна бути не більше 255 символів")
    @Email
    private String email;

    @NotBlank(message = "Пароль не може бути порожнім")
    @Size(max = 255, message = "Довжина пароля повинна бути не більше 255 символів")
    private String password;

    @NotBlank(message = "Ім'я користувача не може бути порожнім")
    @Size(min = 5, max = 50, message = "Ім'я користувача повинно містити від 5 до 50 символів")
    private String first_name;

    @NotBlank(message = "Прізвище користувача не може бути порожнім")
    @Size(min = 5, max = 50, message = "Прізвище користувача повинно містити від 5 до 50 символів")
    @JsonProperty("last_name")
    private String last_name;

    @Size(min = 5, max = 50, message = "По батькові користувача повинно містити від 5 до 50 символів")
    @JsonProperty("parent_name")
    private String parent_name;

    @Size(max = 50, message = "Довжина телефону повинна бути не більше 50 символів")
    private String phone;
}

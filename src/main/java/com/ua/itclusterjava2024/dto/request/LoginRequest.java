package com.ua.itclusterjava2024.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Email адреса не може бути порожньою")
    @Email(message = "Email адреса повинна бути дійсною")
    @Size(max = 255, message = "Довжина email адреси повинна бути не більше 255 символів")
    private String email;

    @NotBlank(message = "Пароль не може бути порожнім")
    @Size(max = 255, message = "Довжина пароля повинна бути не більше 255 символів")
    private String password;
}

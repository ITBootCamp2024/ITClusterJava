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
public class ChangePasswordRequest {
    @NotBlank(message = "Старий пароль не може бути порожнім")
    @Size(max = 255, message = "Довжина пароля повинна бути не більше 255 символів")
    private String old_password;

    @NotBlank(message = "Новий пароль не може бути порожнім")
    @Size(max = 255, message = "Довжина пароля повинна бути не більше 255 символів")
    private String new_password;
}

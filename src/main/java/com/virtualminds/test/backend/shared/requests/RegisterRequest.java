package com.virtualminds.test.backend.shared.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {


    @NotNull(message = "Le nom d'utilisateur est obligatoire")
    @NotBlank(message = "Le nom d'utilisateur ne peut pas être vide")
    private String username;

    @NotNull(message = "Le mot de passe est obligatoire")
    @NotBlank(message = "Le mot de passe ne peut pas être vide")
    @Size(min = 8 , message = "Le mot de passe doit avoir au moins 8 caractères.")
    private String password;

    @NotNull(message = "La confimration du mot de passe est obligatoire")
    @NotBlank(message = "La confimration du mot de passe ne peut pas être vide")
    @Size(min = 8 , message = "La confimration du mot de passe doit avoir au moins 8 caractères.")
    private String repassword;
}

package com.danielks.headspaceprojectweb.HsWeb.models;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record AddressDTO (
        UUID id,
        UUID userId,
        @NotBlank(message = "O número não pode estar em branco!") int number,
        @NotBlank(message = "O bairro não pode estar em branco!") String district,
        @NotBlank(message = "A rua não pode estar em branco!") String street,
        @NotBlank(message = "O nome não pode estar em branco!") String cep
    ){
}

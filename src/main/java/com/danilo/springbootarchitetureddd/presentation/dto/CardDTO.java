package com.danilo.springbootarchitetureddd.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CardDTO {

    @NotNull(message = "Number cannot be empty.")
    @NotBlank(message = "Number cannot be empty.")
    @Size(min = 19,max = 19,message = "Number must contain 19 characters.")
    private String number;

    @NotNull(message = "Expiration Date cannot be empty.")
    private LocalDate expirationDate;

}

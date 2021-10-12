package com.example.entrevueSpringBoot.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ActeurDto implements Serializable {
    private Long id;
    @NotNull
    private String nom;
    @NotNull
    private String prenom;
    private Long filmId;
}

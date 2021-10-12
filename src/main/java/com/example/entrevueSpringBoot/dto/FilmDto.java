package com.example.entrevueSpringBoot.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class FilmDto implements Serializable {
    private Long id;
    private String titre;
    private String description;
    private List<ActeurDto> acteurs = new ArrayList<>();
}

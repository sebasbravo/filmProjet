package com.example.entrevueSpringBoot.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "FILM")
@Getter
@Setter
@EqualsAndHashCode
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column (name = "titre", nullable = false)
    private String titre;

    @Column (name = "description")
    private String description;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "film",fetch = FetchType.LAZY,cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Acteur> acteurs = new ArrayList<>();
}

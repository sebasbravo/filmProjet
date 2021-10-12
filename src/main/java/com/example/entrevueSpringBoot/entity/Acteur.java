package com.example.entrevueSpringBoot.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity(name = "ACTEUR")
@Getter
@Setter
@EqualsAndHashCode
public class Acteur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name="id", updatable=false, nullable=false)
    private Long id;

    @NotNull
    @Column (name="nom", nullable=false)
    private String nom;

    @NotNull
    @Column (name="prenom", nullable=false)
    private String prenom;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="film_id")
    private Film film;
}

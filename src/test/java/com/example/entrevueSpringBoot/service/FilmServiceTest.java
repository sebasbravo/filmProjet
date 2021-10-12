package com.example.entrevueSpringBoot.service;

import com.example.entrevueSpringBoot.dto.ActeurDto;
import com.example.entrevueSpringBoot.dto.FilmDto;
import com.example.entrevueSpringBoot.dto.mapper.ActeurMapper;
import com.example.entrevueSpringBoot.dto.mapper.FilmMapper;
import com.example.entrevueSpringBoot.repository.ActeurRepository;
import com.example.entrevueSpringBoot.repository.FilmRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

class FilmServiceTest {

    @Mock
    private FilmService filmService;
    @Mock
    private FilmRepository filmRepository;
    @Mock
    private ActeurRepository acteurRepository;
    @Spy
    private FilmMapper filmMapper;
    @Spy
    private ActeurMapper acteurMapper;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        filmMapper = Mappers.getMapper(FilmMapper.class);
        acteurMapper = Mappers.getMapper(ActeurMapper.class);
        ReflectionTestUtils.setField(filmMapper, "acteurMapper", acteurMapper);
        filmService = new FilmService(filmRepository, filmMapper, acteurMapper, acteurRepository);
    }

    @Test
    @DisplayName("Return Film exists by id ")
    void getFilmById_ShouldReturn_1_Film() {
        //given
        FilmDto filmDto = new FilmDto();
        filmDto.setId(1L);
        filmDto.setTitre("Batman");
        filmDto.setDescription("Action");

        ActeurDto acteur1 = new ActeurDto();
        acteur1.setId(1L);
        acteur1.setNom("Keaton");
        acteur1.setPrenom("Michael");
        acteur1.setFilmId(filmDto.getId());

        ActeurDto acteur2 = new ActeurDto();
        acteur2.setId(2L);
        acteur2.setNom("Nicholson");
        acteur2.setPrenom("Jack");
        acteur2.setFilmId(filmDto.getId());

        List<ActeurDto> acteurs = new ArrayList<>();
        acteurs.add(acteur1);
        acteurs.add(acteur2);

        filmDto.setActeurs(acteurs);

        when(filmRepository.findById(filmDto.getId())).thenReturn(Optional.of(filmMapper.toEntity(filmDto)));

        //When
        FilmDto film = filmService.getFilmById(filmDto.getId());

        //Then
        Assertions.assertNotNull(film);
        Assertions.assertEquals(film, filmDto);

    }

    @Test
    @DisplayName("Save one Film")
    void saveFilm_ShouldPersist_1_Film() {
        //given
        FilmDto filmDto = new FilmDto();
        filmDto.setId(1L);
        filmDto.setTitre("Batman");
        filmDto.setDescription("Action");

        ActeurDto acteur1 = new ActeurDto();
        acteur1.setId(1L);
        acteur1.setNom("Keaton");
        acteur1.setPrenom("Michael");
        acteur1.setFilmId(filmDto.getId());

        ActeurDto acteur2 = new ActeurDto();
        acteur2.setId(2L);
        acteur2.setNom("Nicholson");
        acteur2.setPrenom("Jack");
        acteur2.setFilmId(filmDto.getId());

        List<ActeurDto> acteurs = new ArrayList<>();
        acteurs.add(acteur1);
        acteurs.add(acteur2);

        filmDto.setActeurs(acteurs);

        when(filmRepository.save(filmMapper.toEntity(filmDto))).thenReturn(filmMapper.toEntity(filmDto));

        //When
        FilmDto film = filmService.save(filmDto);

        //Then
        Assertions.assertNotNull(film);
        List<ActeurDto> createdActeurs = film.getActeurs();
        Assertions.assertEquals(2, createdActeurs.size());
        Assertions.assertEquals(film, filmDto);
        createdActeurs.forEach( acteurDto -> Assertions.assertEquals(acteurDto.getFilmId(), film.getId()));
    }

}

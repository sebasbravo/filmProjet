package com.example.entrevueSpringBoot.service;

import com.example.entrevueSpringBoot.dto.FilmDto;
import com.example.entrevueSpringBoot.dto.mapper.ActeurMapper;
import com.example.entrevueSpringBoot.dto.mapper.FilmMapper;
import com.example.entrevueSpringBoot.entity.Acteur;
import com.example.entrevueSpringBoot.entity.Film;
import com.example.entrevueSpringBoot.repository.ActeurRepository;
import com.example.entrevueSpringBoot.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class FilmService {

    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;
    private final ActeurMapper acteurMapper;
    private final ActeurRepository acteurRepository;

    public FilmDto getFilmById(Long id){
        return filmRepository.findById(id)
                .map(filmMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("Film not available for Id: "+id));
    }

    @Transactional
    public FilmDto save(final FilmDto filmDto){
        final Film film = filmRepository.save(filmMapper.toEntity(filmDto));
        final List<Acteur> acteurs = new ArrayList<>();

        filmDto.getActeurs().forEach(acteurDto -> {
            final Acteur acteur = acteurMapper.toEntity(acteurDto);
            acteur.setFilm(film);
            acteurs.add(acteur);
        });
        film.setActeurs(acteurs);
        acteurRepository.saveAll(acteurs);

        return filmMapper.toDto(film);
    }
}

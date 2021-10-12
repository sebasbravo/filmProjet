package com.example.entrevueSpringBoot.controller;

import com.example.entrevueSpringBoot.dto.FilmDto;
import com.example.entrevueSpringBoot.service.FilmService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping(FilmController.BASE_URL)
@AllArgsConstructor
public class FilmController {

    static final String BASE_URL = "/api/films";
    private final FilmService filmService;

    @GetMapping("/{filmId}")
    public ResponseEntity<FilmDto> getFilm(@PathVariable Long filmId){
        return ResponseEntity.ok(filmService.getFilmById(filmId));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<FilmDto> save(@RequestBody FilmDto filmDto){
        return new ResponseEntity<>(filmService.save(filmDto), HttpStatus.CREATED);
    }
}

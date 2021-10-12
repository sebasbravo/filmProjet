package com.example.entrevueSpringBoot.dto.mapper;

import com.example.entrevueSpringBoot.dto.FilmDto;
import com.example.entrevueSpringBoot.entity.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ActeurMapper.class})
public interface FilmMapper extends EntityMapper<FilmDto, Film> {

    @Mapping(source = "acteurs", target = "acteurs")
    FilmDto toDto(final Film film);

    Film toEntity(final FilmDto filmDto);

}

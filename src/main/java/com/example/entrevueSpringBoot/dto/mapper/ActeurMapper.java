package com.example.entrevueSpringBoot.dto.mapper;

import com.example.entrevueSpringBoot.dto.ActeurDto;
import com.example.entrevueSpringBoot.entity.Acteur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {FilmMapper.class})
public interface ActeurMapper extends EntityMapper<ActeurDto, Acteur>{

    @Mapping(source = "film.id", target = "filmId")
    ActeurDto toDto(final Acteur acteur);

    List<ActeurDto> toDto(final List<Acteur> acteurs);

    @Mapping(source = "filmId", target = "film.id")
    Acteur toEntity(final ActeurDto acteurDto);

    List<Acteur> toEntity(final List<ActeurDto> acteursDto);

}

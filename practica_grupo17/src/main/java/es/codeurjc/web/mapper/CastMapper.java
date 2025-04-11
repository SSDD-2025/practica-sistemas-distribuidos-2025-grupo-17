package es.codeurjc.web.mapper;

import es.codeurjc.web.dto.cast.CastDTO;
import es.codeurjc.web.dto.cast.CastBasicDTO;
import es.codeurjc.web.dto.cast.CreateCastDTO;
import es.codeurjc.web.entities.Cast;

import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CastMapper {
    CastDTO toDTO(Cast cast);

    List<CastDTO> toDTOs(Collection<Cast> casts);

    Cast toDomain(CastDTO castDTO);

    List<CastDTO> toDTO(List<Cast> all);

    Cast toDomain(CreateCastDTO cast);

    CreateCastDTO toCreateCastRequest(Cast cast);

    CastBasicDTO toCastBasicDTO(Cast updatedCast);
}

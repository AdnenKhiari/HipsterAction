package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Acteur;
import com.mycompany.myapp.service.dto.ActeurDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Acteur} and its DTO {@link ActeurDTO}.
 */
@Mapper(componentModel = "spring")
public interface ActeurMapper extends EntityMapper<ActeurDTO, Acteur> {}

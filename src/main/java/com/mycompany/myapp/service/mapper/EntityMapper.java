package com.mycompany.myapp.service.mapper;

import java.time.Instant;
import java.util.List;
import javax.persistence.Column;
import org.mapstruct.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */

public interface EntityMapper<D, E> {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    E toEntity(D dto);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    D toDto(E entity);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    List<E> toEntity(List<D> dtoList);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    List<D> toDto(List<E> entityList);

    @Named("partialUpdate")
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget E entity, D dto);
}

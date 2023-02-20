package com.mycompany.myapp.service.mapper;

/*
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

@MapperConfig(
    mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG
)*/
public interface CentralConfig {
    // Not intended to be generated, but to carry inheritable mapping annotations:
    /* @Mapping(target = "createdBy", ignore = true)
    Object anyDtoToEntity(Object dto);*/
}

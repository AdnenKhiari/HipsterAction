package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Acteur;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Acteur entity.
 */
@SuppressWarnings("unused")
@Repository
@JaversSpringDataAuditable
public interface ActeurRepository extends JpaRepository<Acteur, Long>, JpaSpecificationExecutor<Acteur> {}

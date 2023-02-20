package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.Acteur;
import com.mycompany.myapp.repository.ActeurRepository;
import com.mycompany.myapp.service.criteria.ActeurCriteria;
import com.mycompany.myapp.service.dto.ActeurDTO;
import com.mycompany.myapp.service.mapper.ActeurMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Acteur} entities in the database.
 * The main input is a {@link ActeurCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ActeurDTO} or a {@link Page} of {@link ActeurDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ActeurQueryService extends QueryService<Acteur> {

    private final Logger log = LoggerFactory.getLogger(ActeurQueryService.class);

    private final ActeurRepository acteurRepository;

    private final ActeurMapper acteurMapper;

    public ActeurQueryService(ActeurRepository acteurRepository, ActeurMapper acteurMapper) {
        this.acteurRepository = acteurRepository;
        this.acteurMapper = acteurMapper;
    }

    /**
     * Return a {@link List} of {@link ActeurDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ActeurDTO> findByCriteria(ActeurCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Acteur> specification = createSpecification(criteria);
        return acteurMapper.toDto(acteurRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ActeurDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ActeurDTO> findByCriteria(ActeurCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Acteur> specification = createSpecification(criteria);
        return acteurRepository.findAll(specification, page).map(acteurMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ActeurCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Acteur> specification = createSpecification(criteria);
        return acteurRepository.count(specification);
    }

    /**
     * Function to convert {@link ActeurCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Acteur> createSpecification(ActeurCriteria criteria) {
        Specification<Acteur> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Acteur_.id));
            }
            if (criteria.getVerifie() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifie(), Acteur_.verifie));
            }
            if (criteria.getLoginExpire() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLoginExpire(), Acteur_.loginExpire));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Acteur_.email));
            }
            if (criteria.getNom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNom(), Acteur_.nom));
            }
            if (criteria.getNomResponsable() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNomResponsable(), Acteur_.nomResponsable));
            }
            if (criteria.getNumTel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumTel(), Acteur_.numTel));
            }
            if (criteria.getAddresse() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddresse(), Acteur_.addresse));
            }
            if (criteria.getMatriculeFiscale() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMatriculeFiscale(), Acteur_.matriculeFiscale));
            }
            if (criteria.getUrlRegistreCommerce() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUrlRegistreCommerce(), Acteur_.urlRegistreCommerce));
            }
            if (criteria.getUrlProfileImg() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUrlProfileImg(), Acteur_.urlProfileImg));
            }
            if (criteria.getUrlResponsableImg() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUrlResponsableImg(), Acteur_.urlResponsableImg));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), Acteur_.createdBy));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), Acteur_.createdDate));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), Acteur_.lastModifiedBy));
            }
            if (criteria.getLastModifiedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedDate(), Acteur_.lastModifiedDate));
            }
        }
        return specification;
    }
}

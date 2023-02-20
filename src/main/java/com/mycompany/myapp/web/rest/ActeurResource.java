package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.ActeurRepository;
import com.mycompany.myapp.service.ActeurQueryService;
import com.mycompany.myapp.service.ActeurService;
import com.mycompany.myapp.service.criteria.ActeurCriteria;
import com.mycompany.myapp.service.dto.ActeurDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Acteur}.
 */
@RestController
@RequestMapping("/api")
public class ActeurResource {

    private final Logger log = LoggerFactory.getLogger(ActeurResource.class);

    private static final String ENTITY_NAME = "acteur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ActeurService acteurService;

    private final ActeurRepository acteurRepository;

    private final ActeurQueryService acteurQueryService;

    public ActeurResource(ActeurService acteurService, ActeurRepository acteurRepository, ActeurQueryService acteurQueryService) {
        this.acteurService = acteurService;
        this.acteurRepository = acteurRepository;
        this.acteurQueryService = acteurQueryService;
    }

    /**
     * {@code POST  /acteurs} : Create a new acteur.
     *
     * @param acteurDTO the acteurDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new acteurDTO, or with status {@code 400 (Bad Request)} if the acteur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/acteurs")
    public ResponseEntity<ActeurDTO> createActeur(@Valid @RequestBody ActeurDTO acteurDTO) throws URISyntaxException {
        log.debug("REST request to save Acteur : {}", acteurDTO);
        if (acteurDTO.getId() != null) {
            throw new BadRequestAlertException("A new acteur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActeurDTO result = acteurService.save(acteurDTO);
        return ResponseEntity
            .created(new URI("/api/acteurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /acteurs/:id} : Updates an existing acteur.
     *
     * @param id the id of the acteurDTO to save.
     * @param acteurDTO the acteurDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated acteurDTO,
     * or with status {@code 400 (Bad Request)} if the acteurDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the acteurDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/acteurs/{id}")
    public ResponseEntity<ActeurDTO> updateActeur(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ActeurDTO acteurDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Acteur : {}, {}", id, acteurDTO);
        if (acteurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, acteurDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!acteurRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ActeurDTO result = acteurService.update(acteurDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, acteurDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /acteurs/:id} : Partial updates given fields of an existing acteur, field will ignore if it is null
     *
     * @param id the id of the acteurDTO to save.
     * @param acteurDTO the acteurDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated acteurDTO,
     * or with status {@code 400 (Bad Request)} if the acteurDTO is not valid,
     * or with status {@code 404 (Not Found)} if the acteurDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the acteurDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/acteurs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ActeurDTO> partialUpdateActeur(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ActeurDTO acteurDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Acteur partially : {}, {}", id, acteurDTO);
        if (acteurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, acteurDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!acteurRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ActeurDTO> result = acteurService.partialUpdate(acteurDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, acteurDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /acteurs} : get all the acteurs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of acteurs in body.
     */
    @GetMapping("/acteurs")
    public ResponseEntity<List<ActeurDTO>> getAllActeurs(
        ActeurCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Acteurs by criteria: {}", criteria);
        Page<ActeurDTO> page = acteurQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /acteurs/count} : count all the acteurs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/acteurs/count")
    public ResponseEntity<Long> countActeurs(ActeurCriteria criteria) {
        log.debug("REST request to count Acteurs by criteria: {}", criteria);
        return ResponseEntity.ok().body(acteurQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /acteurs/:id} : get the "id" acteur.
     *
     * @param id the id of the acteurDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the acteurDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/acteurs/{id}")
    public ResponseEntity<ActeurDTO> getActeur(@PathVariable Long id) {
        log.debug("REST request to get Acteur : {}", id);
        Optional<ActeurDTO> acteurDTO = acteurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(acteurDTO);
    }

    /**
     * {@code DELETE  /acteurs/:id} : delete the "id" acteur.
     *
     * @param id the id of the acteurDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/acteurs/{id}")
    public ResponseEntity<Void> deleteActeur(@PathVariable Long id) {
        log.debug("REST request to delete Acteur : {}", id);
        acteurService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

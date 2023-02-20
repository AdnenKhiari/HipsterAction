package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ActeurDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Acteur}.
 */
public interface ActeurService {
    /**
     * Save a acteur.
     *
     * @param acteurDTO the entity to save.
     * @return the persisted entity.
     */
    ActeurDTO save(ActeurDTO acteurDTO);

    /**
     * Updates a acteur.
     *
     * @param acteurDTO the entity to update.
     * @return the persisted entity.
     */
    ActeurDTO update(ActeurDTO acteurDTO);

    /**
     * Partially updates a acteur.
     *
     * @param acteurDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ActeurDTO> partialUpdate(ActeurDTO acteurDTO);

    /**
     * Get all the acteurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ActeurDTO> findAll(Pageable pageable);

    /**
     * Get the "id" acteur.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ActeurDTO> findOne(Long id);

    /**
     * Delete the "id" acteur.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

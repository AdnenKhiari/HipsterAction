package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Acteur;
import com.mycompany.myapp.repository.ActeurRepository;
import com.mycompany.myapp.service.ActeurService;
import com.mycompany.myapp.service.dto.ActeurDTO;
import com.mycompany.myapp.service.mapper.ActeurMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Acteur}.
 */
@Service
@Transactional
public class ActeurServiceImpl implements ActeurService {

    private final Logger log = LoggerFactory.getLogger(ActeurServiceImpl.class);

    private final ActeurRepository acteurRepository;

    private final ActeurMapper acteurMapper;

    public ActeurServiceImpl(ActeurRepository acteurRepository, ActeurMapper acteurMapper) {
        this.acteurRepository = acteurRepository;
        this.acteurMapper = acteurMapper;
    }

    @Override
    public ActeurDTO save(ActeurDTO acteurDTO) {
        log.debug("Request to save Acteur : {}", acteurDTO);
        Acteur acteur = acteurMapper.toEntity(acteurDTO);
        acteur = acteurRepository.save(acteur);
        return acteurMapper.toDto(acteur);
    }

    @Override
    public ActeurDTO update(ActeurDTO acteurDTO) {
        log.debug("Request to update Acteur : {}", acteurDTO);
        Acteur acteur = acteurMapper.toEntity(acteurDTO);
        acteur = acteurRepository.save(acteur);
        return acteurMapper.toDto(acteur);
    }

    @Override
    public Optional<ActeurDTO> partialUpdate(ActeurDTO acteurDTO) {
        log.debug("Request to partially update Acteur : {}", acteurDTO);

        return acteurRepository
            .findById(acteurDTO.getId())
            .map(existingActeur -> {
                acteurMapper.partialUpdate(existingActeur, acteurDTO);

                return existingActeur;
            })
            .map(acteurRepository::save)
            .map(acteurMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ActeurDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Acteurs");
        return acteurRepository.findAll(pageable).map(acteurMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ActeurDTO> findOne(Long id) {
        log.debug("Request to get Acteur : {}", id);
        return acteurRepository.findById(id).map(acteurMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Acteur : {}", id);
        acteurRepository.deleteById(id);
    }
}

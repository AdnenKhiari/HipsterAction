package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Acteur;
import com.mycompany.myapp.repository.ActeurRepository;
import com.mycompany.myapp.service.criteria.ActeurCriteria;
import com.mycompany.myapp.service.dto.ActeurDTO;
import com.mycompany.myapp.service.mapper.ActeurMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ActeurResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ActeurResourceIT {

    private static final Boolean DEFAULT_VERIFIE = false;
    private static final Boolean UPDATED_VERIFIE = true;

    private static final Instant DEFAULT_LOGIN_EXPIRE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LOGIN_EXPIRE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_RESPONSABLE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_RESPONSABLE = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_TEL = "AAAAAAAAAA";
    private static final String UPDATED_NUM_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_MATRICULE_FISCALE = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULE_FISCALE = "BBBBBBBBBB";

    private static final String DEFAULT_URL_REGISTRE_COMMERCE = "AAAAAAAAAA";
    private static final String UPDATED_URL_REGISTRE_COMMERCE = "BBBBBBBBBB";

    private static final String DEFAULT_URL_PROFILE_IMG = "AAAAAAAAAA";
    private static final String UPDATED_URL_PROFILE_IMG = "BBBBBBBBBB";

    private static final String DEFAULT_URL_RESPONSABLE_IMG = "AAAAAAAAAA";
    private static final String UPDATED_URL_RESPONSABLE_IMG = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/acteurs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ActeurRepository acteurRepository;

    @Autowired
    private ActeurMapper acteurMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restActeurMockMvc;

    private Acteur acteur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Acteur createEntity(EntityManager em) {
        Acteur acteur = new Acteur()
            .verifie(DEFAULT_VERIFIE)
            .loginExpire(DEFAULT_LOGIN_EXPIRE)
            .email(DEFAULT_EMAIL)
            .nom(DEFAULT_NOM)
            .nomResponsable(DEFAULT_NOM_RESPONSABLE)
            .numTel(DEFAULT_NUM_TEL)
            .addresse(DEFAULT_ADDRESSE)
            .matriculeFiscale(DEFAULT_MATRICULE_FISCALE)
            .urlRegistreCommerce(DEFAULT_URL_REGISTRE_COMMERCE)
            .urlProfileImg(DEFAULT_URL_PROFILE_IMG)
            .urlResponsableImg(DEFAULT_URL_RESPONSABLE_IMG);
        return acteur;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Acteur createUpdatedEntity(EntityManager em) {
        Acteur acteur = new Acteur()
            .verifie(UPDATED_VERIFIE)
            .loginExpire(UPDATED_LOGIN_EXPIRE)
            .email(UPDATED_EMAIL)
            .nom(UPDATED_NOM)
            .nomResponsable(UPDATED_NOM_RESPONSABLE)
            .numTel(UPDATED_NUM_TEL)
            .addresse(UPDATED_ADDRESSE)
            .matriculeFiscale(UPDATED_MATRICULE_FISCALE)
            .urlRegistreCommerce(UPDATED_URL_REGISTRE_COMMERCE)
            .urlProfileImg(UPDATED_URL_PROFILE_IMG)
            .urlResponsableImg(UPDATED_URL_RESPONSABLE_IMG);
        return acteur;
    }

    @BeforeEach
    public void initTest() {
        acteur = createEntity(em);
    }

    @Test
    @Transactional
    void createActeur() throws Exception {
        int databaseSizeBeforeCreate = acteurRepository.findAll().size();
        // Create the Acteur
        ActeurDTO acteurDTO = acteurMapper.toDto(acteur);
        restActeurMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(acteurDTO)))
            .andExpect(status().isCreated());

        // Validate the Acteur in the database
        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeCreate + 1);
        Acteur testActeur = acteurList.get(acteurList.size() - 1);
        assertThat(testActeur.getVerifie()).isEqualTo(DEFAULT_VERIFIE);
        assertThat(testActeur.getLoginExpire()).isEqualTo(DEFAULT_LOGIN_EXPIRE);
        assertThat(testActeur.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testActeur.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testActeur.getNomResponsable()).isEqualTo(DEFAULT_NOM_RESPONSABLE);
        assertThat(testActeur.getNumTel()).isEqualTo(DEFAULT_NUM_TEL);
        assertThat(testActeur.getAddresse()).isEqualTo(DEFAULT_ADDRESSE);
        assertThat(testActeur.getMatriculeFiscale()).isEqualTo(DEFAULT_MATRICULE_FISCALE);
        assertThat(testActeur.getUrlRegistreCommerce()).isEqualTo(DEFAULT_URL_REGISTRE_COMMERCE);
        assertThat(testActeur.getUrlProfileImg()).isEqualTo(DEFAULT_URL_PROFILE_IMG);
        assertThat(testActeur.getUrlResponsableImg()).isEqualTo(DEFAULT_URL_RESPONSABLE_IMG);
    }

    @Test
    @Transactional
    void createActeurWithExistingId() throws Exception {
        // Create the Acteur with an existing ID
        acteur.setId(1L);
        ActeurDTO acteurDTO = acteurMapper.toDto(acteur);

        int databaseSizeBeforeCreate = acteurRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restActeurMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(acteurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Acteur in the database
        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkVerifieIsRequired() throws Exception {
        int databaseSizeBeforeTest = acteurRepository.findAll().size();
        // set the field null
        acteur.setVerifie(null);

        // Create the Acteur, which fails.
        ActeurDTO acteurDTO = acteurMapper.toDto(acteur);

        restActeurMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(acteurDTO)))
            .andExpect(status().isBadRequest());

        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLoginExpireIsRequired() throws Exception {
        int databaseSizeBeforeTest = acteurRepository.findAll().size();
        // set the field null
        acteur.setLoginExpire(null);

        // Create the Acteur, which fails.
        ActeurDTO acteurDTO = acteurMapper.toDto(acteur);

        restActeurMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(acteurDTO)))
            .andExpect(status().isBadRequest());

        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = acteurRepository.findAll().size();
        // set the field null
        acteur.setEmail(null);

        // Create the Acteur, which fails.
        ActeurDTO acteurDTO = acteurMapper.toDto(acteur);

        restActeurMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(acteurDTO)))
            .andExpect(status().isBadRequest());

        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = acteurRepository.findAll().size();
        // set the field null
        acteur.setNom(null);

        // Create the Acteur, which fails.
        ActeurDTO acteurDTO = acteurMapper.toDto(acteur);

        restActeurMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(acteurDTO)))
            .andExpect(status().isBadRequest());

        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNomResponsableIsRequired() throws Exception {
        int databaseSizeBeforeTest = acteurRepository.findAll().size();
        // set the field null
        acteur.setNomResponsable(null);

        // Create the Acteur, which fails.
        ActeurDTO acteurDTO = acteurMapper.toDto(acteur);

        restActeurMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(acteurDTO)))
            .andExpect(status().isBadRequest());

        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNumTelIsRequired() throws Exception {
        int databaseSizeBeforeTest = acteurRepository.findAll().size();
        // set the field null
        acteur.setNumTel(null);

        // Create the Acteur, which fails.
        ActeurDTO acteurDTO = acteurMapper.toDto(acteur);

        restActeurMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(acteurDTO)))
            .andExpect(status().isBadRequest());

        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAddresseIsRequired() throws Exception {
        int databaseSizeBeforeTest = acteurRepository.findAll().size();
        // set the field null
        acteur.setAddresse(null);

        // Create the Acteur, which fails.
        ActeurDTO acteurDTO = acteurMapper.toDto(acteur);

        restActeurMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(acteurDTO)))
            .andExpect(status().isBadRequest());

        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMatriculeFiscaleIsRequired() throws Exception {
        int databaseSizeBeforeTest = acteurRepository.findAll().size();
        // set the field null
        acteur.setMatriculeFiscale(null);

        // Create the Acteur, which fails.
        ActeurDTO acteurDTO = acteurMapper.toDto(acteur);

        restActeurMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(acteurDTO)))
            .andExpect(status().isBadRequest());

        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUrlRegistreCommerceIsRequired() throws Exception {
        int databaseSizeBeforeTest = acteurRepository.findAll().size();
        // set the field null
        acteur.setUrlRegistreCommerce(null);

        // Create the Acteur, which fails.
        ActeurDTO acteurDTO = acteurMapper.toDto(acteur);

        restActeurMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(acteurDTO)))
            .andExpect(status().isBadRequest());

        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUrlProfileImgIsRequired() throws Exception {
        int databaseSizeBeforeTest = acteurRepository.findAll().size();
        // set the field null
        acteur.setUrlProfileImg(null);

        // Create the Acteur, which fails.
        ActeurDTO acteurDTO = acteurMapper.toDto(acteur);

        restActeurMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(acteurDTO)))
            .andExpect(status().isBadRequest());

        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllActeurs() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList
        restActeurMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(acteur.getId().intValue())))
            .andExpect(jsonPath("$.[*].verifie").value(hasItem(DEFAULT_VERIFIE.booleanValue())))
            .andExpect(jsonPath("$.[*].loginExpire").value(hasItem(DEFAULT_LOGIN_EXPIRE.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].nomResponsable").value(hasItem(DEFAULT_NOM_RESPONSABLE)))
            .andExpect(jsonPath("$.[*].numTel").value(hasItem(DEFAULT_NUM_TEL)))
            .andExpect(jsonPath("$.[*].addresse").value(hasItem(DEFAULT_ADDRESSE)))
            .andExpect(jsonPath("$.[*].matriculeFiscale").value(hasItem(DEFAULT_MATRICULE_FISCALE)))
            .andExpect(jsonPath("$.[*].urlRegistreCommerce").value(hasItem(DEFAULT_URL_REGISTRE_COMMERCE)))
            .andExpect(jsonPath("$.[*].urlProfileImg").value(hasItem(DEFAULT_URL_PROFILE_IMG)))
            .andExpect(jsonPath("$.[*].urlResponsableImg").value(hasItem(DEFAULT_URL_RESPONSABLE_IMG)));
    }

    @Test
    @Transactional
    void getActeur() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get the acteur
        restActeurMockMvc
            .perform(get(ENTITY_API_URL_ID, acteur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(acteur.getId().intValue()))
            .andExpect(jsonPath("$.verifie").value(DEFAULT_VERIFIE.booleanValue()))
            .andExpect(jsonPath("$.loginExpire").value(DEFAULT_LOGIN_EXPIRE.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.nomResponsable").value(DEFAULT_NOM_RESPONSABLE))
            .andExpect(jsonPath("$.numTel").value(DEFAULT_NUM_TEL))
            .andExpect(jsonPath("$.addresse").value(DEFAULT_ADDRESSE))
            .andExpect(jsonPath("$.matriculeFiscale").value(DEFAULT_MATRICULE_FISCALE))
            .andExpect(jsonPath("$.urlRegistreCommerce").value(DEFAULT_URL_REGISTRE_COMMERCE))
            .andExpect(jsonPath("$.urlProfileImg").value(DEFAULT_URL_PROFILE_IMG))
            .andExpect(jsonPath("$.urlResponsableImg").value(DEFAULT_URL_RESPONSABLE_IMG));
    }

    @Test
    @Transactional
    void getActeursByIdFiltering() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        Long id = acteur.getId();

        defaultActeurShouldBeFound("id.equals=" + id);
        defaultActeurShouldNotBeFound("id.notEquals=" + id);

        defaultActeurShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultActeurShouldNotBeFound("id.greaterThan=" + id);

        defaultActeurShouldBeFound("id.lessThanOrEqual=" + id);
        defaultActeurShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllActeursByVerifieIsEqualToSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where verifie equals to DEFAULT_VERIFIE
        defaultActeurShouldBeFound("verifie.equals=" + DEFAULT_VERIFIE);

        // Get all the acteurList where verifie equals to UPDATED_VERIFIE
        defaultActeurShouldNotBeFound("verifie.equals=" + UPDATED_VERIFIE);
    }

    @Test
    @Transactional
    void getAllActeursByVerifieIsInShouldWork() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where verifie in DEFAULT_VERIFIE or UPDATED_VERIFIE
        defaultActeurShouldBeFound("verifie.in=" + DEFAULT_VERIFIE + "," + UPDATED_VERIFIE);

        // Get all the acteurList where verifie equals to UPDATED_VERIFIE
        defaultActeurShouldNotBeFound("verifie.in=" + UPDATED_VERIFIE);
    }

    @Test
    @Transactional
    void getAllActeursByVerifieIsNullOrNotNull() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where verifie is not null
        defaultActeurShouldBeFound("verifie.specified=true");

        // Get all the acteurList where verifie is null
        defaultActeurShouldNotBeFound("verifie.specified=false");
    }

    @Test
    @Transactional
    void getAllActeursByLoginExpireIsEqualToSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where loginExpire equals to DEFAULT_LOGIN_EXPIRE
        defaultActeurShouldBeFound("loginExpire.equals=" + DEFAULT_LOGIN_EXPIRE);

        // Get all the acteurList where loginExpire equals to UPDATED_LOGIN_EXPIRE
        defaultActeurShouldNotBeFound("loginExpire.equals=" + UPDATED_LOGIN_EXPIRE);
    }

    @Test
    @Transactional
    void getAllActeursByLoginExpireIsInShouldWork() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where loginExpire in DEFAULT_LOGIN_EXPIRE or UPDATED_LOGIN_EXPIRE
        defaultActeurShouldBeFound("loginExpire.in=" + DEFAULT_LOGIN_EXPIRE + "," + UPDATED_LOGIN_EXPIRE);

        // Get all the acteurList where loginExpire equals to UPDATED_LOGIN_EXPIRE
        defaultActeurShouldNotBeFound("loginExpire.in=" + UPDATED_LOGIN_EXPIRE);
    }

    @Test
    @Transactional
    void getAllActeursByLoginExpireIsNullOrNotNull() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where loginExpire is not null
        defaultActeurShouldBeFound("loginExpire.specified=true");

        // Get all the acteurList where loginExpire is null
        defaultActeurShouldNotBeFound("loginExpire.specified=false");
    }

    @Test
    @Transactional
    void getAllActeursByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where email equals to DEFAULT_EMAIL
        defaultActeurShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the acteurList where email equals to UPDATED_EMAIL
        defaultActeurShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllActeursByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultActeurShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the acteurList where email equals to UPDATED_EMAIL
        defaultActeurShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllActeursByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where email is not null
        defaultActeurShouldBeFound("email.specified=true");

        // Get all the acteurList where email is null
        defaultActeurShouldNotBeFound("email.specified=false");
    }

    @Test
    @Transactional
    void getAllActeursByEmailContainsSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where email contains DEFAULT_EMAIL
        defaultActeurShouldBeFound("email.contains=" + DEFAULT_EMAIL);

        // Get all the acteurList where email contains UPDATED_EMAIL
        defaultActeurShouldNotBeFound("email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllActeursByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where email does not contain DEFAULT_EMAIL
        defaultActeurShouldNotBeFound("email.doesNotContain=" + DEFAULT_EMAIL);

        // Get all the acteurList where email does not contain UPDATED_EMAIL
        defaultActeurShouldBeFound("email.doesNotContain=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllActeursByNomIsEqualToSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where nom equals to DEFAULT_NOM
        defaultActeurShouldBeFound("nom.equals=" + DEFAULT_NOM);

        // Get all the acteurList where nom equals to UPDATED_NOM
        defaultActeurShouldNotBeFound("nom.equals=" + UPDATED_NOM);
    }

    @Test
    @Transactional
    void getAllActeursByNomIsInShouldWork() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where nom in DEFAULT_NOM or UPDATED_NOM
        defaultActeurShouldBeFound("nom.in=" + DEFAULT_NOM + "," + UPDATED_NOM);

        // Get all the acteurList where nom equals to UPDATED_NOM
        defaultActeurShouldNotBeFound("nom.in=" + UPDATED_NOM);
    }

    @Test
    @Transactional
    void getAllActeursByNomIsNullOrNotNull() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where nom is not null
        defaultActeurShouldBeFound("nom.specified=true");

        // Get all the acteurList where nom is null
        defaultActeurShouldNotBeFound("nom.specified=false");
    }

    @Test
    @Transactional
    void getAllActeursByNomContainsSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where nom contains DEFAULT_NOM
        defaultActeurShouldBeFound("nom.contains=" + DEFAULT_NOM);

        // Get all the acteurList where nom contains UPDATED_NOM
        defaultActeurShouldNotBeFound("nom.contains=" + UPDATED_NOM);
    }

    @Test
    @Transactional
    void getAllActeursByNomNotContainsSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where nom does not contain DEFAULT_NOM
        defaultActeurShouldNotBeFound("nom.doesNotContain=" + DEFAULT_NOM);

        // Get all the acteurList where nom does not contain UPDATED_NOM
        defaultActeurShouldBeFound("nom.doesNotContain=" + UPDATED_NOM);
    }

    @Test
    @Transactional
    void getAllActeursByNomResponsableIsEqualToSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where nomResponsable equals to DEFAULT_NOM_RESPONSABLE
        defaultActeurShouldBeFound("nomResponsable.equals=" + DEFAULT_NOM_RESPONSABLE);

        // Get all the acteurList where nomResponsable equals to UPDATED_NOM_RESPONSABLE
        defaultActeurShouldNotBeFound("nomResponsable.equals=" + UPDATED_NOM_RESPONSABLE);
    }

    @Test
    @Transactional
    void getAllActeursByNomResponsableIsInShouldWork() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where nomResponsable in DEFAULT_NOM_RESPONSABLE or UPDATED_NOM_RESPONSABLE
        defaultActeurShouldBeFound("nomResponsable.in=" + DEFAULT_NOM_RESPONSABLE + "," + UPDATED_NOM_RESPONSABLE);

        // Get all the acteurList where nomResponsable equals to UPDATED_NOM_RESPONSABLE
        defaultActeurShouldNotBeFound("nomResponsable.in=" + UPDATED_NOM_RESPONSABLE);
    }

    @Test
    @Transactional
    void getAllActeursByNomResponsableIsNullOrNotNull() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where nomResponsable is not null
        defaultActeurShouldBeFound("nomResponsable.specified=true");

        // Get all the acteurList where nomResponsable is null
        defaultActeurShouldNotBeFound("nomResponsable.specified=false");
    }

    @Test
    @Transactional
    void getAllActeursByNomResponsableContainsSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where nomResponsable contains DEFAULT_NOM_RESPONSABLE
        defaultActeurShouldBeFound("nomResponsable.contains=" + DEFAULT_NOM_RESPONSABLE);

        // Get all the acteurList where nomResponsable contains UPDATED_NOM_RESPONSABLE
        defaultActeurShouldNotBeFound("nomResponsable.contains=" + UPDATED_NOM_RESPONSABLE);
    }

    @Test
    @Transactional
    void getAllActeursByNomResponsableNotContainsSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where nomResponsable does not contain DEFAULT_NOM_RESPONSABLE
        defaultActeurShouldNotBeFound("nomResponsable.doesNotContain=" + DEFAULT_NOM_RESPONSABLE);

        // Get all the acteurList where nomResponsable does not contain UPDATED_NOM_RESPONSABLE
        defaultActeurShouldBeFound("nomResponsable.doesNotContain=" + UPDATED_NOM_RESPONSABLE);
    }

    @Test
    @Transactional
    void getAllActeursByNumTelIsEqualToSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where numTel equals to DEFAULT_NUM_TEL
        defaultActeurShouldBeFound("numTel.equals=" + DEFAULT_NUM_TEL);

        // Get all the acteurList where numTel equals to UPDATED_NUM_TEL
        defaultActeurShouldNotBeFound("numTel.equals=" + UPDATED_NUM_TEL);
    }

    @Test
    @Transactional
    void getAllActeursByNumTelIsInShouldWork() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where numTel in DEFAULT_NUM_TEL or UPDATED_NUM_TEL
        defaultActeurShouldBeFound("numTel.in=" + DEFAULT_NUM_TEL + "," + UPDATED_NUM_TEL);

        // Get all the acteurList where numTel equals to UPDATED_NUM_TEL
        defaultActeurShouldNotBeFound("numTel.in=" + UPDATED_NUM_TEL);
    }

    @Test
    @Transactional
    void getAllActeursByNumTelIsNullOrNotNull() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where numTel is not null
        defaultActeurShouldBeFound("numTel.specified=true");

        // Get all the acteurList where numTel is null
        defaultActeurShouldNotBeFound("numTel.specified=false");
    }

    @Test
    @Transactional
    void getAllActeursByNumTelContainsSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where numTel contains DEFAULT_NUM_TEL
        defaultActeurShouldBeFound("numTel.contains=" + DEFAULT_NUM_TEL);

        // Get all the acteurList where numTel contains UPDATED_NUM_TEL
        defaultActeurShouldNotBeFound("numTel.contains=" + UPDATED_NUM_TEL);
    }

    @Test
    @Transactional
    void getAllActeursByNumTelNotContainsSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where numTel does not contain DEFAULT_NUM_TEL
        defaultActeurShouldNotBeFound("numTel.doesNotContain=" + DEFAULT_NUM_TEL);

        // Get all the acteurList where numTel does not contain UPDATED_NUM_TEL
        defaultActeurShouldBeFound("numTel.doesNotContain=" + UPDATED_NUM_TEL);
    }

    @Test
    @Transactional
    void getAllActeursByAddresseIsEqualToSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where addresse equals to DEFAULT_ADDRESSE
        defaultActeurShouldBeFound("addresse.equals=" + DEFAULT_ADDRESSE);

        // Get all the acteurList where addresse equals to UPDATED_ADDRESSE
        defaultActeurShouldNotBeFound("addresse.equals=" + UPDATED_ADDRESSE);
    }

    @Test
    @Transactional
    void getAllActeursByAddresseIsInShouldWork() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where addresse in DEFAULT_ADDRESSE or UPDATED_ADDRESSE
        defaultActeurShouldBeFound("addresse.in=" + DEFAULT_ADDRESSE + "," + UPDATED_ADDRESSE);

        // Get all the acteurList where addresse equals to UPDATED_ADDRESSE
        defaultActeurShouldNotBeFound("addresse.in=" + UPDATED_ADDRESSE);
    }

    @Test
    @Transactional
    void getAllActeursByAddresseIsNullOrNotNull() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where addresse is not null
        defaultActeurShouldBeFound("addresse.specified=true");

        // Get all the acteurList where addresse is null
        defaultActeurShouldNotBeFound("addresse.specified=false");
    }

    @Test
    @Transactional
    void getAllActeursByAddresseContainsSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where addresse contains DEFAULT_ADDRESSE
        defaultActeurShouldBeFound("addresse.contains=" + DEFAULT_ADDRESSE);

        // Get all the acteurList where addresse contains UPDATED_ADDRESSE
        defaultActeurShouldNotBeFound("addresse.contains=" + UPDATED_ADDRESSE);
    }

    @Test
    @Transactional
    void getAllActeursByAddresseNotContainsSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where addresse does not contain DEFAULT_ADDRESSE
        defaultActeurShouldNotBeFound("addresse.doesNotContain=" + DEFAULT_ADDRESSE);

        // Get all the acteurList where addresse does not contain UPDATED_ADDRESSE
        defaultActeurShouldBeFound("addresse.doesNotContain=" + UPDATED_ADDRESSE);
    }

    @Test
    @Transactional
    void getAllActeursByMatriculeFiscaleIsEqualToSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where matriculeFiscale equals to DEFAULT_MATRICULE_FISCALE
        defaultActeurShouldBeFound("matriculeFiscale.equals=" + DEFAULT_MATRICULE_FISCALE);

        // Get all the acteurList where matriculeFiscale equals to UPDATED_MATRICULE_FISCALE
        defaultActeurShouldNotBeFound("matriculeFiscale.equals=" + UPDATED_MATRICULE_FISCALE);
    }

    @Test
    @Transactional
    void getAllActeursByMatriculeFiscaleIsInShouldWork() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where matriculeFiscale in DEFAULT_MATRICULE_FISCALE or UPDATED_MATRICULE_FISCALE
        defaultActeurShouldBeFound("matriculeFiscale.in=" + DEFAULT_MATRICULE_FISCALE + "," + UPDATED_MATRICULE_FISCALE);

        // Get all the acteurList where matriculeFiscale equals to UPDATED_MATRICULE_FISCALE
        defaultActeurShouldNotBeFound("matriculeFiscale.in=" + UPDATED_MATRICULE_FISCALE);
    }

    @Test
    @Transactional
    void getAllActeursByMatriculeFiscaleIsNullOrNotNull() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where matriculeFiscale is not null
        defaultActeurShouldBeFound("matriculeFiscale.specified=true");

        // Get all the acteurList where matriculeFiscale is null
        defaultActeurShouldNotBeFound("matriculeFiscale.specified=false");
    }

    @Test
    @Transactional
    void getAllActeursByMatriculeFiscaleContainsSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where matriculeFiscale contains DEFAULT_MATRICULE_FISCALE
        defaultActeurShouldBeFound("matriculeFiscale.contains=" + DEFAULT_MATRICULE_FISCALE);

        // Get all the acteurList where matriculeFiscale contains UPDATED_MATRICULE_FISCALE
        defaultActeurShouldNotBeFound("matriculeFiscale.contains=" + UPDATED_MATRICULE_FISCALE);
    }

    @Test
    @Transactional
    void getAllActeursByMatriculeFiscaleNotContainsSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where matriculeFiscale does not contain DEFAULT_MATRICULE_FISCALE
        defaultActeurShouldNotBeFound("matriculeFiscale.doesNotContain=" + DEFAULT_MATRICULE_FISCALE);

        // Get all the acteurList where matriculeFiscale does not contain UPDATED_MATRICULE_FISCALE
        defaultActeurShouldBeFound("matriculeFiscale.doesNotContain=" + UPDATED_MATRICULE_FISCALE);
    }

    @Test
    @Transactional
    void getAllActeursByUrlRegistreCommerceIsEqualToSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where urlRegistreCommerce equals to DEFAULT_URL_REGISTRE_COMMERCE
        defaultActeurShouldBeFound("urlRegistreCommerce.equals=" + DEFAULT_URL_REGISTRE_COMMERCE);

        // Get all the acteurList where urlRegistreCommerce equals to UPDATED_URL_REGISTRE_COMMERCE
        defaultActeurShouldNotBeFound("urlRegistreCommerce.equals=" + UPDATED_URL_REGISTRE_COMMERCE);
    }

    @Test
    @Transactional
    void getAllActeursByUrlRegistreCommerceIsInShouldWork() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where urlRegistreCommerce in DEFAULT_URL_REGISTRE_COMMERCE or UPDATED_URL_REGISTRE_COMMERCE
        defaultActeurShouldBeFound("urlRegistreCommerce.in=" + DEFAULT_URL_REGISTRE_COMMERCE + "," + UPDATED_URL_REGISTRE_COMMERCE);

        // Get all the acteurList where urlRegistreCommerce equals to UPDATED_URL_REGISTRE_COMMERCE
        defaultActeurShouldNotBeFound("urlRegistreCommerce.in=" + UPDATED_URL_REGISTRE_COMMERCE);
    }

    @Test
    @Transactional
    void getAllActeursByUrlRegistreCommerceIsNullOrNotNull() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where urlRegistreCommerce is not null
        defaultActeurShouldBeFound("urlRegistreCommerce.specified=true");

        // Get all the acteurList where urlRegistreCommerce is null
        defaultActeurShouldNotBeFound("urlRegistreCommerce.specified=false");
    }

    @Test
    @Transactional
    void getAllActeursByUrlRegistreCommerceContainsSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where urlRegistreCommerce contains DEFAULT_URL_REGISTRE_COMMERCE
        defaultActeurShouldBeFound("urlRegistreCommerce.contains=" + DEFAULT_URL_REGISTRE_COMMERCE);

        // Get all the acteurList where urlRegistreCommerce contains UPDATED_URL_REGISTRE_COMMERCE
        defaultActeurShouldNotBeFound("urlRegistreCommerce.contains=" + UPDATED_URL_REGISTRE_COMMERCE);
    }

    @Test
    @Transactional
    void getAllActeursByUrlRegistreCommerceNotContainsSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where urlRegistreCommerce does not contain DEFAULT_URL_REGISTRE_COMMERCE
        defaultActeurShouldNotBeFound("urlRegistreCommerce.doesNotContain=" + DEFAULT_URL_REGISTRE_COMMERCE);

        // Get all the acteurList where urlRegistreCommerce does not contain UPDATED_URL_REGISTRE_COMMERCE
        defaultActeurShouldBeFound("urlRegistreCommerce.doesNotContain=" + UPDATED_URL_REGISTRE_COMMERCE);
    }

    @Test
    @Transactional
    void getAllActeursByUrlProfileImgIsEqualToSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where urlProfileImg equals to DEFAULT_URL_PROFILE_IMG
        defaultActeurShouldBeFound("urlProfileImg.equals=" + DEFAULT_URL_PROFILE_IMG);

        // Get all the acteurList where urlProfileImg equals to UPDATED_URL_PROFILE_IMG
        defaultActeurShouldNotBeFound("urlProfileImg.equals=" + UPDATED_URL_PROFILE_IMG);
    }

    @Test
    @Transactional
    void getAllActeursByUrlProfileImgIsInShouldWork() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where urlProfileImg in DEFAULT_URL_PROFILE_IMG or UPDATED_URL_PROFILE_IMG
        defaultActeurShouldBeFound("urlProfileImg.in=" + DEFAULT_URL_PROFILE_IMG + "," + UPDATED_URL_PROFILE_IMG);

        // Get all the acteurList where urlProfileImg equals to UPDATED_URL_PROFILE_IMG
        defaultActeurShouldNotBeFound("urlProfileImg.in=" + UPDATED_URL_PROFILE_IMG);
    }

    @Test
    @Transactional
    void getAllActeursByUrlProfileImgIsNullOrNotNull() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where urlProfileImg is not null
        defaultActeurShouldBeFound("urlProfileImg.specified=true");

        // Get all the acteurList where urlProfileImg is null
        defaultActeurShouldNotBeFound("urlProfileImg.specified=false");
    }

    @Test
    @Transactional
    void getAllActeursByUrlProfileImgContainsSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where urlProfileImg contains DEFAULT_URL_PROFILE_IMG
        defaultActeurShouldBeFound("urlProfileImg.contains=" + DEFAULT_URL_PROFILE_IMG);

        // Get all the acteurList where urlProfileImg contains UPDATED_URL_PROFILE_IMG
        defaultActeurShouldNotBeFound("urlProfileImg.contains=" + UPDATED_URL_PROFILE_IMG);
    }

    @Test
    @Transactional
    void getAllActeursByUrlProfileImgNotContainsSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where urlProfileImg does not contain DEFAULT_URL_PROFILE_IMG
        defaultActeurShouldNotBeFound("urlProfileImg.doesNotContain=" + DEFAULT_URL_PROFILE_IMG);

        // Get all the acteurList where urlProfileImg does not contain UPDATED_URL_PROFILE_IMG
        defaultActeurShouldBeFound("urlProfileImg.doesNotContain=" + UPDATED_URL_PROFILE_IMG);
    }

    @Test
    @Transactional
    void getAllActeursByUrlResponsableImgIsEqualToSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where urlResponsableImg equals to DEFAULT_URL_RESPONSABLE_IMG
        defaultActeurShouldBeFound("urlResponsableImg.equals=" + DEFAULT_URL_RESPONSABLE_IMG);

        // Get all the acteurList where urlResponsableImg equals to UPDATED_URL_RESPONSABLE_IMG
        defaultActeurShouldNotBeFound("urlResponsableImg.equals=" + UPDATED_URL_RESPONSABLE_IMG);
    }

    @Test
    @Transactional
    void getAllActeursByUrlResponsableImgIsInShouldWork() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where urlResponsableImg in DEFAULT_URL_RESPONSABLE_IMG or UPDATED_URL_RESPONSABLE_IMG
        defaultActeurShouldBeFound("urlResponsableImg.in=" + DEFAULT_URL_RESPONSABLE_IMG + "," + UPDATED_URL_RESPONSABLE_IMG);

        // Get all the acteurList where urlResponsableImg equals to UPDATED_URL_RESPONSABLE_IMG
        defaultActeurShouldNotBeFound("urlResponsableImg.in=" + UPDATED_URL_RESPONSABLE_IMG);
    }

    @Test
    @Transactional
    void getAllActeursByUrlResponsableImgIsNullOrNotNull() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where urlResponsableImg is not null
        defaultActeurShouldBeFound("urlResponsableImg.specified=true");

        // Get all the acteurList where urlResponsableImg is null
        defaultActeurShouldNotBeFound("urlResponsableImg.specified=false");
    }

    @Test
    @Transactional
    void getAllActeursByUrlResponsableImgContainsSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where urlResponsableImg contains DEFAULT_URL_RESPONSABLE_IMG
        defaultActeurShouldBeFound("urlResponsableImg.contains=" + DEFAULT_URL_RESPONSABLE_IMG);

        // Get all the acteurList where urlResponsableImg contains UPDATED_URL_RESPONSABLE_IMG
        defaultActeurShouldNotBeFound("urlResponsableImg.contains=" + UPDATED_URL_RESPONSABLE_IMG);
    }

    @Test
    @Transactional
    void getAllActeursByUrlResponsableImgNotContainsSomething() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        // Get all the acteurList where urlResponsableImg does not contain DEFAULT_URL_RESPONSABLE_IMG
        defaultActeurShouldNotBeFound("urlResponsableImg.doesNotContain=" + DEFAULT_URL_RESPONSABLE_IMG);

        // Get all the acteurList where urlResponsableImg does not contain UPDATED_URL_RESPONSABLE_IMG
        defaultActeurShouldBeFound("urlResponsableImg.doesNotContain=" + UPDATED_URL_RESPONSABLE_IMG);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultActeurShouldBeFound(String filter) throws Exception {
        restActeurMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(acteur.getId().intValue())))
            .andExpect(jsonPath("$.[*].verifie").value(hasItem(DEFAULT_VERIFIE.booleanValue())))
            .andExpect(jsonPath("$.[*].loginExpire").value(hasItem(DEFAULT_LOGIN_EXPIRE.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].nomResponsable").value(hasItem(DEFAULT_NOM_RESPONSABLE)))
            .andExpect(jsonPath("$.[*].numTel").value(hasItem(DEFAULT_NUM_TEL)))
            .andExpect(jsonPath("$.[*].addresse").value(hasItem(DEFAULT_ADDRESSE)))
            .andExpect(jsonPath("$.[*].matriculeFiscale").value(hasItem(DEFAULT_MATRICULE_FISCALE)))
            .andExpect(jsonPath("$.[*].urlRegistreCommerce").value(hasItem(DEFAULT_URL_REGISTRE_COMMERCE)))
            .andExpect(jsonPath("$.[*].urlProfileImg").value(hasItem(DEFAULT_URL_PROFILE_IMG)))
            .andExpect(jsonPath("$.[*].urlResponsableImg").value(hasItem(DEFAULT_URL_RESPONSABLE_IMG)));

        // Check, that the count call also returns 1
        restActeurMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultActeurShouldNotBeFound(String filter) throws Exception {
        restActeurMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restActeurMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingActeur() throws Exception {
        // Get the acteur
        restActeurMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingActeur() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        int databaseSizeBeforeUpdate = acteurRepository.findAll().size();

        // Update the acteur
        Acteur updatedActeur = acteurRepository.findById(acteur.getId()).get();
        // Disconnect from session so that the updates on updatedActeur are not directly saved in db
        em.detach(updatedActeur);
        updatedActeur
            .verifie(UPDATED_VERIFIE)
            .loginExpire(UPDATED_LOGIN_EXPIRE)
            .email(UPDATED_EMAIL)
            .nom(UPDATED_NOM)
            .nomResponsable(UPDATED_NOM_RESPONSABLE)
            .numTel(UPDATED_NUM_TEL)
            .addresse(UPDATED_ADDRESSE)
            .matriculeFiscale(UPDATED_MATRICULE_FISCALE)
            .urlRegistreCommerce(UPDATED_URL_REGISTRE_COMMERCE)
            .urlProfileImg(UPDATED_URL_PROFILE_IMG)
            .urlResponsableImg(UPDATED_URL_RESPONSABLE_IMG);
        ActeurDTO acteurDTO = acteurMapper.toDto(updatedActeur);

        restActeurMockMvc
            .perform(
                put(ENTITY_API_URL_ID, acteurDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(acteurDTO))
            )
            .andExpect(status().isOk());

        // Validate the Acteur in the database
        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeUpdate);
        Acteur testActeur = acteurList.get(acteurList.size() - 1);
        assertThat(testActeur.getVerifie()).isEqualTo(UPDATED_VERIFIE);
        assertThat(testActeur.getLoginExpire()).isEqualTo(UPDATED_LOGIN_EXPIRE);
        assertThat(testActeur.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testActeur.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testActeur.getNomResponsable()).isEqualTo(UPDATED_NOM_RESPONSABLE);
        assertThat(testActeur.getNumTel()).isEqualTo(UPDATED_NUM_TEL);
        assertThat(testActeur.getAddresse()).isEqualTo(UPDATED_ADDRESSE);
        assertThat(testActeur.getMatriculeFiscale()).isEqualTo(UPDATED_MATRICULE_FISCALE);
        assertThat(testActeur.getUrlRegistreCommerce()).isEqualTo(UPDATED_URL_REGISTRE_COMMERCE);
        assertThat(testActeur.getUrlProfileImg()).isEqualTo(UPDATED_URL_PROFILE_IMG);
        assertThat(testActeur.getUrlResponsableImg()).isEqualTo(UPDATED_URL_RESPONSABLE_IMG);
    }

    @Test
    @Transactional
    void putNonExistingActeur() throws Exception {
        int databaseSizeBeforeUpdate = acteurRepository.findAll().size();
        acteur.setId(count.incrementAndGet());

        // Create the Acteur
        ActeurDTO acteurDTO = acteurMapper.toDto(acteur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restActeurMockMvc
            .perform(
                put(ENTITY_API_URL_ID, acteurDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(acteurDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Acteur in the database
        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchActeur() throws Exception {
        int databaseSizeBeforeUpdate = acteurRepository.findAll().size();
        acteur.setId(count.incrementAndGet());

        // Create the Acteur
        ActeurDTO acteurDTO = acteurMapper.toDto(acteur);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restActeurMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(acteurDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Acteur in the database
        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamActeur() throws Exception {
        int databaseSizeBeforeUpdate = acteurRepository.findAll().size();
        acteur.setId(count.incrementAndGet());

        // Create the Acteur
        ActeurDTO acteurDTO = acteurMapper.toDto(acteur);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restActeurMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(acteurDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Acteur in the database
        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateActeurWithPatch() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        int databaseSizeBeforeUpdate = acteurRepository.findAll().size();

        // Update the acteur using partial update
        Acteur partialUpdatedActeur = new Acteur();
        partialUpdatedActeur.setId(acteur.getId());

        partialUpdatedActeur
            .nom(UPDATED_NOM)
            .numTel(UPDATED_NUM_TEL)
            .matriculeFiscale(UPDATED_MATRICULE_FISCALE)
            .urlRegistreCommerce(UPDATED_URL_REGISTRE_COMMERCE)
            .urlProfileImg(UPDATED_URL_PROFILE_IMG)
            .urlResponsableImg(UPDATED_URL_RESPONSABLE_IMG);

        restActeurMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedActeur.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedActeur))
            )
            .andExpect(status().isOk());

        // Validate the Acteur in the database
        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeUpdate);
        Acteur testActeur = acteurList.get(acteurList.size() - 1);
        assertThat(testActeur.getVerifie()).isEqualTo(DEFAULT_VERIFIE);
        assertThat(testActeur.getLoginExpire()).isEqualTo(DEFAULT_LOGIN_EXPIRE);
        assertThat(testActeur.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testActeur.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testActeur.getNomResponsable()).isEqualTo(DEFAULT_NOM_RESPONSABLE);
        assertThat(testActeur.getNumTel()).isEqualTo(UPDATED_NUM_TEL);
        assertThat(testActeur.getAddresse()).isEqualTo(DEFAULT_ADDRESSE);
        assertThat(testActeur.getMatriculeFiscale()).isEqualTo(UPDATED_MATRICULE_FISCALE);
        assertThat(testActeur.getUrlRegistreCommerce()).isEqualTo(UPDATED_URL_REGISTRE_COMMERCE);
        assertThat(testActeur.getUrlProfileImg()).isEqualTo(UPDATED_URL_PROFILE_IMG);
        assertThat(testActeur.getUrlResponsableImg()).isEqualTo(UPDATED_URL_RESPONSABLE_IMG);
    }

    @Test
    @Transactional
    void fullUpdateActeurWithPatch() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        int databaseSizeBeforeUpdate = acteurRepository.findAll().size();

        // Update the acteur using partial update
        Acteur partialUpdatedActeur = new Acteur();
        partialUpdatedActeur.setId(acteur.getId());

        partialUpdatedActeur
            .verifie(UPDATED_VERIFIE)
            .loginExpire(UPDATED_LOGIN_EXPIRE)
            .email(UPDATED_EMAIL)
            .nom(UPDATED_NOM)
            .nomResponsable(UPDATED_NOM_RESPONSABLE)
            .numTel(UPDATED_NUM_TEL)
            .addresse(UPDATED_ADDRESSE)
            .matriculeFiscale(UPDATED_MATRICULE_FISCALE)
            .urlRegistreCommerce(UPDATED_URL_REGISTRE_COMMERCE)
            .urlProfileImg(UPDATED_URL_PROFILE_IMG)
            .urlResponsableImg(UPDATED_URL_RESPONSABLE_IMG);

        restActeurMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedActeur.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedActeur))
            )
            .andExpect(status().isOk());

        // Validate the Acteur in the database
        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeUpdate);
        Acteur testActeur = acteurList.get(acteurList.size() - 1);
        assertThat(testActeur.getVerifie()).isEqualTo(UPDATED_VERIFIE);
        assertThat(testActeur.getLoginExpire()).isEqualTo(UPDATED_LOGIN_EXPIRE);
        assertThat(testActeur.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testActeur.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testActeur.getNomResponsable()).isEqualTo(UPDATED_NOM_RESPONSABLE);
        assertThat(testActeur.getNumTel()).isEqualTo(UPDATED_NUM_TEL);
        assertThat(testActeur.getAddresse()).isEqualTo(UPDATED_ADDRESSE);
        assertThat(testActeur.getMatriculeFiscale()).isEqualTo(UPDATED_MATRICULE_FISCALE);
        assertThat(testActeur.getUrlRegistreCommerce()).isEqualTo(UPDATED_URL_REGISTRE_COMMERCE);
        assertThat(testActeur.getUrlProfileImg()).isEqualTo(UPDATED_URL_PROFILE_IMG);
        assertThat(testActeur.getUrlResponsableImg()).isEqualTo(UPDATED_URL_RESPONSABLE_IMG);
    }

    @Test
    @Transactional
    void patchNonExistingActeur() throws Exception {
        int databaseSizeBeforeUpdate = acteurRepository.findAll().size();
        acteur.setId(count.incrementAndGet());

        // Create the Acteur
        ActeurDTO acteurDTO = acteurMapper.toDto(acteur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restActeurMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, acteurDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(acteurDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Acteur in the database
        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchActeur() throws Exception {
        int databaseSizeBeforeUpdate = acteurRepository.findAll().size();
        acteur.setId(count.incrementAndGet());

        // Create the Acteur
        ActeurDTO acteurDTO = acteurMapper.toDto(acteur);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restActeurMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(acteurDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Acteur in the database
        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamActeur() throws Exception {
        int databaseSizeBeforeUpdate = acteurRepository.findAll().size();
        acteur.setId(count.incrementAndGet());

        // Create the Acteur
        ActeurDTO acteurDTO = acteurMapper.toDto(acteur);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restActeurMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(acteurDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Acteur in the database
        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteActeur() throws Exception {
        // Initialize the database
        acteurRepository.saveAndFlush(acteur);

        int databaseSizeBeforeDelete = acteurRepository.findAll().size();

        // Delete the acteur
        restActeurMockMvc
            .perform(delete(ENTITY_API_URL_ID, acteur.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Acteur> acteurList = acteurRepository.findAll();
        assertThat(acteurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

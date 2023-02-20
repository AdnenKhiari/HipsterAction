package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Acteur.
 */
@Entity
@Table(name = "acteur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Acteur extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "verifie", nullable = false)
    private Boolean verifie;

    @NotNull
    @Column(name = "login_expire", nullable = false)
    private Instant loginExpire;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Size(min = 2, max = 80)
    @Column(name = "nom", length = 80, nullable = false)
    private String nom;

    @NotNull
    @Column(name = "nom_responsable", nullable = false)
    private String nomResponsable;

    @NotNull
    @Column(name = "num_tel", nullable = false)
    private String numTel;

    @NotNull
    @Column(name = "addresse", nullable = false)
    private String addresse;

    @NotNull
    @Column(name = "matricule_fiscale", nullable = false)
    private String matriculeFiscale;

    @NotNull
    @Column(name = "url_registre_commerce", nullable = false)
    private String urlRegistreCommerce;

    @NotNull
    @Column(name = "url_profile_img", nullable = false)
    private String urlProfileImg;

    @Column(name = "url_responsable_img")
    private String urlResponsableImg;

    // Inherited createdBy definition
    // Inherited createdDate definition
    // Inherited lastModifiedBy definition
    // Inherited lastModifiedDate definition

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Acteur id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getVerifie() {
        return this.verifie;
    }

    public Acteur verifie(Boolean verifie) {
        this.setVerifie(verifie);
        return this;
    }

    public void setVerifie(Boolean verifie) {
        this.verifie = verifie;
    }

    public Instant getLoginExpire() {
        return this.loginExpire;
    }

    public Acteur loginExpire(Instant loginExpire) {
        this.setLoginExpire(loginExpire);
        return this;
    }

    public void setLoginExpire(Instant loginExpire) {
        this.loginExpire = loginExpire;
    }

    public String getEmail() {
        return this.email;
    }

    public Acteur email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return this.nom;
    }

    public Acteur nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomResponsable() {
        return this.nomResponsable;
    }

    public Acteur nomResponsable(String nomResponsable) {
        this.setNomResponsable(nomResponsable);
        return this;
    }

    public void setNomResponsable(String nomResponsable) {
        this.nomResponsable = nomResponsable;
    }

    public String getNumTel() {
        return this.numTel;
    }

    public Acteur numTel(String numTel) {
        this.setNumTel(numTel);
        return this;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getAddresse() {
        return this.addresse;
    }

    public Acteur addresse(String addresse) {
        this.setAddresse(addresse);
        return this;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getMatriculeFiscale() {
        return this.matriculeFiscale;
    }

    public Acteur matriculeFiscale(String matriculeFiscale) {
        this.setMatriculeFiscale(matriculeFiscale);
        return this;
    }

    public void setMatriculeFiscale(String matriculeFiscale) {
        this.matriculeFiscale = matriculeFiscale;
    }

    public String getUrlRegistreCommerce() {
        return this.urlRegistreCommerce;
    }

    public Acteur urlRegistreCommerce(String urlRegistreCommerce) {
        this.setUrlRegistreCommerce(urlRegistreCommerce);
        return this;
    }

    public void setUrlRegistreCommerce(String urlRegistreCommerce) {
        this.urlRegistreCommerce = urlRegistreCommerce;
    }

    public String getUrlProfileImg() {
        return this.urlProfileImg;
    }

    public Acteur urlProfileImg(String urlProfileImg) {
        this.setUrlProfileImg(urlProfileImg);
        return this;
    }

    public void setUrlProfileImg(String urlProfileImg) {
        this.urlProfileImg = urlProfileImg;
    }

    public String getUrlResponsableImg() {
        return this.urlResponsableImg;
    }

    public Acteur urlResponsableImg(String urlResponsableImg) {
        this.setUrlResponsableImg(urlResponsableImg);
        return this;
    }

    public void setUrlResponsableImg(String urlResponsableImg) {
        this.urlResponsableImg = urlResponsableImg;
    }

    // Inherited createdBy methods
    public Acteur createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    // Inherited createdDate methods
    public Acteur createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    // Inherited lastModifiedBy methods
    public Acteur lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    // Inherited lastModifiedDate methods
    public Acteur lastModifiedDate(Instant lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Acteur)) {
            return false;
        }
        return id != null && id.equals(((Acteur) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Acteur{" +
            "id=" + getId() +
            ", verifie='" + getVerifie() + "'" +
            ", loginExpire='" + getLoginExpire() + "'" +
            ", email='" + getEmail() + "'" +
            ", nom='" + getNom() + "'" +
            ", nomResponsable='" + getNomResponsable() + "'" +
            ", numTel='" + getNumTel() + "'" +
            ", addresse='" + getAddresse() + "'" +
            ", matriculeFiscale='" + getMatriculeFiscale() + "'" +
            ", urlRegistreCommerce='" + getUrlRegistreCommerce() + "'" +
            ", urlProfileImg='" + getUrlProfileImg() + "'" +
            ", urlResponsableImg='" + getUrlResponsableImg() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}

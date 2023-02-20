package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Acteur} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ActeurDTO implements Serializable {

    private Long id;

    @NotNull
    private Boolean verifie;

    @NotNull
    private Instant loginExpire;

    @NotNull
    private String email;

    @NotNull
    @Size(min = 2, max = 80)
    private String nom;

    @NotNull
    private String nomResponsable;

    @NotNull
    private String numTel;

    @NotNull
    private String addresse;

    @NotNull
    private String matriculeFiscale;

    @NotNull
    private String urlRegistreCommerce;

    @NotNull
    private String urlProfileImg;

    private String urlResponsableImg;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getVerifie() {
        return verifie;
    }

    public void setVerifie(Boolean verifie) {
        this.verifie = verifie;
    }

    public Instant getLoginExpire() {
        return loginExpire;
    }

    public void setLoginExpire(Instant loginExpire) {
        this.loginExpire = loginExpire;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomResponsable() {
        return nomResponsable;
    }

    public void setNomResponsable(String nomResponsable) {
        this.nomResponsable = nomResponsable;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getMatriculeFiscale() {
        return matriculeFiscale;
    }

    public void setMatriculeFiscale(String matriculeFiscale) {
        this.matriculeFiscale = matriculeFiscale;
    }

    public String getUrlRegistreCommerce() {
        return urlRegistreCommerce;
    }

    public void setUrlRegistreCommerce(String urlRegistreCommerce) {
        this.urlRegistreCommerce = urlRegistreCommerce;
    }

    public String getUrlProfileImg() {
        return urlProfileImg;
    }

    public void setUrlProfileImg(String urlProfileImg) {
        this.urlProfileImg = urlProfileImg;
    }

    public String getUrlResponsableImg() {
        return urlResponsableImg;
    }

    public void setUrlResponsableImg(String urlResponsableImg) {
        this.urlResponsableImg = urlResponsableImg;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActeurDTO)) {
            return false;
        }

        ActeurDTO acteurDTO = (ActeurDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, acteurDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ActeurDTO{" +
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

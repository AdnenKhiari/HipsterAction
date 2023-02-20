package com.mycompany.myapp.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.Acteur} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.ActeurResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /acteurs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ActeurCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private BooleanFilter verifie;

    private InstantFilter loginExpire;

    private StringFilter email;

    private StringFilter nom;

    private StringFilter nomResponsable;

    private StringFilter numTel;

    private StringFilter addresse;

    private StringFilter matriculeFiscale;

    private StringFilter urlRegistreCommerce;

    private StringFilter urlProfileImg;

    private StringFilter urlResponsableImg;

    private StringFilter createdBy;

    private InstantFilter createdDate;

    private StringFilter lastModifiedBy;

    private InstantFilter lastModifiedDate;

    private Boolean distinct;

    public ActeurCriteria() {}

    public ActeurCriteria(ActeurCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.verifie = other.verifie == null ? null : other.verifie.copy();
        this.loginExpire = other.loginExpire == null ? null : other.loginExpire.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.nom = other.nom == null ? null : other.nom.copy();
        this.nomResponsable = other.nomResponsable == null ? null : other.nomResponsable.copy();
        this.numTel = other.numTel == null ? null : other.numTel.copy();
        this.addresse = other.addresse == null ? null : other.addresse.copy();
        this.matriculeFiscale = other.matriculeFiscale == null ? null : other.matriculeFiscale.copy();
        this.urlRegistreCommerce = other.urlRegistreCommerce == null ? null : other.urlRegistreCommerce.copy();
        this.urlProfileImg = other.urlProfileImg == null ? null : other.urlProfileImg.copy();
        this.urlResponsableImg = other.urlResponsableImg == null ? null : other.urlResponsableImg.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.createdDate = other.createdDate == null ? null : other.createdDate.copy();
        this.lastModifiedBy = other.lastModifiedBy == null ? null : other.lastModifiedBy.copy();
        this.lastModifiedDate = other.lastModifiedDate == null ? null : other.lastModifiedDate.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ActeurCriteria copy() {
        return new ActeurCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public BooleanFilter getVerifie() {
        return verifie;
    }

    public BooleanFilter verifie() {
        if (verifie == null) {
            verifie = new BooleanFilter();
        }
        return verifie;
    }

    public void setVerifie(BooleanFilter verifie) {
        this.verifie = verifie;
    }

    public InstantFilter getLoginExpire() {
        return loginExpire;
    }

    public InstantFilter loginExpire() {
        if (loginExpire == null) {
            loginExpire = new InstantFilter();
        }
        return loginExpire;
    }

    public void setLoginExpire(InstantFilter loginExpire) {
        this.loginExpire = loginExpire;
    }

    public StringFilter getEmail() {
        return email;
    }

    public StringFilter email() {
        if (email == null) {
            email = new StringFilter();
        }
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getNom() {
        return nom;
    }

    public StringFilter nom() {
        if (nom == null) {
            nom = new StringFilter();
        }
        return nom;
    }

    public void setNom(StringFilter nom) {
        this.nom = nom;
    }

    public StringFilter getNomResponsable() {
        return nomResponsable;
    }

    public StringFilter nomResponsable() {
        if (nomResponsable == null) {
            nomResponsable = new StringFilter();
        }
        return nomResponsable;
    }

    public void setNomResponsable(StringFilter nomResponsable) {
        this.nomResponsable = nomResponsable;
    }

    public StringFilter getNumTel() {
        return numTel;
    }

    public StringFilter numTel() {
        if (numTel == null) {
            numTel = new StringFilter();
        }
        return numTel;
    }

    public void setNumTel(StringFilter numTel) {
        this.numTel = numTel;
    }

    public StringFilter getAddresse() {
        return addresse;
    }

    public StringFilter addresse() {
        if (addresse == null) {
            addresse = new StringFilter();
        }
        return addresse;
    }

    public void setAddresse(StringFilter addresse) {
        this.addresse = addresse;
    }

    public StringFilter getMatriculeFiscale() {
        return matriculeFiscale;
    }

    public StringFilter matriculeFiscale() {
        if (matriculeFiscale == null) {
            matriculeFiscale = new StringFilter();
        }
        return matriculeFiscale;
    }

    public void setMatriculeFiscale(StringFilter matriculeFiscale) {
        this.matriculeFiscale = matriculeFiscale;
    }

    public StringFilter getUrlRegistreCommerce() {
        return urlRegistreCommerce;
    }

    public StringFilter urlRegistreCommerce() {
        if (urlRegistreCommerce == null) {
            urlRegistreCommerce = new StringFilter();
        }
        return urlRegistreCommerce;
    }

    public void setUrlRegistreCommerce(StringFilter urlRegistreCommerce) {
        this.urlRegistreCommerce = urlRegistreCommerce;
    }

    public StringFilter getUrlProfileImg() {
        return urlProfileImg;
    }

    public StringFilter urlProfileImg() {
        if (urlProfileImg == null) {
            urlProfileImg = new StringFilter();
        }
        return urlProfileImg;
    }

    public void setUrlProfileImg(StringFilter urlProfileImg) {
        this.urlProfileImg = urlProfileImg;
    }

    public StringFilter getUrlResponsableImg() {
        return urlResponsableImg;
    }

    public StringFilter urlResponsableImg() {
        if (urlResponsableImg == null) {
            urlResponsableImg = new StringFilter();
        }
        return urlResponsableImg;
    }

    public void setUrlResponsableImg(StringFilter urlResponsableImg) {
        this.urlResponsableImg = urlResponsableImg;
    }

    public StringFilter getCreatedBy() {
        return createdBy;
    }

    public StringFilter createdBy() {
        if (createdBy == null) {
            createdBy = new StringFilter();
        }
        return createdBy;
    }

    public void setCreatedBy(StringFilter createdBy) {
        this.createdBy = createdBy;
    }

    public InstantFilter getCreatedDate() {
        return createdDate;
    }

    public InstantFilter createdDate() {
        if (createdDate == null) {
            createdDate = new InstantFilter();
        }
        return createdDate;
    }

    public void setCreatedDate(InstantFilter createdDate) {
        this.createdDate = createdDate;
    }

    public StringFilter getLastModifiedBy() {
        return lastModifiedBy;
    }

    public StringFilter lastModifiedBy() {
        if (lastModifiedBy == null) {
            lastModifiedBy = new StringFilter();
        }
        return lastModifiedBy;
    }

    public void setLastModifiedBy(StringFilter lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public InstantFilter getLastModifiedDate() {
        return lastModifiedDate;
    }

    public InstantFilter lastModifiedDate() {
        if (lastModifiedDate == null) {
            lastModifiedDate = new InstantFilter();
        }
        return lastModifiedDate;
    }

    public void setLastModifiedDate(InstantFilter lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ActeurCriteria that = (ActeurCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(verifie, that.verifie) &&
            Objects.equals(loginExpire, that.loginExpire) &&
            Objects.equals(email, that.email) &&
            Objects.equals(nom, that.nom) &&
            Objects.equals(nomResponsable, that.nomResponsable) &&
            Objects.equals(numTel, that.numTel) &&
            Objects.equals(addresse, that.addresse) &&
            Objects.equals(matriculeFiscale, that.matriculeFiscale) &&
            Objects.equals(urlRegistreCommerce, that.urlRegistreCommerce) &&
            Objects.equals(urlProfileImg, that.urlProfileImg) &&
            Objects.equals(urlResponsableImg, that.urlResponsableImg) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
            Objects.equals(lastModifiedDate, that.lastModifiedDate) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            verifie,
            loginExpire,
            email,
            nom,
            nomResponsable,
            numTel,
            addresse,
            matriculeFiscale,
            urlRegistreCommerce,
            urlProfileImg,
            urlResponsableImg,
            createdBy,
            createdDate,
            lastModifiedBy,
            lastModifiedDate,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ActeurCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (verifie != null ? "verifie=" + verifie + ", " : "") +
            (loginExpire != null ? "loginExpire=" + loginExpire + ", " : "") +
            (email != null ? "email=" + email + ", " : "") +
            (nom != null ? "nom=" + nom + ", " : "") +
            (nomResponsable != null ? "nomResponsable=" + nomResponsable + ", " : "") +
            (numTel != null ? "numTel=" + numTel + ", " : "") +
            (addresse != null ? "addresse=" + addresse + ", " : "") +
            (matriculeFiscale != null ? "matriculeFiscale=" + matriculeFiscale + ", " : "") +
            (urlRegistreCommerce != null ? "urlRegistreCommerce=" + urlRegistreCommerce + ", " : "") +
            (urlProfileImg != null ? "urlProfileImg=" + urlProfileImg + ", " : "") +
            (urlResponsableImg != null ? "urlResponsableImg=" + urlResponsableImg + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
            (lastModifiedBy != null ? "lastModifiedBy=" + lastModifiedBy + ", " : "") +
            (lastModifiedDate != null ? "lastModifiedDate=" + lastModifiedDate + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}

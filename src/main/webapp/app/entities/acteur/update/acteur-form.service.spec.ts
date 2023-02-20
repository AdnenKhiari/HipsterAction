import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../acteur.test-samples';

import { ActeurFormService } from './acteur-form.service';

describe('Acteur Form Service', () => {
  let service: ActeurFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ActeurFormService);
  });

  describe('Service methods', () => {
    describe('createActeurFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createActeurFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            verifie: expect.any(Object),
            loginExpire: expect.any(Object),
            email: expect.any(Object),
            nom: expect.any(Object),
            nomResponsable: expect.any(Object),
            numTel: expect.any(Object),
            addresse: expect.any(Object),
            matriculeFiscale: expect.any(Object),
            urlRegistreCommerce: expect.any(Object),
            urlProfileImg: expect.any(Object),
            urlResponsableImg: expect.any(Object),
            createdBy: expect.any(Object),
            createdDate: expect.any(Object),
            lastModifiedBy: expect.any(Object),
            lastModifiedDate: expect.any(Object),
          })
        );
      });

      it('passing IActeur should create a new form with FormGroup', () => {
        const formGroup = service.createActeurFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            verifie: expect.any(Object),
            loginExpire: expect.any(Object),
            email: expect.any(Object),
            nom: expect.any(Object),
            nomResponsable: expect.any(Object),
            numTel: expect.any(Object),
            addresse: expect.any(Object),
            matriculeFiscale: expect.any(Object),
            urlRegistreCommerce: expect.any(Object),
            urlProfileImg: expect.any(Object),
            urlResponsableImg: expect.any(Object),
            createdBy: expect.any(Object),
            createdDate: expect.any(Object),
            lastModifiedBy: expect.any(Object),
            lastModifiedDate: expect.any(Object),
          })
        );
      });
    });

    describe('getActeur', () => {
      it('should return NewActeur for default Acteur initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createActeurFormGroup(sampleWithNewData);

        const acteur = service.getActeur(formGroup) as any;

        expect(acteur).toMatchObject(sampleWithNewData);
      });

      it('should return NewActeur for empty Acteur initial value', () => {
        const formGroup = service.createActeurFormGroup();

        const acteur = service.getActeur(formGroup) as any;

        expect(acteur).toMatchObject({});
      });

      it('should return IActeur', () => {
        const formGroup = service.createActeurFormGroup(sampleWithRequiredData);

        const acteur = service.getActeur(formGroup) as any;

        expect(acteur).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IActeur should not enable id FormControl', () => {
        const formGroup = service.createActeurFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewActeur should disable id FormControl', () => {
        const formGroup = service.createActeurFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});

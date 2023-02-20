import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IActeur, NewActeur } from '../acteur.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IActeur for edit and NewActeurFormGroupInput for create.
 */
type ActeurFormGroupInput = IActeur | PartialWithRequiredKeyOf<NewActeur>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IActeur | NewActeur> = Omit<T, 'loginExpire' | 'createdDate' | 'lastModifiedDate'> & {
  loginExpire?: string | null;
  createdDate?: string | null;
  lastModifiedDate?: string | null;
};

type ActeurFormRawValue = FormValueOf<IActeur>;

type NewActeurFormRawValue = FormValueOf<NewActeur>;

type ActeurFormDefaults = Pick<NewActeur, 'id' | 'verifie' | 'loginExpire' | 'createdDate' | 'lastModifiedDate'>;

type ActeurFormGroupContent = {
  id: FormControl<ActeurFormRawValue['id'] | NewActeur['id']>;
  verifie: FormControl<ActeurFormRawValue['verifie']>;
  loginExpire: FormControl<ActeurFormRawValue['loginExpire']>;
  email: FormControl<ActeurFormRawValue['email']>;
  nom: FormControl<ActeurFormRawValue['nom']>;
  nomResponsable: FormControl<ActeurFormRawValue['nomResponsable']>;
  numTel: FormControl<ActeurFormRawValue['numTel']>;
  addresse: FormControl<ActeurFormRawValue['addresse']>;
  matriculeFiscale: FormControl<ActeurFormRawValue['matriculeFiscale']>;
  urlRegistreCommerce: FormControl<ActeurFormRawValue['urlRegistreCommerce']>;
  urlProfileImg: FormControl<ActeurFormRawValue['urlProfileImg']>;
  urlResponsableImg: FormControl<ActeurFormRawValue['urlResponsableImg']>;
  createdBy: FormControl<ActeurFormRawValue['createdBy']>;
  createdDate: FormControl<ActeurFormRawValue['createdDate']>;
  lastModifiedBy: FormControl<ActeurFormRawValue['lastModifiedBy']>;
  lastModifiedDate: FormControl<ActeurFormRawValue['lastModifiedDate']>;
};

export type ActeurFormGroup = FormGroup<ActeurFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ActeurFormService {
  createActeurFormGroup(acteur: ActeurFormGroupInput = { id: null }): ActeurFormGroup {
    const acteurRawValue = this.convertActeurToActeurRawValue({
      ...this.getFormDefaults(),
      ...acteur,
    });
    return new FormGroup<ActeurFormGroupContent>({
      id: new FormControl(
        { value: acteurRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      verifie: new FormControl(acteurRawValue.verifie, {
        validators: [Validators.required],
      }),
      loginExpire: new FormControl(acteurRawValue.loginExpire, {
        validators: [Validators.required],
      }),
      email: new FormControl(acteurRawValue.email, {
        validators: [Validators.required],
      }),
      nom: new FormControl(acteurRawValue.nom, {
        validators: [Validators.required, Validators.minLength(2), Validators.maxLength(80)],
      }),
      nomResponsable: new FormControl(acteurRawValue.nomResponsable, {
        validators: [Validators.required],
      }),
      numTel: new FormControl(acteurRawValue.numTel, {
        validators: [Validators.required],
      }),
      addresse: new FormControl(acteurRawValue.addresse, {
        validators: [Validators.required],
      }),
      matriculeFiscale: new FormControl(acteurRawValue.matriculeFiscale, {
        validators: [Validators.required],
      }),
      urlRegistreCommerce: new FormControl(acteurRawValue.urlRegistreCommerce, {
        validators: [Validators.required],
      }),
      urlProfileImg: new FormControl(acteurRawValue.urlProfileImg, {
        validators: [Validators.required],
      }),
      urlResponsableImg: new FormControl(acteurRawValue.urlResponsableImg),
      createdBy: new FormControl(acteurRawValue.createdBy),
      createdDate: new FormControl(acteurRawValue.createdDate),
      lastModifiedBy: new FormControl(acteurRawValue.lastModifiedBy),
      lastModifiedDate: new FormControl(acteurRawValue.lastModifiedDate),
    });
  }

  getActeur(form: ActeurFormGroup): IActeur | NewActeur {
    return this.convertActeurRawValueToActeur(form.getRawValue() as ActeurFormRawValue | NewActeurFormRawValue);
  }

  resetForm(form: ActeurFormGroup, acteur: ActeurFormGroupInput): void {
    const acteurRawValue = this.convertActeurToActeurRawValue({ ...this.getFormDefaults(), ...acteur });
    form.reset(
      {
        ...acteurRawValue,
        id: { value: acteurRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ActeurFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      verifie: false,
      loginExpire: currentTime,
      createdDate: undefined,
      lastModifiedDate: undefined,
    };
  }

  private convertActeurRawValueToActeur(rawActeur: ActeurFormRawValue | NewActeurFormRawValue): IActeur | NewActeur {
    return {
      ...rawActeur,
      loginExpire: dayjs(rawActeur.loginExpire, DATE_TIME_FORMAT),
      createdDate: undefined,
      lastModifiedDate: undefined,
    };
  }

  private convertActeurToActeurRawValue(
    acteur: IActeur | (Partial<NewActeur> & ActeurFormDefaults)
  ): ActeurFormRawValue | PartialWithRequiredKeyOf<NewActeurFormRawValue> {
    return {
      ...acteur,
      loginExpire: acteur.loginExpire ? acteur.loginExpire.format(DATE_TIME_FORMAT) : undefined,
      createdDate: undefined,
      lastModifiedDate: undefined,
    };
  }
}

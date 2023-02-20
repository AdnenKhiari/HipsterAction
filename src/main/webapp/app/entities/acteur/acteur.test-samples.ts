import dayjs from 'dayjs/esm';

import { IActeur, NewActeur } from './acteur.model';

export const sampleWithRequiredData: IActeur = {
  id: 25798,
  verifie: false,
  loginExpire: dayjs('2023-02-19T03:17'),
  email: 'Arthaud38@yahoo.fr',
  nom: 'application lime',
  nomResponsable: 'back-end Zloty',
  numTel: 'Cambridgeshire array firewall',
  addresse: 'systematic Tasty',
  matriculeFiscale: 'Customizable',
  urlRegistreCommerce: 'Dahomey Bosnie-Herzégovine Concrete',
  urlProfileImg: 'Wooden',
};

export const sampleWithPartialData: IActeur = {
  id: 36558,
  verifie: false,
  loginExpire: dayjs('2023-02-19T13:25'),
  email: 'Apollinaire.Fleury@yahoo.fr',
  nom: 'input Music',
  nomResponsable: 'Ingenieur',
  numTel: 'Account repurpose',
  addresse: 'tertiary',
  matriculeFiscale: 'XSS invoice',
  urlRegistreCommerce: 'bluetooth indexing',
  urlProfileImg: 'reboot Car tan',
  createdBy: 'b',
  createdDate: dayjs('2023-02-19T18:40'),
  lastModifiedBy: 'Consultant',
};

export const sampleWithFullData: IActeur = {
  id: 59303,
  verifie: false,
  loginExpire: dayjs('2023-02-19T17:16'),
  email: 'Christian.Benoit19@hotmail.fr',
  nom: 'aX',
  nomResponsable: 'contextually-based',
  numTel: 'deposit',
  addresse: 'Laffitte FTP a',
  matriculeFiscale: 'solid Checking Centre',
  urlRegistreCommerce: 'Franc Berkshire',
  urlProfileImg: 'orchestrate Avon',
  urlResponsableImg: 'Avon',
  createdBy: 'c',
  createdDate: dayjs('2023-02-19T17:20'),
  lastModifiedBy: 'Market Assimilated',
  lastModifiedDate: dayjs('2023-02-18T22:04'),
};

export const sampleWithNewData: NewActeur = {
  verifie: true,
  loginExpire: dayjs('2023-02-19T12:30'),
  email: 'Oger.Olivier@hotmail.fr',
  nom: 'Total de c',
  nomResponsable: 'Nord-Pas-de-Calais overriding',
  numTel: 'Cambridgeshire target Optimized',
  addresse: 'Producteur Île-de-France salmon',
  matriculeFiscale: 'Richelieu',
  urlRegistreCommerce: 'Manager compress Soft',
  urlProfileImg: 'a Shirt',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

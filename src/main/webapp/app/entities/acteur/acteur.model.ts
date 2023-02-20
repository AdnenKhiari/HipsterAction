import dayjs from 'dayjs/esm';

export interface IActeur {
  id: number;
  verifie?: boolean | null;
  loginExpire?: dayjs.Dayjs | null;
  email?: string | null;
  nom?: string | null;
  nomResponsable?: string | null;
  numTel?: string | null;
  addresse?: string | null;
  matriculeFiscale?: string | null;
  urlRegistreCommerce?: string | null;
  urlProfileImg?: string | null;
  urlResponsableImg?: string | null;
  createdBy?: string | null;
  createdDate?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
  lastModifiedDate?: dayjs.Dayjs | null;
}

export type NewActeur = Omit<IActeur, 'id'> & { id: null };

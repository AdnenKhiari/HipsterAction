import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IActeur, NewActeur } from '../acteur.model';

export type PartialUpdateActeur = Partial<IActeur> & Pick<IActeur, 'id'>;

type RestOf<T extends IActeur | NewActeur> = Omit<T, 'loginExpire' | 'createdDate' | 'lastModifiedDate'> & {
  loginExpire?: string | null;
  createdDate?: string | null;
  lastModifiedDate?: string | null;
};

export type RestActeur = RestOf<IActeur>;

export type NewRestActeur = RestOf<NewActeur>;

export type PartialUpdateRestActeur = RestOf<PartialUpdateActeur>;

export type EntityResponseType = HttpResponse<IActeur>;
export type EntityArrayResponseType = HttpResponse<IActeur[]>;

@Injectable({ providedIn: 'root' })
export class ActeurService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/acteurs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(acteur: NewActeur): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(acteur);
    return this.http
      .post<RestActeur>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(acteur: IActeur): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(acteur);
    return this.http
      .put<RestActeur>(`${this.resourceUrl}/${this.getActeurIdentifier(acteur)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(acteur: PartialUpdateActeur): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(acteur);
    return this.http
      .patch<RestActeur>(`${this.resourceUrl}/${this.getActeurIdentifier(acteur)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestActeur>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestActeur[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getActeurIdentifier(acteur: Pick<IActeur, 'id'>): number {
    return acteur.id;
  }

  compareActeur(o1: Pick<IActeur, 'id'> | null, o2: Pick<IActeur, 'id'> | null): boolean {
    return o1 && o2 ? this.getActeurIdentifier(o1) === this.getActeurIdentifier(o2) : o1 === o2;
  }

  addActeurToCollectionIfMissing<Type extends Pick<IActeur, 'id'>>(
    acteurCollection: Type[],
    ...acteursToCheck: (Type | null | undefined)[]
  ): Type[] {
    const acteurs: Type[] = acteursToCheck.filter(isPresent);
    if (acteurs.length > 0) {
      const acteurCollectionIdentifiers = acteurCollection.map(acteurItem => this.getActeurIdentifier(acteurItem)!);
      const acteursToAdd = acteurs.filter(acteurItem => {
        const acteurIdentifier = this.getActeurIdentifier(acteurItem);
        if (acteurCollectionIdentifiers.includes(acteurIdentifier)) {
          return false;
        }
        acteurCollectionIdentifiers.push(acteurIdentifier);
        return true;
      });
      return [...acteursToAdd, ...acteurCollection];
    }
    return acteurCollection;
  }

  protected convertDateFromClient<T extends IActeur | NewActeur | PartialUpdateActeur>(acteur: T): RestOf<T> {
    return {
      ...acteur,
      loginExpire: acteur.loginExpire?.toJSON() ?? null,
      createdDate: acteur.createdDate?.toJSON() ?? null,
      lastModifiedDate: acteur.lastModifiedDate?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restActeur: RestActeur): IActeur {
    return {
      ...restActeur,
      loginExpire: restActeur.loginExpire ? dayjs(restActeur.loginExpire) : undefined,
      createdDate: restActeur.createdDate ? dayjs(restActeur.createdDate) : undefined,
      lastModifiedDate: restActeur.lastModifiedDate ? dayjs(restActeur.lastModifiedDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestActeur>): HttpResponse<IActeur> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestActeur[]>): HttpResponse<IActeur[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}

import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IActeur } from '../acteur.model';
import { ActeurService } from '../service/acteur.service';

@Injectable({ providedIn: 'root' })
export class ActeurRoutingResolveService implements Resolve<IActeur | null> {
  constructor(protected service: ActeurService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IActeur | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((acteur: HttpResponse<IActeur>) => {
          if (acteur.body) {
            return of(acteur.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}

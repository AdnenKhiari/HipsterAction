import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ActeurComponent } from '../list/acteur.component';
import { ActeurDetailComponent } from '../detail/acteur-detail.component';
import { ActeurUpdateComponent } from '../update/acteur-update.component';
import { ActeurRoutingResolveService } from './acteur-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const acteurRoute: Routes = [
  {
    path: '',
    component: ActeurComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ActeurDetailComponent,
    resolve: {
      acteur: ActeurRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ActeurUpdateComponent,
    resolve: {
      acteur: ActeurRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ActeurUpdateComponent,
    resolve: {
      acteur: ActeurRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(acteurRoute)],
  exports: [RouterModule],
})
export class ActeurRoutingModule {}

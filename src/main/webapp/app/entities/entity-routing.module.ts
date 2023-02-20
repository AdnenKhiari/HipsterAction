import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'acteur',
        data: { pageTitle: 'javertestApp.acteur.home.title' },
        loadChildren: () => import('./acteur/acteur.module').then(m => m.ActeurModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}

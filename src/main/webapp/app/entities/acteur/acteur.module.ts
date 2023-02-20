import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ActeurComponent } from './list/acteur.component';
import { ActeurDetailComponent } from './detail/acteur-detail.component';
import { ActeurUpdateComponent } from './update/acteur-update.component';
import { ActeurDeleteDialogComponent } from './delete/acteur-delete-dialog.component';
import { ActeurRoutingModule } from './route/acteur-routing.module';

@NgModule({
  imports: [SharedModule, ActeurRoutingModule],
  declarations: [ActeurComponent, ActeurDetailComponent, ActeurUpdateComponent, ActeurDeleteDialogComponent],
})
export class ActeurModule {}

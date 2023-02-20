import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ActeurFormService, ActeurFormGroup } from './acteur-form.service';
import { IActeur } from '../acteur.model';
import { ActeurService } from '../service/acteur.service';

@Component({
  selector: 'jhi-acteur-update',
  templateUrl: './acteur-update.component.html',
})
export class ActeurUpdateComponent implements OnInit {
  isSaving = false;
  acteur: IActeur | null = null;

  editForm: ActeurFormGroup = this.acteurFormService.createActeurFormGroup();

  constructor(
    protected acteurService: ActeurService,
    protected acteurFormService: ActeurFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ acteur }) => {
      this.acteur = acteur;
      if (acteur) {
        this.updateForm(acteur);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const acteur = this.acteurFormService.getActeur(this.editForm);
    if (acteur.id !== null) {
      this.subscribeToSaveResponse(this.acteurService.update(acteur));
    } else {
      this.subscribeToSaveResponse(this.acteurService.create(acteur));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IActeur>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(acteur: IActeur): void {
    this.acteur = acteur;
    this.acteurFormService.resetForm(this.editForm, acteur);
  }
}

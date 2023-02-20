import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IActeur } from '../acteur.model';

@Component({
  selector: 'jhi-acteur-detail',
  templateUrl: './acteur-detail.component.html',
})
export class ActeurDetailComponent implements OnInit {
  acteur: IActeur | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ acteur }) => {
      this.acteur = acteur;
    });
  }

  previousState(): void {
    window.history.back();
  }
}

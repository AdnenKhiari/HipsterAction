import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ActeurDetailComponent } from './acteur-detail.component';

describe('Acteur Management Detail Component', () => {
  let comp: ActeurDetailComponent;
  let fixture: ComponentFixture<ActeurDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ActeurDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ acteur: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(ActeurDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ActeurDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load acteur on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.acteur).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});

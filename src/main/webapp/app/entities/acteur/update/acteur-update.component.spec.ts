import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ActeurFormService } from './acteur-form.service';
import { ActeurService } from '../service/acteur.service';
import { IActeur } from '../acteur.model';

import { ActeurUpdateComponent } from './acteur-update.component';

describe('Acteur Management Update Component', () => {
  let comp: ActeurUpdateComponent;
  let fixture: ComponentFixture<ActeurUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let acteurFormService: ActeurFormService;
  let acteurService: ActeurService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ActeurUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(ActeurUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ActeurUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    acteurFormService = TestBed.inject(ActeurFormService);
    acteurService = TestBed.inject(ActeurService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const acteur: IActeur = { id: 456 };

      activatedRoute.data = of({ acteur });
      comp.ngOnInit();

      expect(comp.acteur).toEqual(acteur);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IActeur>>();
      const acteur = { id: 123 };
      jest.spyOn(acteurFormService, 'getActeur').mockReturnValue(acteur);
      jest.spyOn(acteurService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ acteur });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: acteur }));
      saveSubject.complete();

      // THEN
      expect(acteurFormService.getActeur).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(acteurService.update).toHaveBeenCalledWith(expect.objectContaining(acteur));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IActeur>>();
      const acteur = { id: 123 };
      jest.spyOn(acteurFormService, 'getActeur').mockReturnValue({ id: null });
      jest.spyOn(acteurService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ acteur: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: acteur }));
      saveSubject.complete();

      // THEN
      expect(acteurFormService.getActeur).toHaveBeenCalled();
      expect(acteurService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IActeur>>();
      const acteur = { id: 123 };
      jest.spyOn(acteurService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ acteur });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(acteurService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});

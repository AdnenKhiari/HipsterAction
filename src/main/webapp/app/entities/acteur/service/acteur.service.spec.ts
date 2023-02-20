import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IActeur } from '../acteur.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../acteur.test-samples';

import { ActeurService, RestActeur } from './acteur.service';

const requireRestSample: RestActeur = {
  ...sampleWithRequiredData,
  loginExpire: sampleWithRequiredData.loginExpire?.toJSON(),
  createdDate: sampleWithRequiredData.createdDate?.toJSON(),
  lastModifiedDate: sampleWithRequiredData.lastModifiedDate?.toJSON(),
};

describe('Acteur Service', () => {
  let service: ActeurService;
  let httpMock: HttpTestingController;
  let expectedResult: IActeur | IActeur[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ActeurService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a Acteur', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const acteur = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(acteur).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Acteur', () => {
      const acteur = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(acteur).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Acteur', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Acteur', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Acteur', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addActeurToCollectionIfMissing', () => {
      it('should add a Acteur to an empty array', () => {
        const acteur: IActeur = sampleWithRequiredData;
        expectedResult = service.addActeurToCollectionIfMissing([], acteur);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(acteur);
      });

      it('should not add a Acteur to an array that contains it', () => {
        const acteur: IActeur = sampleWithRequiredData;
        const acteurCollection: IActeur[] = [
          {
            ...acteur,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addActeurToCollectionIfMissing(acteurCollection, acteur);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Acteur to an array that doesn't contain it", () => {
        const acteur: IActeur = sampleWithRequiredData;
        const acteurCollection: IActeur[] = [sampleWithPartialData];
        expectedResult = service.addActeurToCollectionIfMissing(acteurCollection, acteur);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(acteur);
      });

      it('should add only unique Acteur to an array', () => {
        const acteurArray: IActeur[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const acteurCollection: IActeur[] = [sampleWithRequiredData];
        expectedResult = service.addActeurToCollectionIfMissing(acteurCollection, ...acteurArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const acteur: IActeur = sampleWithRequiredData;
        const acteur2: IActeur = sampleWithPartialData;
        expectedResult = service.addActeurToCollectionIfMissing([], acteur, acteur2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(acteur);
        expect(expectedResult).toContain(acteur2);
      });

      it('should accept null and undefined values', () => {
        const acteur: IActeur = sampleWithRequiredData;
        expectedResult = service.addActeurToCollectionIfMissing([], null, acteur, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(acteur);
      });

      it('should return initial array if no Acteur is added', () => {
        const acteurCollection: IActeur[] = [sampleWithRequiredData];
        expectedResult = service.addActeurToCollectionIfMissing(acteurCollection, undefined, null);
        expect(expectedResult).toEqual(acteurCollection);
      });
    });

    describe('compareActeur', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareActeur(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareActeur(entity1, entity2);
        const compareResult2 = service.compareActeur(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareActeur(entity1, entity2);
        const compareResult2 = service.compareActeur(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareActeur(entity1, entity2);
        const compareResult2 = service.compareActeur(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});

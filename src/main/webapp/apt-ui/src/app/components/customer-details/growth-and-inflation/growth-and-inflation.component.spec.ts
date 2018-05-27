import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GrowthAndInflationComponent } from './growth-and-inflation.component';

describe('GrowthAndInflationComponent', () => {
  let component: GrowthAndInflationComponent;
  let fixture: ComponentFixture<GrowthAndInflationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GrowthAndInflationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GrowthAndInflationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

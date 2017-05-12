import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NuevoComp1Component } from './nuevo-comp-1.component';

describe('NuevoComp1Component', () => {
  let component: NuevoComp1Component;
  let fixture: ComponentFixture<NuevoComp1Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NuevoComp1Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NuevoComp1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

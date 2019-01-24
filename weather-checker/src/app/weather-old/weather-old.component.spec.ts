import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WeatherOldComponent } from './weather-old.component';

describe('WeatherOldComponent', () => {
  let component: WeatherOldComponent;
  let fixture: ComponentFixture<WeatherOldComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WeatherOldComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WeatherOldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

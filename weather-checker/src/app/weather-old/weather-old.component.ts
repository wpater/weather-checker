import { Component, OnInit } from '@angular/core';
import { Observable } from  "rxjs";
import {tap} from 'rxjs/internal/operators';
import { HttpClient } from  "@angular/common/http";

class  Weather {
  name: string;
  temp: number;
  date: string;
}

@Component({
  selector: 'app-weather-old',
  templateUrl: './weather-old.component.html',
  styleUrls: ['./weather-old.component.css']
})
export class WeatherOldComponent implements OnInit {

  locationsObservable: Observable<Weather[][]>;

  constructor(private httpClient:HttpClient) {}

  ngOnInit() {
    this.locationsObservable = this.httpClient
      .get<Weather[]>("http://localhost:8080/weather/prev")
      .pipe(tap(console.log));
  }

}

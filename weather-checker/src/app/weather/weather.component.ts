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
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent implements OnInit {

  weatherObservable: Observable<Weather[]>;

  constructor(private httpClient:HttpClient) {}

  ngOnInit() {
    this.weatherObservable = this.httpClient
      .get<Weather[]>("http://localhost:8080/weather")
  }

}

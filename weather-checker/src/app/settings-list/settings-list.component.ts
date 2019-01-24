import { Component, OnInit } from '@angular/core';
import { Observable } from  "rxjs";
import {tap} from 'rxjs/internal/operators';
import { HttpClient } from  "@angular/common/http";

@Component({
  selector: 'app-settings-list',
  templateUrl: './settings-list.component.html',
  styleUrls: ['./settings-list.component.css']
})
export class SettingsListComponent implements OnInit {

  locationsObservable: Observable<string[]>;
  autoObservable: Observable<string>;

  constructor(private httpClient:HttpClient) {}

  ngOnInit() {
    this.locationsObservable = this.httpClient
      .get<string[]>("http://localhost:8080/settings/location")
    this.autoObservable = this.httpClient
      .get("http://localhost:8080/settings/detect", {responseType: 'text'})
  }

}

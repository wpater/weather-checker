import { Component, OnInit } from '@angular/core';
import { Observable } from  "rxjs";
import {tap} from 'rxjs/internal/operators';
import { HttpClient } from  "@angular/common/http";
import {HttpParams} from  "@angular/common/http";

@Component({
  selector: 'app-settings-update',
  templateUrl: './settings-update.component.html',
  styleUrls: ['./settings-update.component.css']
})
export class SettingsUpdateComponent implements OnInit {

  constructor(private httpClient:HttpClient) {}

  ngOnInit() { }

  onSubmit(form) {
    if (form.value.addLocation) {
      const addParams = new  HttpParams().set('location', form.value.addLocation);
      this.httpClient.post("http://127.0.0.1:8080/settings/location/add", addParams)
      .subscribe(
        data  => {
        console.log("POST Request is successful ", data);
      },
      error  => {
        console.log("Error", error);
      });
    }

    if (form.value.delLocation) {
      const delParams = new  HttpParams().set('location', form.value.delLocation);
      this.httpClient.post("http://127.0.0.1:8080/settings/location/del", delParams)
      .subscribe(
        data  => {
        console.log("POST Request is successful ", data);
      },
      error  => {
        console.log("Error", error);
      });
    }

    if (form.value.auto) {
      const autoParams = new  HttpParams().set('auto', form.value.auto);
      this.httpClient.post("http://127.0.0.1:8080/settings/detect", autoParams)
      .subscribe(
        data  => {
        console.log("POST Request is successful ", data);
      },
      error  => {
        console.log("Error", error);
      });
    }
  }

}

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WeatherComponent } from './weather/weather.component';
import { WeatherOldComponent } from './weather-old/weather-old.component';
import { SettingsUpdateComponent } from './settings-update/settings-update.component';
import { SettingsListComponent } from './settings-list/settings-list.component';

@NgModule({
  declarations: [
    AppComponent,
    WeatherComponent,
    WeatherOldComponent,
    SettingsUpdateComponent,
    SettingsListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

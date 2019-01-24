import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {WeatherComponent} from './weather/weather.component';
import {WeatherOldComponent} from './weather-old/weather-old.component';
import {SettingsListComponent} from './settings-list/settings-list.component';
import {SettingsUpdateComponent} from './settings-update/settings-update.component';

const routes: Routes = [
  { path:  '', pathMatch:  'full', redirectTo:  'weather'},
  { path: 'weather', component: WeatherComponent},
  { path: 'weather/prev', component: WeatherOldComponent},
  { path: 'settings', component: SettingsListComponent},
  { path: 'settings/update', component: SettingsUpdateComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

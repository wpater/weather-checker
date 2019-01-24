# Weather Checker
Simple application for checking weather in few locations.

## Configure

In `weather-checker/src/main/resources/application.properties` declare list of locations to check weather and set option for autodetect location.
Weather will be checked once every 10 minutes and stored in database.
Each request at `/weather` is additional check.

## Build and run server

`mvn install`

`java -jar target/weather_checker-1.0-SNAPSHOT.jar `

For run tests:
`mvn test`

## Build and run client

`cd weather-checker`

`npm update`

`ng serve -o` - the browser will open

## Client - functions

* `/weather` - weather for declared locations
* `/weather/prev` - stored weather for declared locations
* `/settings` - already loaded settings on server
* `/settings/update` - modify settings (add/remove locations, change autodetection)

## Server - API

* `Post at /settings/location/add?location=string` - add new location
* `Post at /settings/location/del?location=string` - delete location
* `Post at /settings/detect?auto=string` - set autodetection
* `Get at /settings/location` - get list of locations
* `Get at /settings/detect` - get autodetection status
* `Get at /weather` - get weather for declared locations
* `Get at /weather/prev` - get stored weather for declared locations
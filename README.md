# ND035-P02-VehiclesAPI-Project

Project repository for JavaND Project 2, where students implement a Vehicles API using Java and Spring Boot that can communicate with separate location and pricing services.

## Instructions

Check each component to see its details and instructions. Note that all three applications
should be running at once for full operation. Further instructions are available in the classroom.

- [Vehicles API](vehicles-api/README.md)
- [Pricing Service](pricing-service/README.md)
- [Boogle Maps](boogle-maps/README.md)

## Dependencies

The project requires the use of Maven and Spring Boot, along with Java v11.

```sh
curl --request POST \
  --url http://localhost:8080/cars \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=FF0D09BEA0E502D7B211FA9CA5E3F127 \
  --data '{
   "id":null,
   "createdAt":null,
   "modifiedAt":null,
   "condition":"USED",
   "details":{
      "body":"Sedan",
      "model":"Cronos",
      "manufacturer":{
         "code":101,
         "name":"Fiat"
      },
      "numberOfDoors":4,
      "fuelType":"Gasoline",
      "engine":"1.8L V16",
      "mileage":26000,
      "modelYear":2018,
      "productionYear":2018,
      "externalColor":"White"
   },
   "location":{
      "lat":40.73061,
      "lon":-73.935242,
      "address":null,
      "city":null,
      "state":null,
      "zip":null
   },
   "price":null
}'

curl --request PUT \
  --url http://localhost:8080/cars/1 \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=FF0D09BEA0E502D7B211FA9CA5E3F127 \
  --data '{
   "id":null,
   "createdAt":null,
   "modifiedAt":null,
   "condition":"USED",
   "details":{
      "body":"Sedan",
      "model":"Cronos Precision",
      "manufacturer":{
         "code":101,
         "name":"Fiat"
      },
      "numberOfDoors":4,
      "fuelType":"Gasoline",
      "engine":"1.8L V16",
      "mileage":26000,
      "modelYear":2018,
      "productionYear":2018,
      "externalColor":"White"
   },
   "location":{
      "lat":40.73061,
      "lon":-73.935242,
      "address":null,
      "city":null,
      "state":null,
      "zip":null
   },
   "price":null
}'
```

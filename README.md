# xtreme-camp-application

The solution consists in the definition of 5 microservices:

* eureka-server: Spring Eureka Server Instance for the Service Registry for all the deployed microservices. Eureka Server can see at "http://localhost:8888/
".
* extreme-camp-campsite-service: Contains the Rest API for the management of CampSite information: number of bookings per day, max number of days before the booking init date, min number of days before the booking init date, max number of days for a daterange booking. The complete API can see at "localhost:8101/swagger-ui.html".
* extreme-camp-customer-service: Contains the Rest API for the managemente of customers: create, update, delete, find by email. The complete API can see at "localhost:8201/swagger-ui.html".
* extreme-camp-booking-service: Contains the Rest API for the management of Bookings: create, update, cancel, validate a daterange for booking. The complete API can see at "localhost:8301/swagger-ui.html".
* extreme-camp-api-service: Contains the Main Rest API for hidding details of each microservice behind. The complete API can see at "localhost:8401/swagger-ui.html".

# "DB SCRIPTS" FOLDER

"DB SCRIPTS XTREMECAMP.txt" Contains the SQL Scripts for the creation of CAMPSITES, CUSTOMERS, BOOKINGS tables, and the insert sentence for the creation of CAMPSITE "VolcanoCamp".

# "JMETER LOAD TEST PROJECT" FOLDER

LoadTest through JMeter project "EXTREMECAMP.jmx", using a csv file "prueba.csv" for the simulation of customers making reservations.

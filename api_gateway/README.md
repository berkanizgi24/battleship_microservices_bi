#Assignment 5 GATEWAY

Es wurde ein API GATEWAY erstellt, sodass die Kommunikation zwischen dem Dashboard und allen verschiedenen Services zentral regelt. 
Es dient als Einstiegspunkt für alle Anfragen und leitet diese automatisch an den passenden Service weiter.


Damit das funktioniert, wurde der API Gateway als eigenes Spring Boot Modul erstellt und so konfiguriert, dass es 
sich beim Eureka Discovery Server registriert. Zusätzlich wurden in einer Konfigurationsklasse Weiterleitungen (Routen) definiert,
die Anfragen wie `/api/games` automatisch an den Game-Service schicken.



Assignment 4:

Discovery Server

Der Discovery Server ist ein zentraler Teil des Projekts.  
Er ist dafür zuständig, dass sich alle Microservices automatisch finden können.  
Dazu nutzt er das Eureka-System von Spring Cloud.

Aufgaben des Discovery Servers:
- bietet zentrale Registrierungsstelle
- meldet sich beim Start sozusagen hier an
- andere Service können über den Namen des Ziel-Services Anfragen senden.


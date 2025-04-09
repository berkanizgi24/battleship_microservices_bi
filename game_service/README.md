Game-Service

Der GameService verwaltet die Spiellogik

Er hat folgende Funktionalitäten:

* Erstellt neue Spieler
* Liefert alle Spiele
* Prüft, ob ein Spiel existiert
* Prüft, ob noch Spieler hinzugefügt werden können

Hier wurden ebenfalls Circuit Breaker hinzugefügt, sobald eine Methode auf einen anderen
Microservice zugreifen möchte.

(Läuft trotz dashboard auf Port 8081, kann man entfernen)

Assignment 3: RabbitMQ

Der GameService empfängt nun eine Nachricht, wenn der PlayerService einen Player (zum Spiel)
erstellt. Dieser wird im Terminal vom GameService jetzt ausgegeben. 

Durch Docker konnte man localhost:15672 starten und mit der Anmeldung (guest, guest) die 
Ergebnisse mitverfolgen. 



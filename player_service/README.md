Player-Service

Der Player-Service verwaltet Spieler und deren Schiffe. 

Er hat folgende Funktionalitäten:

* Erstellt neue Spiele
* Liefert alle Spieler eines Spiels
* Setzt Schiffe
* Liefert Schiffe eines Spiels

Ebenfalls Circuit Breaker eingesetzt, sobald ein Aufruf eines anderen Services stattfindet.

(Läuft trotz dashboard auf Port 8082, kann man entfernen)

Assignment 3: RabbitMQ

Der PlayerService sendet eine Nachricht an den GameService sobald ein Player erstellt wurde.
Diese wird dann im Terminal des Empfängers angezeigt. Der PlayerService ist auch ein Empfänger 
des Shot-Services, der dann berichtet, wann ein Schuss vom Spieler abgefeuert wurde. Also 
der PlayerService hat 2 queues in der Configuration. 
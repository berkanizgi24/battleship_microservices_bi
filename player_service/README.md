Player-Service

Der Player-Service verwaltet Spieler und deren Schiffe. 

Er hat folgende Funktionalitäten:

* Erstellt neue Spiele
* Liefert alle Spieler eines Spiels
* Setzt Schiffe
* Liefert Schiffe eines Spiels

Ebenfalls Circuit Breaker eingesetzt, sobald ein Aufruf eines anderen Services stattfindet.

(Läuft trotz dashboard auf Port 8082, kann man entfernen)
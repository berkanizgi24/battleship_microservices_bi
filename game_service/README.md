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



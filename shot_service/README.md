Shot-Service

Der Shot-Service verwaltet die Schüsse der Spieler.

Er hat folgende Funktionalitäten:

* Speichert abgegebene Schüsse
* Prüft automatisch, ob der Schuss ein Treffer war

Circuit Breaker ebenfalls eingesetzt, aufgrund Zugriff anderer Services.

(Läuft trotz dashboard auf Port 8083, kann man entfernen)

Assignment 3: RabbitMQ

Der ShotService sendet eine Nachricht an den PlayerService, sobald ein Schuss abgefeuert wurde.
Wird im Terminal von PlayerService (Empfänger) angezeigt.

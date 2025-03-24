Dashboard-Service

Durch das Dashboard wird dem Nutzer eine zentrale Benutzeroberfläche des Projekts geboten.
Diese kommuniziert mit allen anderen Microservices.

Es hat folgende Funktionalitäten:
* Erstellt neue Spiele
* Zeigt alle Spiele an
* Fügt neue Spieler hinzu
* Setzt Schiffe der Spieler
* Feuert Schüsse auf gegnerische Schiffe

Die DashboardService.java Datei verwendet bei jeder Methode einen Circuit Breaker, da sie
direkt auf andere Microservices zugreif. Dadurch wird sichergestellt, dass der zentrale
Zugriffspunkt des Systems auch bei Ausfällen einzelner Services stabil bleibt.

Läuft auf Port 8080 im Swagger: http://localhost:8080/swagger-ui/index.html#
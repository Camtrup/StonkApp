## INNLEVERING 1

## FUNKSJONALITET:
Vi har fått til å få funksjonaliteten for å søke opp en aksje fra marketwatch.com og få pris, navn og ticker for nå, i Stonk.java. 
Planen er å senere kunne ta ut enda mer informasjon for hver aksje. 
Dette ble gjort med market scraping ved hjelp av Jsoup som det står mer om i ReadME filen.
Mye av tiden har gått på å få login og registrer bruker sidene til å funkere. 
Vi har også hatt problemer med å overføre informasjon fra de forskjellige javafxml filene til en controller. 
Derfor fant vi ut at det var bedre å lage en controller klas for hver fxml fil.

## FIL LAGRING: 
Vi bruker databas.json filen for å lagre kontoen til brukere. Her mangler vi kun å få lagret aksjene en person har kjøpt og ønsker å kjøpe.


## JAVA FXML:
Til nå så har vi 3 ganske ferdige javafxml sider. Totalt vil det være 5 sider. 
De 2 siste har vi ennå ikke fått laget. 
ferdiges sider:
- Login.fxml er for innloging siden. 
- mainPage.fxml er for hovedsiden man kommer inn på
- newUser.fxml er for å registrere nye brukere.

ikke ferdige sider:
- stockPage.fxml: siden som vises for en stock når du prøver å kjøpe den.
- myProfile.fxml: usikker på om vi trenger dette, så vi tar den senere
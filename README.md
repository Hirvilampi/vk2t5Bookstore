#vk3 tehtava Bookstore jatko
Aika paljon on mennyt koodi uusiksi. Ensin ihan sen takia, että tein oman version eri nimillä, kuin alkuperäisen StudentList:n mukaan. Tässä sitten oli monta ongelmaa, kun ei oikein tiennyt mitä mikäkin tarkoittaa. Toisaalta tuo nimeämisen muutos oli hyvä edellisen viikon harjoituksen kannalta, että pystyi paremmin ymmärtämään mikä vie html tiedostoon ja mikä controllerin toiseen kohtaan. 

Vielä olisi tuo ylimääräinen tehtävä tehtävänä. Katsotaan miten sen suhteeeen. 

1.2.2025 bookstrap oli kyllä kiva opetella käyttämään. 
Sain esiin ongelman, että editbook.html:ssä bookstrappiä ladatessa, tuli mukaan ylimääräinen kansio " http://localhost:8080/editbook/css/bootstrap.min.css", eli tuo editbook
Mietin, että jotenkin se liittyy tuohon linkkiin ja miten sitä käsitellään.. sain sen pois lisäämällä linkkikoodiin / ennen, jolloin tuo relatiivisuus editbookiin katosi. Se toki jäi vielä mysteeriksi, miksi se lisäsi sen editbookin sinne eteen tässä editbook.html tiedostossa, mutta ei esim addbook.html tiedostossa, mikä on käytännössä sama.
Alkuperäinen linkki, joka toimi addbookissa, mutta ei editbookissa
 <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" th:href="@{css/bootstrap.min.css}" />
korjattu linkki editbookiin ja muihin sekä toimii niissä
 <link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" th:href="@{/css/bootstrap.min.css}" />

#vk2 tehtava 5 Bookstore
tämä oli yllättävän helppo, kun oli tehnyt jo edellisen tehtävän kokonaan omanaan
mutta aina tulee pieniä kirjoitusvirheitä yms "ongelmia", joiden kanssa saa hetken painia

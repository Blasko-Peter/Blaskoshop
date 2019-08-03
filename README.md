# Blasko Online Shop

Egy web áruház ahol user-ek regisztrálhatnak és az áruház kínálatából rendelhetnek kedvükre.

# Technológiák

A project jetty server alatt fut, java servlet-et használva. A front end-et HTML és CSS, míg a backend-et java biztosítja. A front end és a back end közötti kommunikációra a javascript is a segítségemre volt. Az adatbázist pedig egy postgreSQL adatbázis biztosítja.

# Kihívások

A feladat elég összetett volt, sok technológiát kellett használni és összehangolni. Rengeteg user story-t kellett megvalósítani ahhoz, hogy ez a project megfelelő módon tudjon működni. A tervezés rengeteg fejfájást okozott. Többször újra kellett tervezni a projektet.

# Eredmény

A webshop-nál session-ökkel oldottam meg egyes oldalak elérhetőségét. Így kezdetben csak a main, login, registration oldalakat lehet elérni. Regisztráció és Login után már a többi funkció is elérhető lesz a user számára. Az ADMIN tud feltölteni újabb és újabb productokat, supplier-eket, illetve categories. A user-ek belépés nélkül is képesek végignézni a product-okat és tudnak supplier-re és/vagy category-ra szűrve is keresni. A user regisztrálás és login után képes vásárolni. Terméket tehet a shopcart-ba, majd a termékek számát változtathatjuk még a shopcart-ban is. A megrendelés befejezéséhez még egy szállítási címet kell beállítani, amennyiben a user már adott le megrendelést, akkor a főoldalon a history gombra kattintva megnézhati előző vásárlásainak történetét, míg a megrendelés oldalon kiválaszthatja a már megadott szállítási címét is.

A projekt visszaadja egy web shop érzést.

# További fejlesztések

Amennyiben megoldásra kerül, hogy a Shopcart-ba csak akkor lehessen belépni, ha legalább egy termék már van benne, és amennyiben a user a shopcart-ban tartózkodva minden terméket kitöröl, akkor automatikusan kerüljön vissza a főoldalra, akkor már szinte profi web shop is lehetne. 

# Installáció

A projekt működéséhez szükséges adatbázis vázlata megtalálható a project database sql mappájában található sql file-ban. A database telepítése után az adatbázisban létre kell hozni az első Address-t ami majd az ADMIN-hoz fog tartozni. Ezek után regisztrálni kell, vagy létre kell hozni az ADMIN felhasználót admin@admin.hu felhasználónévvel és egy saját password-del. Ezek után ADMIN-ként belépve fel tudunk tölteni supplier-eket, és categories. Majd ezt követően létre tudunk hozni product-okat. Ezt követően a webshop készen áll a user-ek fogadására.

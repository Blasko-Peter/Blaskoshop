# Blasko Online Shop

Java SE: Web Project

# Task

A feladat az volt, hogy hozzak létre egy online webshop-ot, ahol vannak product-ok, a product-oknak vannak supplier-ei és category-ái is. Ezeket csak az ADMIN tudja hozzáadni a projekthez. A user-ek pedig saját felhasználónevükkel és password-jükkel tudjanak regisztrálni és utána belépni a shop felületére. A user-ek vásárlásaikat rögzíteni kell és az aktuális megrendelést vissza kell adni.

# Technologies

A project jetty server alatt fut, java servlet-et használva. A front end-et html és css, míg a backend-et java biztosítja. A front end és a back end közötti kommunikációra használtam javascriptet is. Az adatbázist pedig egy postgreSQL adatbázis biztosítja.

# Kihívások

A feladat elég összetett volt, sok technológiát kellett használni és összehangolni. Rengeteg user story-t kellett megvalósítani ahhoz, hogy ez a project megfelelő módon tudjon működni.

# Project

A webshop-nál session-ökkel oldottam meg egyes oldalak elérhetőségét. Így kezdetben csak a main, login, registration oldalakat lehet elérni. Regisztráció és Login után már a többi funkció is elérhető lesz a user számára. Az ADMIN tud feltölteni újabb és újabb productokat, supplier-eket, illetve categories. A user-ek vásárlásaikat shopcart-okon tárolom, mindig csak egy aktív shopcart létezik. A befejezett vásárlásokat a user-ek a history-ban megnézhetik. A user-ek a megrendeléseiknél a kiszállítási címet megadhatják, vagy amennyiben már volt megrendelésük, akkor kiválaszthatják a régebben megadott címüket a cím listából.

# Installáció

A projekt működéséhez szükséges adatbázis vázlata megtalálható a project database sql mappájában található sql file-ban. A database telepítése után az adatbázisban létre kell hozni az első Address-t ami majd az ADMIN-hoz fog tartozni. Ezek után regisztrálni kell, vagy létre kell hozni az ADMIN felhasználót admin@admin.hu felhasználónévvel és egy saját password-del. Ezek után ADMIN-ként belépve fel tudunk tölteni supplier-eket, és categories. Majd ezt követően létre tudunk hozni product-okat. Ezt követően a webshop készen áll a user-ek fogadására. A user-ek belépés nélkül is képesek végignézni a product-okat és tudnak supplier-re és/vagy category-ra szűrve is keresni. A user regisztrálás és login után képes vásárolni. Terméket tehet a shopcart-ba, majd a termékek számát változtathatjuk még a shopcart-ban is. A megrendelés befejezéséhez még egy szállítási címet kell beállítani, amennyiben a user már adott le megrendelést, akkor a főoldalon a history gombra kattintva megnézhati előző vásárlásainak történetét, míg a megrendelés oldalon kiválaszthatja a már megadott szállítási címét is.

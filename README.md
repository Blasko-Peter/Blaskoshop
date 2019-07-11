# Blasko Online Shop

Java SE: Web Project

# A Feladat

A feladat az volt, hogy egy webshop alkalmazást építsek. A feladat akkor tekinthető teljesítettnek, ha működik a user login,
 supplier-t, category-t és product-ot is hozzá lehet adni. A user-eknek van egy shopcart-juk, amire product-okat tudnak hozzáadni,
 és annak végösszegét is láthatják. Majd a pontos cím megadása után a megrendelést rögzíteni kell az adatbázisban.

# Technológiák

A feladat megvalósításához Intellij ide-t használtam ahol egy jetty szervert futtattam. A routok kezelésére java servletet használtam.
Az adatbázisom egy PostgreSQL adatbázis volt. Míg a frontendhez HTML-t, css-t használtam természetesen Bootstrap 4-sel kiegészítve.
A frontend és backend közötti kommunikációhoz használtam Javascriptet és jQuerry-t is.

# Kihívás

Kihívást jelentett a feladat összetettsége. Hogy mindig újabb és újabb user story-kat akartam beépíteni a projektbe, így folyamatosan
 újra és újra kellett tervezni a projektet, újra kellett gondolni a megvalósítás fonalát.
 
# Eredmény

A webshop-ba admin@admin.hu felhasználónévvel belépve tudunk új supplier-t, category-t vagy product-ot hozzáadni a programhoz. A user-ek 
regisztrálni tudják magukat, és mindig van egy aktív shopkárt-juk melyre vásárolhatnak. A vásárlás befejeztével a pontos szállítási címet
meg lehet adni, majd a megrendelés megtörtént, a shopcart ordered státuszba kerül és egy új üres shopcart-kerül a user-hez. A program jelenleg
15 féle terméket tud egy shopcart-on tárolni, de ez természetesen növelhető. 

A program használható, de élvezeti értékén csökkent, hogy az admin a már bevitt adatokat nem tudja módosítani. Törölni sem tud, de azt nem is szerettük volna,
mert egy törölt product nagy gondot okozott volna egy régebbi shopcart behívásánál. A user-ek nem tudják lehívni a régebbi vásárlásaikat és jelenleg már meglévő
címüket sem tudják kiválasztani.

# További fejlesztési lehetőségek

- Szeretném megoldani a user-ek számára, hogy a már az adatbázisban lévő címüket használni tudják.
- Szeretném megvalósítani, hogy a user-ek korábbi megrendeléseiket is láthassák.
- Szeretném az admin jogosultságait bővíteni, hogy módosítani tudjon a már bevitt adatokon.
 

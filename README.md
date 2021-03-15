1. На основании запроса SELECT ID_ART, NAME, CODE, GUID, USERNAME FROM TABLE1 формируем XML вида 
//<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
//<articles>
//  <article id_art="1" name="Батон нарезной в/с=1" code="19561682" username="WHS-1" guid="1309850966925697043"/>
//</articles>

2. Осуществляем XSLT преобразование и приводим XML файл к виду 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<articles>
    <article>
        <id_art>1</id_art>
        <name>Батон нарезной в/с=1</name>
        <code>19561682</code>
        <username>WHS-1</username>
        <guid>1309850966925697043</guid>
    </article>
</articles>

3. Создаем CSV на основе последнего XML файла 

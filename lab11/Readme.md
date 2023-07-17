# Multi Release Jar
Na początku, w folderze, uruchomiono polecenie javac w celu skompilowania plików źródłowych aplikacji.

Następnie, wykonano polecenie jar w celu utworzenia pliku JAR o nazwie mrj.jar. Poniżej przedstawiono szczegóły tego polecenia:

Użyto flagi --create w celu utworzenia nowego archiwum JAR.

Użyto flagi --file z wartością mrj.jar w celu określenia nazwy i ścieżki docelowego pliku JAR.

Użyto flagi --main-class z wartością example.Main w celu określenia klasy głównej aplikacji.

Użyto flagi -C z wartością classes w celu dodania zawartości folderu classes do archiwum JAR.

Użyto flagi --release z wartościami 9, 10 i 11 w celu określenia wersji Javy dla poszczególnych części archiwum JAR.

Użyto flagi -C z wartościami bin/classes-9, bin/classes-10 i bin/classes-11 w celu dodania zawartości tych folderów do odpowiednich części archiwum JAR.


Po zakończeniu wykonywania polecenia jar, plik mrj.jar  został utworzony.

package pl.edu.pwr.tkubik.javata;

import javafx.application.Application;

public class Main {
    // Logika niniejszego programu została napisana głównie w pliku primary.fxml
    // Tam tworzone są komponenty interfejsu użytkownika i tam też są one obsługiwane
    // - w tym w skrypcje javascript
    //
    // Klasa App służy głównie do załadowania kodu primary.fxml
    //
    // Ponieważ w JDK 17 nie ma już silnika skryptowego, dołączono do projektu silnik NASHORN
    // Aby to wszystko zadziałało należy odpalić aplikację z następującymi parametrami dla wirtualnej maszyny:
    // -Djavafx.allowjs=true --add-modules jdk.dynalink --add-reads jdk.dynalink=ALL-UNNAMED --add-reads java.base=ALL-UNNAMED

    // Do uruchamiania niniejszej aplikacji można wykorzystać pluginy, sekwencja komend do wykonania:
    // 1. mvn test (by skopiowały się do target/compiler odpowiednie zależności
    // 2. mvm exec:exec
    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}

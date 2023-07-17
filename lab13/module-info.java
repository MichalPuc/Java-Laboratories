module pl.edu.pwr.tkubik.javata {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.scripting;

    opens pl.edu.pwr.tkubik.javata to javafx.fxml;
    exports pl.edu.pwr.tkubik.javata;
}

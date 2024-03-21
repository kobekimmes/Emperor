module kobekimmes.com.emperor {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;

    opens kobekimmes.com.emperor to javafx.fxml;
    exports kobekimmes.com.emperor;
}
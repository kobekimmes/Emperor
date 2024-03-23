package kobekimmes.com.emperor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        //stage.setResizable(false);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("Press 'esc' to exit full-screen");
        stage.setFullScreenExitKeyCombination(KeyCombination.keyCombination("esc"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
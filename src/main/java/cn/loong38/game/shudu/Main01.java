package cn.loong38.game.shudu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main01 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Main01.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 500);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}

package cn.loong38.game.shudu;

import cn.loong38.game.shudu.fxml.GameBlock;
import cn.loong38.game.shudu.fxml.GameBody;
import cn.loong38.game.shudu.tools.ArrayData;
import cn.loong38.game.shudu.tools.GameInit;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main02 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Main02.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 500);

        Main02Controller controller = fxmlLoader.getController();
        GameBody body = controller.getBody();

        int index = 0;
        ObservableList<Node> children = body.getChildren();
        for (int i = 0; i < ArrayData.BLOCK_SIZE; i++) {
            for (int j = 0; j < ArrayData.BLOCK_SIZE; j++) {
                GameBlock gameBlock = (GameBlock) children.get(index);
                gameBlock.setBlockButton(GameBody.arrayButton.getBlockData()[index]);
                GridPane.setConstraints(gameBlock, j, i);
                index++;
            }
        }
        GameInit gi = new GameInit(body, controller.getDifficult());

        controller.getQuery_game().setOnAction(e -> {
            boolean win = gi.queryGame();
            System.out.println(win);
//            GameBody.arrayData.queryRow();
//            GameBody.arrayData.queryBlock();
//            GameBody.arrayData.queryCol();
//            System.out.println(GameBody.arrayData.isWin());
        });

        controller.getNew_game().setOnAction(e -> {
            gi.newGame();
        });
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}

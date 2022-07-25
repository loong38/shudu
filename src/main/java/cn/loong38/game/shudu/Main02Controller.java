package cn.loong38.game.shudu;

import cn.loong38.game.shudu.fxml.GameBody;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;

/**
 * @author liuzh
 */
public class Main02Controller {
    @FXML
    private Button query_game;
    @FXML
    private Button new_game;
    @FXML
    private ToggleGroup difficult;
    @FXML
    private GameBody body;
    public Main02Controller() {
    }

    public ToggleGroup getDifficult() {
        return difficult;
    }

    public Button getNew_game() {
        return new_game;
    }

    private void init() {

    }

    public GameBody getBody() {
        return body;
    }

    public Button getQuery_game() {
        return query_game;
    }
}

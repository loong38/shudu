package cn.loong38.game.shudu.fxml;

import cn.loong38.game.shudu.tools.*;
import javafx.scene.layout.GridPane;

/**
 * @author liuzh
 */
public class GameBody extends GridPane {
    public static ArrayString arrayData = new ArrayString();
    public static ArrayButton arrayButton = new ArrayButton();

    public GameBody() {
        this.setVgap(10);
        this.setHgap(10);
        init();
    }

    public void init() {
//        System.out.println(arrayButton.toString());

//        {{0,1,2},{3,4,5},{6,7,8}}
    }
}
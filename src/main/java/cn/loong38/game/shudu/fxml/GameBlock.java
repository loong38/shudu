package cn.loong38.game.shudu.fxml;

import cn.loong38.game.shudu.tools.ArrayData;
import cn.loong38.game.shudu.tools.GameButton;
import javafx.scene.layout.GridPane;

/**
 * @author liuzh
 */
public class GameBlock extends GridPane {
    private GameButton[][] blockButton;

    public GameBlock() {
        this.setVgap(5);
        this.setHgap(5);
    }

    public GameButton[][] getBlockButton() {
        return blockButton;
    }

    public void setBlockButton(GameButton[][] blockButton) {
        this.blockButton = blockButton;
        init();
    }

    private void init() {
        for (int i = 0; i < ArrayData.BLOCK_SIZE; i++) {
            for (int j = 0; j < ArrayData.BLOCK_SIZE; j++) {
                add(blockButton[i][j], j, i);
            }
        }
    }
}
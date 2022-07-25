package cn.loong38.game.shudu.tools;

import cn.loong38.game.shudu.fxml.GameBody;

/**
 * @author liuzh
 */
public class ArrayButton extends ArrayData<GameButton> {
    private GameButton[] totalData = new GameButton[SIZE * SIZE];
    private GameButton[][] rowData = new GameButton[SIZE][SIZE];
    private GameButton[][] colData = new GameButton[SIZE][SIZE];
    private GameButton[][][] blockData = new GameButton[SIZE][BLOCK_SIZE][BLOCK_SIZE];

    public ArrayButton() {
        for (int i = 0; i < ArrayData.SIZE; i++) {
            for (int j = 0; j < ArrayData.SIZE; j++) {
                GameButton gameButton = new GameButton();
                gameButton.setOnKeyReleased(new ButtonEvent(GameBody.arrayData, i * ArrayData.SIZE + j, gameButton));
                add(i * ArrayData.SIZE + j, gameButton);
            }
        }
    }

    @Override
    public GameButton[][][] getBlockData() {
        return blockData;
    }

    @Override
    public GameButton[][] getColData() {
        return colData;
    }

    @Override
    public GameButton[][] getRowData() {
        return rowData;
    }

    @Override
    public GameButton[] getTotalData() {
        return totalData;
    }
}

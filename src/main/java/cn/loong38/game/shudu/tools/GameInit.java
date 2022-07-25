package cn.loong38.game.shudu.tools;

import cn.loong38.game.shudu.core.GameCore;
import cn.loong38.game.shudu.fxml.GameBody;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.Random;

public class GameInit {
    private final GameBody body;
    private final ToggleGroup group;
    private final GameCore gameCore = new GameCore();
    //    private final GameButtonInit gbi;
    Random random = new Random();
    private String difficult;
    private int[][] srcMatrix;
    private int[][] gameMatrix = new int[9][9];


    public GameInit(GameBody body, ToggleGroup group) {
        this.body = body;
        this.group = group;
//        this.gbi = new GameButtonInit(body);
    }

    public static void main(String[] args) {
        GameInit gi = new GameInit(null, null);
//        gi.difficult = "primary";
//        gi.difficult = "simple";
        gi.difficult = "hard";
        gi.newGame();
    }

    public void newGame() {
        this.difficult = ((RadioButton) group.getSelectedToggle()).getId();
//        GameButtonInit gbi = new GameButtonInit(body);
        srcMatrix = gameCore.generatePuzzleMatrix();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                gameMatrix[i][j] = srcMatrix[i][j];
            }
        }

        if (difficult.equals("primary")) {
            initPrimary();
        } else if (difficult.equals("simple")) {
            initSimple();
        } else if (difficult.equals("hard")) {
            initHard();
        }

        initGUI();
        toString();
    }

    private void initGUI() {
        GameButton[][] rowData = GameBody.arrayButton.getRowData();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                rowData[i][j].setText("");
                rowData[i][j].setDisable(false);

                if (gameMatrix[i][j] == 0) {
                    continue;
                }
                rowData[i][j].setText(String.valueOf(gameMatrix[i][j]));
                rowData[i][j].setDisable(true);
            }
        }
    }

    private void initPrimary() {
        int maxBlockTotalKeep = 5;
        int blockMaxElement = 6;
        initDifficult(maxBlockTotalKeep, blockMaxElement);
    }

    private void initSimple() {
        int maxBlockTotalKeep = 1;
        int blockMaxElement = 3;
        initDifficult(maxBlockTotalKeep, blockMaxElement);
    }

    private void initHard() {

        int maxBlockTotalKeep = 0;
        int blockMaxElement = 1;
        initDifficult(maxBlockTotalKeep, blockMaxElement);
    }

    private void initDifficult(int maxBlockTotalKeep, int blockMaxElement) {
        int[] intsB = new int[9];
        for (int b = 0; b < 9 - maxBlockTotalKeep; b++) {
            int indexBlock = random.nextInt(0, 9);
            if (intsB[indexBlock] != 0) {
                b--;
                continue;
            }
            intsB[indexBlock] = 1;

            int[][] point = new int[3][3];
            for (int i = 0; i < 9 - blockMaxElement; i++) {
                int row = random.nextInt(0, 3);
                int col = random.nextInt(0, 3);
                if (point[row][col] != 0) {
                    i--;
                    continue;
                }

                int indexRow = indexBlock / ArrayData.BLOCK_SIZE * ArrayData.BLOCK_SIZE + row;
                int indexCol = indexBlock % ArrayData.BLOCK_SIZE * ArrayData.BLOCK_SIZE + col;
                gameMatrix[indexRow][indexCol] = 0;

                point[row][col] = 1;
            }
        }
    }

    @Override
    public String toString() {
        String rowTitle = "    0   1   2   3   4   5   6   7   8\n";
        StringBuffer total = new StringBuffer();

        total.append(rowTitle);
        int count = 0;
        for (int i = 0; i < ArrayData.SIZE; i++) {
            StringBuffer sb = new StringBuffer();
            sb.append(i + " ");
            for (int j = 0; j < ArrayData.SIZE; j++) {
                int[] t = gameMatrix[i];
                if (t[j] == 0) {
                    sb.append("|   ");
                } else {
                    sb.append(String.format("| %d ", t[j]));
                }
            }
            sb.append("|");
            total.append(sb.toString() + "\n");
        }
        System.out.println(total.toString());
        return total.toString();
    }

    public boolean queryGame() {
        boolean bool = false;
        int[][] query = new int[9][9];
        GameButton[][] rowData = GameBody.arrayButton.getRowData();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String text = rowData[i][j].getText();
                String value = "";
                switch (text) {
                    case "":
                        bool = false;
                        continue;
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6":
                    case "7":
                    case "8":
                    case "9":
                        value = text;
                        break;
                    default:
                        break;
                }
                query[i][j] = Integer.valueOf(value);
            }
        }
        int index = 0;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (gameCore.noConflict(query, row, col) == false) {

                    if (rowData[row][col].isDisable()) {
                        continue;
                    } else {
                        rowData[row][col].setInlineReuse(true);
                    }
//                    bool = false;
//                    return bool;
//                    asdasda
                    index++;
                }
            }
        }

        if (index >= 81) {
            return true;
        } else {
            return false;
        }


    }
}

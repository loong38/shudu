package cn.loong38.game.shudu.tools;

import java.util.ArrayList;

import static cn.loong38.game.shudu.fxml.GameBody.arrayButton;

public class ArrayString extends ArrayData<String> {
    private String[] totalData = new String[SIZE * SIZE];

    private String[][] rowData = new String[SIZE][SIZE];
    private String[][] colData = new String[SIZE][SIZE];
    private String[][][] blockData = new String[SIZE][BLOCK_SIZE][BLOCK_SIZE];

    public ArrayString() {
        setEnableAddReplace(true);
        for (int i = 0; i < totalData.length; i++) {
            add(i, "");
        }
    }

    public boolean queryRow() {
        boolean bool = true;
        for (int i = 0; i < SIZE; i++) {
            String[] row = rowData[i];
            for (int j = 0; j < SIZE; j++) {
                Integer[] contains = contains(row, row[j]);
                if (contains.length == 1) {
                    int index = contains[0];
                    if (index == j) {
                        arrayButton.getRowData()[i][index].setInlineReuse(false);
                    }
                    continue;
                }
                for (Integer index : contains) {
                    GameButton button = arrayButton.getRowData()[i][index];
                    if (button.isDisable()) {
                        continue;
                    }
                    button.setInlineReuse(true);
                    bool = true;
                }
            }
        }
        return bool;
    }

    public boolean queryCol() {
        boolean bool = true;
        GameButton[][] colButton = arrayButton.getColData();
        for (int i = 0; i < SIZE; i++) {
            String[] col = colData[i];
            for (int j = 0; j < SIZE; j++) {
                Integer[] contains = contains(col, col[j]);
                if (contains.length == 1) {
                    int index = contains[0];
                    if (index == j) {
                        colButton[i][index].setColumnReuse(false);
                    }
                    continue;
                }
                for (Integer index : contains) {
                    GameButton button = colButton[i][index];
                    if (button.isDisable()) {
                        continue;
                    }
                    button.setColumnReuse(true);
                    bool = false;
                }
            }
        }
        return bool;
    }

    public boolean queryBlock() {
        boolean bool = true;
        GameButton[][][] blockButton = arrayButton.getBlockData();
        for (int b = 0; b < SIZE; b++) {
//            String[] block = getSingleArray(BLOCK, b);
            String[][] block = getBlockData()[b];
            for (int i = 0; i < BLOCK_SIZE; i++) {
                for (int j = 0; j < BLOCK_SIZE; j++) {

                    if (block[i][j].equals("")) {
                        continue;
                    }
                    ArrayList<Integer> list = new ArrayList<>();
                    for (int k = 0; k < BLOCK_SIZE; k++) {
                        for (int l = 0; l < BLOCK_SIZE; l++) {
                            if (block[i][j].equals(block[k][l])) {
                                list.add(i);
                                list.add(j);
                            }
                        }
                    }
                    if (list.size() == 2) {
                        int row = list.get(0);
                        int col = list.get(1);
                        if (row == i && col == j) {
                            blockButton[b][row][col].setBlockReuse(false);
                        }
                        continue;
                    }
                    if (list.size() > 2) {
                        for (int k = 0; k < list.size(); k += 2) {
                            int row = list.get(k);
                            int col = list.get(k + 1);
                            GameButton button = blockButton[b][row][col];
                            if (button.isDisable()) {
                                continue;
                            }
                            button.setBlockReuse(true);
                            bool = false;
                        }
                    }


                }
            }
        }
        return bool;
    }

    public boolean isWin() {
        if (!queryRow()) {
            return false;
        }
        if (!queryCol()) {
            return false;
        }
        if (!queryBlock()) {
            return false;
        }
        for (int i = 0; i < totalData.length; i++) {
            if (totalData[i].equals("")) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String[] getTotalData() {
        return totalData;
    }

    @Override
    public String[][] getRowData() {
        return rowData;
    }

    @Override
    public String[][] getColData() {
        return colData;
    }

    @Override
    public String[][][] getBlockData() {
        return blockData;
    }
}

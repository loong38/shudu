package cn.loong38.game.shudu.tools;

import java.util.ArrayList;

/**
 * @author liuzh
 */
public abstract class ArrayData<T> {
    public static final int SIZE = 9;
    public static final int BLOCK_SIZE = 3;
    /**
     * TYPE
     */
    public static final int ROW = 0;
    /**
     * TYPE
     */
    public static final int COL = 1;
    /**
     * TYPE
     */
    public static final int BLOCK = 2;
    //    private final int[][] doubleToBlockIndex = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
//    private final int[] rowToBlockRow = new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2};i/3
//    private final int[] colToBlockCol = new int[]{0, 1, 2, 0, 1, 2, 0, 1, 2};i%3
//    private final int[] blockIndexAddRow = new int[]{0, 0, 0, 3, 3, 3, 6, 6, 6};
//    private final int[] blockIndexAddCol = new int[]{0, 3, 6, 0, 3, 6, 0, 3, 6};
    boolean enableAddReplace = false;

    public Integer[] contains(T[] arr, T t) {
        if (t.equals("")) {
            return new Integer[]{-1};
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(t)) {
                list.add(i);
            }
        }
        if (list.size() > 0) {
            return list.toArray(new Integer[list.size()]);
        }
        return new Integer[]{-1};
    }

    public boolean isEnableAddReplace() {
        return enableAddReplace;
    }

    public void setEnableAddReplace(boolean bool) {
        this.enableAddReplace = bool;
    }

    /**
     * 获取total数组。此数组包含所有的对象。
     *
     * @return
     */
    public abstract T[] getTotalData();

    public abstract T[][] getRowData();

    public abstract T[][] getColData();

    public abstract T[][][] getBlockData();

    public void add(int index, T value) {
        if (isAdd(index)) {
            getTotalData()[index] = value;

            int row = index / SIZE;
            int col = index % SIZE;
            getRowData()[row][col] = value;
            getColData()[col][row] = value;

            int blockRow = row % 3;
            int blockCol = col % 3;
            int blockIndex = row / BLOCK_SIZE * BLOCK_SIZE + col / BLOCK_SIZE;

            getBlockData()[blockIndex][blockRow][blockCol] = value;
        }
    }

    /**
     * @param type  ROW or COl
     * @param var1  type=ROW,var1 = inline.<br>type=Col,var1=col.
     * @param var2  index
     * @param value
     */
    public void add(int type, int var1, int var2, T value) {
        if (type != ROW && type != COL) {
            return;
        }
        if (var1 < 0 || var1 > SIZE) {
            return;
        }
        if (var2 < 0 || var2 > SIZE) {
            return;
        }
        switch (type) {
            case ROW:
                add(var1, var2, value);
                break;
            case COL:
                add(var2, var1, value);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    /**
     * 简化行列集合对调数据
     *
     * @param row
     * @param col
     * @param value
     */
    protected void add(int row, int col, T value) {
        int index = row * SIZE + col;
        if (isAdd(index)) {
            getTotalData()[index] = value;

            getRowData()[row][col] = value;
            getColData()[col][row] = value;

            int blockRow = row % 3;
            int blockCol = col % 3;
            int blockIndex = row / BLOCK_SIZE * BLOCK_SIZE + col / BLOCK_SIZE;
            getBlockData()[blockIndex][blockRow][blockCol] = value;
        }
    }

    /**
     * @param type       Block
     * @param blockIndex 9
     * @param row        3
     * @param col        3
     * @param value
     */
    public void add(int type, int blockIndex, int row, int col, T value) {
        if (type != BLOCK) {
            return;
        }
        if (row < 0 || row > BLOCK_SIZE) {
            return;
        }
        if (col < 0 || col > BLOCK_SIZE) {
            return;
        }


        int indexRow = blockIndex / BLOCK_SIZE * BLOCK_SIZE + row;
        int indexCol = blockIndex % BLOCK_SIZE * BLOCK_SIZE + col;

        int index = indexRow * SIZE + indexCol;
        if (isAdd(index)) {
            getTotalData()[index] = value;

            getRowData()[indexRow][indexCol] = value;
            getColData()[indexCol][indexRow] = value;

            getBlockData()[blockIndex][row][col] = value;
        }
    }


    public T get(int type, int blockIndex, int row, int col) {
        if (type != BLOCK) {
            return null;
        }
        if (row < 0 || row > BLOCK_SIZE) {
            return null;
        }
        if (col < 0 || col > BLOCK_SIZE) {
            return null;
        }
        return getBlockData()[blockIndex][row][col];

//        int indexRow = blockIndex / BLOCK_SIZE * BLOCK_SIZE + row;
//        int indexCol = blockIndex % BLOCK_SIZE * BLOCK_SIZE + col;
//
//        int index = indexRow * SIZE + indexCol;
//        if (isAdd(index)) {
//            getTotalData()[index] = value;
//
//            getRowData()[indexRow][indexCol] = value;
//            getColData()[indexCol][indexRow] = value;
//
//            getBlockData()[blockIndex][row][col] = value;
//        }
    }

    private boolean isAdd(int index) {
        if (getTotalData()[index] == null) {
            return true;
        }
        if (getTotalData()[index].equals("")) {
            return true;
        }
        return enableAddReplace;
    }

    @Override
    public String toString() {
//        System.out.println("--------------------行----------------");
        String rowTitle = "    0   1   2   3   4   5   6   7   8\n";
        StringBuffer total = new StringBuffer();

        total.append(rowTitle);
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            StringBuffer sb = new StringBuffer();
            sb.append(i + " ");
            for (int j = 0; j < SIZE; j++) {
                Object t = getTotalData()[i * SIZE + j];
                if (t == null) {
                    sb.append("| null ");
                } else if (t.equals("")) {
                    sb.append("|   ");
                } else {
                    sb.append(String.format("| %s ", t.toString()));
                }
            }
            sb.append("|");
            total.append(sb.toString() + "\n");
        }
        return total.toString();
    }

    public T get(int type, int var1, int var2) {

        if (var1 < 0 || var1 > SIZE) {
            return null;
        }
        if (var2 < 0 || var2 > SIZE) {
            return null;
        }

        if (type == ROW) {
            return getRowData()[var1][var2];
        }
        if (type != COL) {
            return getColData()[var2][var1];
        }

        return null;
    }
}

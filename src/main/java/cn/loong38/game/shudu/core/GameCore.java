package cn.loong38.game.shudu.core;

import java.util.Random;

/**
 * 来源：https://blog.csdn.net/weixin_42350940/article/details/114075010
 */
public class GameCore {

    private static final int MAX_CALL_RANDOM_ARRAY_TIMES = 220;
    private Random random = new Random();
    private int currentTimes = 0;

    public static void main(String[] args) {
        GameCore s = new GameCore();
        int[][] ints = s.generatePuzzleMatrix();
        ints[0][0] = 1;
        for (int i = 0; i < 9; i++) {
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < 9; j++) {
                sb.append(ints[i][j] + " ");
                if (s.noConflict(ints, i, j)) {
//                    System.out.print(true);
                }
            }
            sb.append("\n");
            System.out.print(sb.toString());
        }
    }

    /**
     * 生成拼图矩阵
     *
     * @return
     */
    public int[][] generatePuzzleMatrix() {

        int[][] randomMatrix = new int[9][9];

        for (int row = 0; row < 9; row++) {

            if (row == 0) {

                currentTimes = 0;

                randomMatrix[row] = buildRandomArray();

            } else {

                int[] tempRandomArray = buildRandomArray();

                for (int col = 0; col < 9; col++) {

                    if (currentTimes < MAX_CALL_RANDOM_ARRAY_TIMES) {

                        if (!isCandidateNmbFound(randomMatrix, tempRandomArray, row, col)) {

                            resetValuesInRowToZero(randomMatrix, row);

                            row -= 1;

                            col = 8;

                            tempRandomArray = buildRandomArray();

                        }

                    } else {

                        row = -1;

                        col = 8;

                        resetValuesToZeros(randomMatrix);

                        currentTimes = 0;

                    }

                }

            }

        }

        return randomMatrix;

    }

    private void resetValuesInRowToZero(int[][] matrix, int row) {

        for (int j = 0; j < 9; j++) {

            matrix[row][j] = 0;

        }

    }

    private void resetValuesToZeros(int[][] matrix) {

        for (int row = 0; row < 9; row++) {

            for (int col = 0; col < 9; col++) {

                matrix[row][col] = 0;

            }

        }

    }

    private boolean isCandidateNmbFound(int[][] randomMatrix, int[] randomArray, int row, int col) {

        for (int i = 0; i < 9; i++) {

            randomMatrix[row][col] = randomArray[i];

            if (noConflict(randomMatrix, row, col)) {

                return true;
            }
        }

        return false;

    }

    public boolean noConflict(int[][] candidateMatrix, int row, int col) {

        return noConflictInRow(candidateMatrix, row, col) && noConflictInColumn(candidateMatrix, row, col) && noConflictInBlock(candidateMatrix, row, col);

    }

    private boolean noConflictInRow(int[][] candidateMatrix, int row, int col) {

        int currentValue = candidateMatrix[row][col];

        for (int colNum = 0; colNum < col; colNum++) {

            if (currentValue == candidateMatrix[row][colNum]) {

                return false;

            }

        }

        return true;

    }

    private boolean noConflictInColumn(int[][] candidateMatrix, int row, int col) {

        int currentValue = candidateMatrix[row][col];

        for (int rowNum = 0; rowNum < row; rowNum++) {

            if (currentValue == candidateMatrix[rowNum][col]) {

                return false;

            }

        }

        return true;

    }

    private boolean noConflictInBlock(int[][] candidateMatrix, int row, int col) {

        int baseRow = row / 3 * 3;

        int baseCol = col / 3 * 3;

        for (int rowNum = 0; rowNum < 8; rowNum++) {

            if (candidateMatrix[baseRow + rowNum / 3][baseCol + rowNum % 3] == 0) {

                continue;

            }

            for (int colNum = rowNum + 1; colNum < 9; colNum++) {

                if (candidateMatrix[baseRow + rowNum / 3][baseCol + rowNum % 3] == candidateMatrix[baseRow + colNum / 3][baseCol + colNum % 3]) {

                    return false;

                }

            }

        }

        return true;

    }

    /**
     * 构建随机数组
     * 随机排序二十次
     *
     * @return
     */
    private int[] buildRandomArray() {

        currentTimes++;

        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        int randomInt = 0;

        for (int i = 0; i < 20; i++) {

            randomInt = random.nextInt(8) + 1;

            int temp = array[0];

            array[0] = array[randomInt];

            array[randomInt] = temp;

        }

        return array;

    }

    public int getCurrentTimes() {

        return currentTimes;

    }

    public void setCurrentTimes(int currentTimes) {

        this.currentTimes = currentTimes;

    }

}

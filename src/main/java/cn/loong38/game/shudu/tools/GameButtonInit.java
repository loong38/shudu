package cn.loong38.game.shudu.tools;

import cn.loong38.game.shudu.fxml.GameBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author liuzh
 */
public class GameButtonInit {
    final ArrayList<Row> row = new ArrayList<>();
    final ArrayList<Col> col = new ArrayList<>();
    final ArrayList<Block> block = new ArrayList<>();

    final ArrayList<HashSet<String>> rSet = new ArrayList<>();
    final ArrayList<HashSet<String>> cSet = new ArrayList<>();
    final ArrayList<HashSet<String>> bSet = new ArrayList<>();
    final int[][] blockIndex = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
    private final GameBody body;
    int SIZE = 9;

    public GameButtonInit(GameBody body) {
        this.body = body;
        init();
    }

    public static void main(String[] args) {
        GameButtonInit gbi = new GameButtonInit(null);
//        gbi.col.get(1).add(0, "1");
//        gbi.col.get(1).add(1, "2");
//        gbi.col.get(1).add(2, "3");
//        gbi.col.get(1).add(3, "4");
//        gbi.col.get(1).add(4, "5");
//        gbi.col.get(1).add(5, "6");
//        gbi.col.get(1).add(6, "7");
//        gbi.col.get(1).add(7, "8");
//        gbi.col.get(1).add(8, "9");
//
//        gbi.row.get(0).add(0, "1");
//        gbi.row.get(1).add(0, "2");
//        gbi.row.get(2).add(0, "3");
//        gbi.row.get(3).add(0, "4");
//        gbi.row.get(4).add(0, "5");
//        gbi.row.get(5).add(0, "6");
//        gbi.row.get(6).add(0, "7");
//        gbi.row.get(7).add(0, "8");
//        gbi.row.get(8).add(0, "9");
//        gbi.col.get(0).add(0, "4");


        gbi.block.get(0).add(0, 0, "1");

        gbi.log();

        RandomCreate rc = new RandomCreate(gbi);

    }

    private void init() {
        for (int i = 0; i < SIZE; i++) {
            rSet.add(new HashSet<>());
            cSet.add(new HashSet<>());
            bSet.add(new HashSet<>());
            row.add(new Row(this));
            col.add(new Col(this));
            block.add(new Block(this));
        }
    }

    public void log() {
        int c;
        int i;
        int j;
        int count;

        System.out.println("--------------------行----------------");
        System.out.println("    0   1   2   3   4   5   6   7   8");
        for (c = 0; c < SIZE; c++) {
            Row row = this.row.get(c);
            Row sort = sort(row);
            ArrayList<Integer> index = sort.index;
            ArrayList<String> value = sort.value;

            StringBuffer sb = new StringBuffer();
            j = 0;
            sb.append(c + " ");
            for (count = 0; count < index.size(); count++) {
                for (i = j; i < index.get(count); i++) {
                    sb.append("|   ");
                    j++;
                }
                sb.append(String.format("| %s ", value.get(count)));
                j++;
            }
            for (; j < SIZE; j++) {
                sb.append("|   ");
            }
            sb.append("|");
            System.out.println(sb.toString());
        }

        System.out.println();
        System.out.println("--------------------列----------------");
        System.out.println("    0   1   2   3   4   5   6   7   8");
        j = 0;
        for (i = 0; i < SIZE; i++) {
            StringBuffer sb = new StringBuffer();
            sb.append(j + " ");
            j++;
            for (c = 0; c < SIZE; c++) {
                Col col = this.col.get(c);
                Col array = sort(col);
                ArrayList<Integer> index = array.index;
                ArrayList<String> value = array.value;


                String s = "";
                try {
                    for (int k = 0; k < index.size(); k++) {
                        if (index.get(k) == i) {
                            s = value.get(k);
                        }
                    }

                } catch (Exception e) {
                    s = "";
                }
                if (s.equals("")) {
                    sb.append("|   ");
                } else {
                    sb.append(String.format("| %s ", s));
                }

            }
            sb.append("|");
            System.out.println(sb.toString());
        }
//        System.out.println();
//        System.out.println("--------------------块··----------------");
//        System.out.println("    0   1   2   0   1   2   0   1   2");
//        System.out.println(block);
//        int r;
//        int[][] toIndex = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
//        StringBuffer sb = new StringBuffer();
//        for (i = 0; i < 3; i++) {
//            for (j = 0; j < 3; j++) {
//                int bIndex = toIndex[i][j];
//                Block block = this.block.get(bIndex);
//                for (c = 0; c < 3; c++) {
//                    block.get(c);
////                    sb.append();
//                }
//
//            }
//        }
        System.out.println();
    }

    private <T extends Array> T sort(T t) {
        Integer[] index = new Integer[0];
        String[] value = new String[0];
        Array obj = null;
        if (t instanceof Row row) {
            index = row.index.toArray(new Integer[row.index.size()]);
            value = row.value.toArray(new String[row.value.size()]);
            obj = new Row(this);
//            for ()
        } else if (t instanceof Col col) {
            index = col.index.toArray(new Integer[col.index.size()]);
            value = col.value.toArray(new String[col.value.size()]);
            obj = new Col(this);
        }
        //sort
        for (int i = 0; i < index.length; i++) {
            for (int j = 0; j < index.length; j++) {
                if (index[i] < index[j]) {
                    int a = index[j];
                    index[j] = index[i];
                    index[i] = a;

                    String s = value[j];
                    value[j] = value[i];
                    value[i] = s;
                }
            }
        }
        obj.index.addAll(List.of(index));
        obj.value.addAll(List.of(value));
        return (T) obj;
    }

    protected <T extends Array> void sync(T t) {
        if (t instanceof Row row) {
            rowCallSync(row);
        } else if (t instanceof Col col) {
            colCallSync(col);
        } else if (t instanceof Block block) {
            blockCallSync(block);
        }
    }

    private void blockCallSync(Block block) {
        //在大范围block所在的位置
        int bIndex = this.block.indexOf(block);
        ArrayList<Integer> index = block.index;
        ArrayList<String> value = block.value;

        int[] indexToRow = new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2};
        int[] indexToCol = new int[]{0, 1, 2, 0, 1, 2, 0, 1, 2};

        int[] indexAddRow = new int[]{0, 0, 0, 3, 3, 3, 5, 5, 5};
        int[] indexAddCol = new int[]{0, 3, 6, 0, 3, 6, 0, 3, 6};
        int k;
        for (k = 0; k < index.size(); k++) {
            //元素在block中所处的序号
            int elementIndex = index.get(k);
            int indexRow = indexToRow[elementIndex];
            int indexCol = indexToCol[elementIndex];

            int allRow = indexAddRow[bIndex] + indexRow;
            int allCol = indexAddCol[bIndex] + indexCol;

            Row row = this.row.get(allRow);
            if (row.get(allCol).equals("")) {
                row.add(allCol, value.get(k));
                bSet.get(bIndex).add(value.get(k));
                cSet.get(allCol).add(value.get(k));
                rSet.get(allRow).add(value.get(k));
            }
        }

    }

    private void colCallSync(Col col) {
        int cIndex = this.col.indexOf(col);

        ArrayList<Integer> index = col.index;
        ArrayList<String> value = col.value;
        for (int i = 0; i < index.size(); i++) {

            int rIndex = index.get(i);

            int r = rIndex / 3;
            int c = cIndex / 3;
            int bIndex = blockIndex[r][c];

            Row row = this.row.get(rIndex);
            if (row.get(i).equals("")) {
                row.add(cIndex, value.get(i));

                rSet.get(rIndex).add(value.get(i));
                cSet.get(cIndex).add(value.get(i));
                bSet.get(bIndex).add(value.get(i));
            }


        }

    }

    private void rowCallSync(Row row) {
        int rIndex = this.row.indexOf(row);

        ArrayList<Integer> index = row.index;
        ArrayList<String> value = row.value;
        for (int i = 0; i < index.size(); i++) {

            int cIndex = index.get(i);

            int r = rIndex / 3;
            int c = cIndex / 3;
            int bIndex = blockIndex[r][c];

            Col col = this.col.get(cIndex);
            if (col.get(rIndex).equals("")) {
                col.add(rIndex, value.get(i));

                rSet.get(rIndex).add(value.get(i));
                cSet.get(cIndex).add(value.get(i));
                bSet.get(bIndex).add(value.get(i));
            }


        }
    }

}

abstract class Array {




    protected final GameButtonInit gbi;
    protected final HashSet<Integer> set = new HashSet<>();
    protected ArrayList<Integer> index = new ArrayList<>();
    protected ArrayList<String> value = new ArrayList<>();

    public Array(GameButtonInit gbi) {
        this.gbi = gbi;
    }

    public String get(int i) {
        try {
            return value.get(i);
        } catch (Exception e) {
            return "";
        }
    }

    public boolean add(int i, String value) {
        if (!set.contains(i)) {
            set.add(i);
            this.index.add(i);
            this.value.add(value);
            this.gbi.sync(this);
            return true;
        }
        return false;
    }
}

class Row extends Array {
    public Row(GameButtonInit gbi) {
        super(gbi);
    }

    @Override
    public String get(int cIndex) {
        return super.get(cIndex);
    }

    @Override
    public boolean add(int col, String value) {
        return super.add(col, value);
    }
}

class Col extends Array {
    public Col(GameButtonInit gbi) {
        super(gbi);
    }

    @Override
    public String get(int rIndex) {
        return super.get(rIndex);
    }

    @Override
    public boolean add(int row, String value) {
        return super.add(row, value);
    }
}

class Block extends Array {
    final int[][] number = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
//    protected ArrayList<Integer> row = new ArrayList<>();
//    protected ArrayList<Integer> col = new ArrayList<>();

    public Block(GameButtonInit gbi) {
        super(gbi);
    }

//    /**
//     * 此方法在block类中不起作用。
//     * @param i
//     * @param value
//     * @return
//     * @see #add(int, int, String)
//     */
//    @Deprecated
//    @Override
//    public boolean add(int i, String value) {
//        new Thread().stop();
//        return false;
//    }

    public boolean add(int row, int col, String value) {
        int index = this.number[row][col];
        return super.add(index, value);
//        if (!set.contains(index)) {
//            add();
//            set.add(index);
////            this.row.add(row);
////            this.col.add(col);
//            this.index.add(index);
//            this.value.add(value);
//            gbi.sync(this);
//            return true;
//        }
//        return false;
    }
}

package cn.loong38.game.shudu;

import cn.loong38.game.shudu.fxml.GameBody;
import cn.loong38.game.shudu.tools.GameButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GameQuery01 {
    static Map<String, ArrayList<GameButton>> map = new HashMap<>();
    static HashSet<GameButton> set = new HashSet<>();

    static {
        map.put("1", new ArrayList<>());
        map.put("2", new ArrayList<>());
        map.put("3", new ArrayList<>());
        map.put("4", new ArrayList<>());
        map.put("5", new ArrayList<>());
        map.put("6", new ArrayList<>());
        map.put("7", new ArrayList<>());
        map.put("8", new ArrayList<>());
        map.put("9", new ArrayList<>());
    }

    private final GameBody body;

    public GameQuery01(GameBody body) {
        this.body = body;
    }

    private void query_block(GameButton[][][] buttons) {
        for (GameButton[][] block : buttons) {
            for (GameButton[] rows : block) {
                for (GameButton btn : rows) {
                    addMap(btn);
                }
            }
            markButton("col");
        }
    }

//    public void query() {
////        Button button = buttons[0][0][0];
//        query_block(body.getButtonsBlock());
//        query_col(body.getButtonsPlanar());
//        query_row(body.getButtonsPlanar());
//        if (isWin()) {
//            System.out.println("WIN");
//        }
//    }
//
//    private boolean isWin() {
//        for (GameButton[] rows : body.getButtonsPlanar()) {
//            for (GameButton col : rows) {
//                if (col.isInlineReuse()) {
//                    return false;
//                }
//
//                if (col.isColumnReuse()) {
//                    return false;
//                }
//
//                if (col.isBlockReuse()) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    private void query_row(GameButton[][] buttons) {
        for (GameButton[] rows : buttons) {
            for (GameButton col : rows) {
                addMap(col);
            }
            markButton("row");
        }

    }

    private void addMap(GameButton btn) {
        String code = btn.getText();
        switch (code) {
            case "1":
                map.get("1").add(btn);
                break;
            case "2":
                map.get("2").add(btn);
                break;
            case "3":
                map.get("3").add(btn);
                break;
            case "4":
                map.get("4").add(btn);
                break;
            case "5":
                map.get("5").add(btn);
                break;
            case "6":
                map.get("6").add(btn);
                break;
            case "7":
                map.get("7").add(btn);
                break;
            case "8":
                map.get("8").add(btn);
                break;
            case "9":
                map.get("9").add(btn);
                break;
            default:
        }
    }

    private void query_col(GameButton[][] buttons) {
        for (int c = 0; c < buttons.length; c++) {
            for (int r = 0; r < buttons.length; r++) {
                GameButton button = buttons[r][c];
                addMap(button);
            }
            markButton("col");
        }
    }

    private void markButton(String coord) {
        for (String key : map.keySet()) {
            ArrayList<GameButton> buttons = map.get(key);
            if (buttons.size() == 1) {
                switch (coord) {
                    case "col" -> buttons.get(0).setColumnReuse(false);
                    case "row" -> buttons.get(0).setInlineReuse(false);
                    case "block" -> buttons.get(0).setBlockReuse(false);
                }
                continue;
            }

            for (GameButton button : buttons) {
                if (button.isDisabled()) {
                    continue;
                }

                switch (coord) {
                    case "row" -> button.setInlineReuse(true);
                    case "col" -> button.setColumnReuse(true);
                    case "block" -> button.setBlockReuse(true);
                }
            }
        }
        clearMap();
    }

    private void clearMap() {
        map.get("1").clear();
        map.get("2").clear();
        map.get("3").clear();
        map.get("4").clear();
        map.get("5").clear();
        map.get("6").clear();
        map.get("7").clear();
        map.get("8").clear();
        map.get("9").clear();
    }
}

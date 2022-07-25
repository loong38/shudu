package cn.loong38.game.shudu.tools;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author liuzh
 */
public class ButtonEvent implements EventHandler<KeyEvent> {
    private final ArrayString arrayData;
    private final int index;
    private final GameButton gameButton;

    public ButtonEvent(ArrayString arrayData, int index, GameButton gameButton) {
        this.arrayData = arrayData;
        this.index = index;
        this.gameButton = gameButton;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        keyEvent(keyEvent);
    }

    private void keyEvent(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        String text = "";
        switch (code) {
            case ESCAPE:
                text = "";
                break;
            case DIGIT1:
                text = "1";
                break;
            case DIGIT2:
                text = "2";
                break;
            case DIGIT3:
                text = "3";
                break;
            case DIGIT4:
                text = "4";
                break;
            case DIGIT5:
                text = "5";
                break;
            case DIGIT6:
                text = "6";
                break;
            case DIGIT7:
                text = "7";
                break;
            case DIGIT8:
                text = "8";
                break;
            case DIGIT9:
                text = "9";
                break;
            default:
                break;
        }

        if (!gameButton.getText().equals(text)) {
            gameButton.setInlineReuse(false);
            gameButton.setColumnReuse(false);
            gameButton.setBlockReuse(false);

            gameButton.setText(text);

            arrayData.add(index, text);
            System.out.println(arrayData.toString());
        }
    }
}

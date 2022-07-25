package cn.loong38.game.shudu.tools;

import javafx.scene.control.Button;
import javafx.scene.paint.Paint;

/**
 * @author liuzh
 */
public class GameButton extends Button {
    boolean inlineReuse = false;
    boolean columnReuse = false;
    boolean blockReuse = false;

    public GameButton() {
        this("");
    }

    public GameButton(String s) {
        super(s);
        init();
    }

    private void init() {
        this.getStyleClass().add("gameButton");
        this.setTextFill(Paint.valueOf("blue"));
        this.setMinWidth(40);
        this.setMinHeight(40);
    }

    public void setInlineReuse(boolean inlineReuse) {
        this.inlineReuse = inlineReuse;
    }

    public void setColumnReuse(boolean columnReuse) {
        this.columnReuse = columnReuse;
        updateBounds();
    }

    public void setBlockReuse(boolean blockReuse) {
        this.blockReuse = blockReuse;
    }

    @Override
    protected void updateBounds() {
        super.updateBounds();
        if (isDisabled()) {
            this.setTextFill(Paint.valueOf("black"));
        } else if (inlineReuse || columnReuse || blockReuse) {
            this.setTextFill(Paint.valueOf("red"));
        } else {
            this.setTextFill(Paint.valueOf("blue"));
        }
    }

}

package cn.loong38.game.shudu;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    Button[][] btn = new Button[9][9];
    Button[][][] block = new Button[9][3][3];
    Paint textFill;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 500);
        HelloController controller = fxmlLoader.getController();
        initRadio(controller);

        controller.main.setMinHeight(450);

        initButton(controller);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private void initButton(HelloController controller) {
        VBox main = controller.main;
        ObservableList<Node> children = main.getChildren();
        int index = 1;
        ArrayList<Button> list = new ArrayList<>();
        //HBox
        for (int m_c = 0; m_c < children.size(); m_c++) {
            ObservableList<Node> m_c_hbox = ((HBox) children.get(m_c)).getChildren();
            //VBox
            for (int m_r = 0; m_r < m_c_hbox.size(); m_r++) {
                ObservableList<Node> m_r_box = ((VBox) m_c_hbox.get(m_r)).getChildren();
                //HBox btn
                for (int row = 0; row < m_r_box.size(); row++) {
                    ObservableList<Node> row_hbox = ((HBox) m_r_box.get(row)).getChildren();
                    //VBox btn
                    for (int col = 0; col < row_hbox.size(); col++) {
                        ObservableList<Node> col_vbox = ((VBox) row_hbox.get(col)).getChildren();
                        Button button = ((Button) col_vbox.get(0));
                        list.add(button);
                    }
                }
                index++;
            }
        }

        //list转九大块。
        int b = 0;
        int r = 0;
        int c = 0;
        index = 0;
        for (b = 0; b < block.length; b++) {
            for (r = 0; r < block[0].length; r++) {
                for (c = 0; c < block[0][0].length; c++) {
                    Button button = list.get(index);
                    block[b][r][c] = button;
                    index++;
                }
            }
        }

        //九大块转二维数组
        int r_2 = 0;
        b = 0;
        r = 0;
        c = 0;
        int ct = 0;
        int max = 0;
        for (max = 0; max < block[0].length; max++) {
            for (r = 0; r < block[0].length; r++) {
                if (max == 0) {
                    b = 0;
                } else if (max == 1) {
                    b = 3;
                } else if (max == 2) {
                    b = 6;
                }
                int c_2 = 0;
                for (ct = 0; ct < block[0].length; ct++, b++) {
                    for (c = 0; c < block[0].length; c++) {
                        Button button = block[b][r][c];
                        btn[r_2][c_2] = button;
                        c_2++;
                    }
                }
                r_2++;
            }
        }

//        初始化
        for (Button[] bts : btn) {
            for (Button bt : bts) {
                bt.setText("  ");
                bt.setMinWidth(40);
                bt.setMinHeight(40);

                bt.setOnMouseEntered(e -> {
                    textFill = bt.getTextFill();
                });
                bt.setOnMouseMoved(e -> {
                    bt.setTextFill(textFill);
                });
                bt.setOnMouseExited(e -> {
                    System.out.println(bt.getTextFill());
                    bt.setTextFill(textFill);
                });

            }
        }

        controller.query_game.setOnAction(e -> {
            query();
        });

    }

    private void query() {
        int bt_b = 0;
        int bt_r = 0;
        int bt_c = 0;

        block[0][1][1].setDisable(true);
        block[0][1][1].setText("1");

        ArrayList<Button> list;
        for (int b = 0; b < block.length; b++) {
            list = new ArrayList<>();
            for (int r = 0; r < block[0].length; r++) {
                for (int c = 0; c < block[0].length; c++) {
                    Button button = block[b][r][c];
                    if (button.getText().equals("  ")) {
                        continue;
                    }
                    list.add(button);
                }
            }

            //做红
            for (int i = 0; i < list.size(); i++) {
                Button btn = list.get(i);
                for (Button bt : list) {
                    String btn_text = btn.getText();
                    String bt_text = bt.getText();
                    if (btn_text.equals(bt_text)) {
                        btn.setTextFill(Paint.valueOf("red"));
                        bt.setTextFill(Paint.valueOf("red"));
                    }
                    if (bt.isDisabled()) {
                        bt.setTextFill(Paint.valueOf("black"));
                    }
                }
            }
        }
    }

    private void initRadio(HelloController controller) {
        ToggleGroup tg = new ToggleGroup();
        controller.primary.setToggleGroup(tg);
        controller.simple.setToggleGroup(tg);
        controller.difficult.setToggleGroup(tg);
    }
}
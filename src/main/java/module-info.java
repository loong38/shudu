module cn.loong.game.shudu {
    exports cn.loong38.game.shudu;
    exports cn.loong38.game.shudu.tools;
    exports cn.loong38.game.shudu.fxml;

    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens cn.loong38.game.shudu to javafx.fxml;
    opens cn.loong38.game.shudu.fxml to javafx.fxml;
}
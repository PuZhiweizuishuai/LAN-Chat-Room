package ChatMessage.Login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author PuZhiwei
 * */
public class LoginMain extends Application {
    private Stage stage = new Stage();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/LoginUI.fxml"));
        primaryStage.setTitle("ChatMessages登陆");
        Scene scene = new Scene(root, 960, 540);
        primaryStage.setScene(scene);
        // 窗口样式调整
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        // 设置窗口大小不可调整
        primaryStage.setResizable(false);
        // 窗口移动
        DragUtil.addDragListener(primaryStage, root);
        primaryStage.getIcons().add(new Image("/resources/images/Icon.png"));
        primaryStage.show();
        // SaveUser.userSerialize(new UserInformation("","",""));
    }



    public void showWindow() {
        try {
            start(stage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

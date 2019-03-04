package mycontrol.chatbox;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Pu Zhiwei
 * 消息气泡
 * */
public class OtherChatBox extends HBox implements Initializable {
    @FXML
    private HBox rootHbox;

    @FXML
    private ImageView otherHeadImage;

    @FXML
    private TextArea chatMessage;

    @FXML
    private Pane TextPane;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        chatMessage.setWrapText(true);
        chatMessage.setEditable(false);
        chatMessage.setPrefColumnCount(20);
    }

    public OtherChatBox() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OtherChatBoxUI.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setHeightAndWidth(double height, double width) {
        chatMessage.setPrefHeight(height);
        TextPane.setPrefHeight(height);
        chatMessage.setPrefWidth(width);
        TextPane.setPrefWidth(width-5);
    }

    public void setMessage(String name) {
        textProperty().set(name);
    }


    private StringProperty textProperty() {
        return chatMessage.textProperty();
    }

    public void setHeadImageView(String imagePath) {
        Image image = new Image(imagePath);
        otherHeadImage.setImage(image);
    }
}

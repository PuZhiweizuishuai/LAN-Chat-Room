package ChatMessage.Login;

import ChatMessage.Main.Main;
import ChatMessage.SignUp.SignUp;
import ChatMessage.communication.Communication;
import ChatMessage.user.*;
import javafx.event.ActionEvent;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mycontrol.popup.PopUpUI;


/**
 * @author Pu Zhiwei
 * */
public class LoginControl implements Initializable {
    private static LoginControl instance;

    Communication comm;

    @FXML
    private AnchorPane rootBox;

    @FXML
    private TextField userName;

    @FXML
    private  PasswordField userPassword;

    private static boolean isLoginResults = false;

    /**
     * 为了获取 loginControl 对象
     * */
    public LoginControl() {
        instance = this;
    }

    public static LoginControl getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        UserInformation user;
        try {
            user = SaveUser.userDeserialize();
            userName.setText(user.getUserName());
        } catch (Exception e) {
            userName.setText("");
        }
    }

    /**
     * 判断密码或用户名是否为空
     * */
    private boolean checkUpNameAndPwd(String name, String passworad) {
        if(name.equals("")|| passworad.equals(""))
            return false;
        return true;
    }


    /**
     * 点击登陆按钮后的事件
     * */
    @FXML
    public void clickLoginButton(ActionEvent event) {
        String name = userName.getText();
        String pwd = userPassword.getText();
        if(checkUpNameAndPwd(name, pwd)) {
            SaveUser.saveLoginUserName(name);
            if(isLoginResults) {
                LoadMain();
            } else {
                new PopUpUI("提示", "密码或账号错误！");
            }
        } else {
            new PopUpUI("提示:", "请输入用户名和密码!");
        }
        //TODO
    }

    /**
     * 点击注册按钮后的事件
     * */
    @FXML
    public void clickSignUpButton(ActionEvent event) {
        new SignUp();
        try {
            Stage thisStage = (Stage) rootBox.getScene().getWindow();
            thisStage.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * 关闭窗口
     * */
    @FXML
    public void close(ActionEvent event) {
        //TODO
        System.exit(0);
    }

    /**
     * 最小化
     * */
    @FXML
    public void minimize(ActionEvent event) {
        Stage stage = (Stage) rootBox.getScene().getWindow();
        // 最小化到任务栏
        stage.setIconified(true);
    }



    private void LoadMain() {
        try {
            Main main = new Main();
            Stage thisStage = (Stage) rootBox.getScene().getWindow();
            thisStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setIsLoginResults(boolean isLoginResults) {
        LoginControl.isLoginResults = isLoginResults;
    }
}

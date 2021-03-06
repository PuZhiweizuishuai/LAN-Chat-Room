package DB;

import ChatMessage.user.Message;
import MainUI.MainUIControll;
import Server.SaveServerMessage;
import Server.ServerMessage;

import java.sql.*;

/**
 * 数据库控制模块
 * @author Pu Zhiwei
 * */
public class DBControl {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://"+ ServerMessage.MYSSQL + ":" + ServerMessage.MYSQLPORT + "/ChatMessage" ;
    //+ ServerMessage.MYSSQL + ":" + ServerMessage.MYSQLPORT + "/ChatMessage"
    private static String user = ServerMessage.mysqlName;
    private static String password = ServerMessage.mysqlPassword;
    private static Connection con;


    /**
     * 连接数据库
     * */
    private static void connect() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed()) {
                System.out.println("Succeeded connecting to the Database!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 登陆验证
     * */
    public static boolean checkUserNameAndPwd(String sql, Message message) {
        connect();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            String name = "", email = "", pwd = "";
            while (rs.next()) {
                name = rs.getString("Name");
                email = rs.getString("Email");
                pwd = rs.getString("Password");
            }
            if(name.equals("")) {
                System.out.println("用户名不存在");
                rs.close();
                con.close();
                return false;
            }
            if(name.equals(message.getName()) && pwd.equals(message.getPassword())) {
                System.out.println("登陆成功");
                rs.close();
                con.close();
                return true;
            }
            rs.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 注册
     * */
    public static boolean signUp(Message message) {
        connect();
        try {
            String sql = "select * from User where Name = " + "'" + message.getName() + "'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            String name = "", email = "", pwd = "";
            while (rs.next()) {
                name = rs.getString("Name");
                email = rs.getString("Email");
                pwd = rs.getString("Password");
            }
            System.out.println(name.equals(""));
            if(name.equals("")) {
                sql = "insert into User values (\"" +
                        message.getName() + "\",\"" + message.getEmail() + "\",\"" + message.getPassword() + "\")";
                PreparedStatement insert = con.prepareStatement(sql);
                insert.executeUpdate();
                con.close();
                return true;
            }
            con.close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改密码
     * */
    public static boolean changePwd(Message message) {
        connect();
        try {
            String sql = "select * from User where Name = " + "'" + message.getName() + "'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            String name = "", email = "", pwd = "";
            while (rs.next()) {
                name = rs.getString("Name");
                email = rs.getString("Email");
                pwd = rs.getString("Password");
            }
            if(pwd.equals(message.getMessage())) {
                sql = "UPDATE User SET Password="+"'"+message.getPassword() + "'"
                        + " where Name=" + "'" + message.getName() + "'";
                PreparedStatement insert = con.prepareStatement(sql);
                insert.executeUpdate();
                System.out.println("修改成功！");
                con.close();
                return true;
            }
            con.close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 存储消息
     * */
    public static void saveMessage(Message message) {
        //TODO 待添加

    }
}

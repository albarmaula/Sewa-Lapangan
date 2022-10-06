package com.WAM.databasemanager;

import com.WAM.model.Admin;
import com.WAM.util.DBManager;
import com.WAM.view.Dashboard;
import com.WAM.view.LoginMenu;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDBManager {
    public static int addAdmin(Admin admin) throws SQLException {
        String query = "INSERT INTO DataAdmin(name, username, password, phoneNo) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
        preparedStatement.setString(1, admin.getAdminName());
        preparedStatement.setString(2, admin.getUsername());
        preparedStatement.setString(3, admin.getPassword());
        preparedStatement.setString(4, admin.getPhoneNo());

        int affectedRows = preparedStatement.executeUpdate();
        return affectedRows;
    }

    //https://www.malasngoding.com/cara-membuat-login-pada-java-mysql/
    public static void checkLogin(String username, String password) throws SQLException{
        try {
            String query = ("SELECT * FROM DataAdmin WHERE username = '" + username + "' AND password = '" + password + "'");
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Dashboard dashboard = Dashboard.getInstance();
                dashboard.show();
                LoginMenu loginMenu = LoginMenu.getInstance();
                loginMenu.hide();
            } else {
                JOptionPane.showMessageDialog(null, "Username dan Password Salah atau Akun Belum Terdaftar", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
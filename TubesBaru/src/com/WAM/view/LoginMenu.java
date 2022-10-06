package com.WAM.view;

import com.WAM.databasemanager.AdminDBManager;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class LoginMenu {
    private static LoginMenu instance;

    JFrame jFrame;
    JPanel jPanel;
    JLabel jLabelUsername;
    JLabel jLabelPassword;
    JLabel jLabelLogin;
    JLabel jLabelOrder;
    JLabel jLabelSuggestion;
    JLabel jLabelIcon;
    JTextField jTextFieldUsername;
    JPasswordField jPasswordField;
    JButton jButtonLogin;
    JButton jButtonRegis;
    JButton jButtonCancel;
    Font font;

    public static LoginMenu getInstance(){
        if (instance == null) {
            instance = new LoginMenu();
        }

        return instance;
    }

    public void show() {
        this.jFrame.setVisible(true);
    }

    public void hide() {
        this.jFrame.setVisible(false);
    }

    private LoginMenu() {
        initializeComponents();
        prepareUILayout();
        prepareUIEventListeners();
    }

    private void initializeComponents(){
        jPanel = new JPanel();
        jFrame = new JFrame();
        jLabelUsername = new JLabel();
        jLabelPassword = new JLabel();
        jLabelSuggestion = new JLabel();
        jLabelOrder = new JLabel();
        jLabelLogin = new JLabel();
        jLabelIcon = new JLabel(new ImageIcon("iconLogin.png"));
        jTextFieldUsername = new JTextField();
        jPasswordField = new JPasswordField();
        jButtonLogin = new JButton();
        jButtonRegis = new JButton();
        jButtonCancel = new JButton();
        font = new Font("Roboto", Font.PLAIN, 20);
    }

    private void prepareUILayout(){
        jPanel.setLayout(new MigLayout());

        jLabelLogin.setText("Login");
        jLabelLogin.setFont(font);
        jPanel.add(jLabelLogin,"gap 130, wrap");

        jPanel.add(jLabelIcon,"gap 120, wrap");

        jLabelOrder.setText("Masukkan username dan password yang telah terdaftar");
        jPanel.add(jLabelOrder, "newline 10, wrap");

        jLabelUsername.setText("Username");
        jPanel.add(jLabelUsername, "wrap");
        jPanel.add(jTextFieldUsername, "grow, spanx, wrap");

        jLabelPassword.setText("Kata Sandi");
        jPanel.add(jLabelPassword, "wrap");
        jPanel.add(jPasswordField, "grow, spanx, wrap");

        jButtonCancel.setText("Batalkan");
        jPanel.add(jButtonCancel,"newline 15, split 2, grow, spanx");

        jButtonLogin.setText("Login");
        jPanel.add(jButtonLogin,"grow, spanx, wrap");

        jLabelSuggestion.setText("Belum punya akun? Klik Tombol Di Bawah Ini");
        jPanel.add(jLabelSuggestion,"wrap");

        jButtonRegis.setText("Registrasi");
        jPanel.add(jButtonRegis, "grow, spanx");

        jFrame.setTitle("Aplikasi Penyewaan Lapangan WAM Sport Center");
        jFrame.setContentPane(jPanel);
        jFrame.setSize(350,400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void prepareUIEventListeners() {
        jButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jButtonRegis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextFieldUsername.setText("");
                jPasswordField.setText("");
                AdminRegistrationForm adminRegistrationForm = AdminRegistrationForm.getInstance();
                adminRegistrationForm.show();
                hide();
            }
        });
        jButtonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = jTextFieldUsername.getText();
                String password = new String(jPasswordField.getPassword());

                if(username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Masukkan username/password anda!");
                }
                else {
                    try {
                        AdminDBManager.checkLogin(username, password);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,"Tidak dapat login! "+ex.getMessage());
                    }
                }
                jTextFieldUsername.setText("");
                jPasswordField.setText("");
            }
        });
    }
}

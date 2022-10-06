package com.WAM.view;

import com.WAM.model.Admin;
import com.WAM.databasemanager.AdminDBManager;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminRegistrationForm {
    private static AdminRegistrationForm instance;

    JFrame jFrame;
    JPanel jPanel;
    JButton jButtonRegister;
    JButton jButtonBack;
    JLabel jLabelTitle;
    JLabel jLabelOrder;
    JLabel jLabelName;
    JLabel jLabelPhoneNo;
    JLabel jLabelUsername;
    JLabel jLabelPassword;
    JLabel jLabelIcon;
    JTextField jTextFieldName;
    JTextField jTextFieldPhoneNo;
    JTextField jTextFieldUsername;
    JPasswordField jPasswordField;
    Font font;


    public static AdminRegistrationForm getInstance(){
        if (instance == null) {
            instance = new AdminRegistrationForm();
        }
        return instance;
    }

    public void show() {
        this.jFrame.setVisible(true);
    }

    public void hide() {
        this.jFrame.setVisible(false);
    }

    private AdminRegistrationForm() {
        initializeComponents();
        prepareUILayout();
        prepareUIEventListeners();
    }

    private void initializeComponents(){
        jPanel = new JPanel();
        jButtonRegister = new JButton();
        jButtonBack = new JButton();
        jFrame = new JFrame();
        jLabelOrder = new JLabel();
        jLabelName = new JLabel();
        jLabelPhoneNo = new JLabel();
        jLabelUsername = new JLabel();
        jLabelPassword = new JLabel();
        jLabelTitle = new JLabel();
        jLabelIcon = new JLabel(new ImageIcon("iconForm.png"));
        jTextFieldName = new JTextField();
        jTextFieldPhoneNo = new JTextField();
        jTextFieldUsername = new JTextField();
        jPasswordField = new JPasswordField();
        font = new Font("Roboto", Font.BOLD, 20);
    }

    private void prepareUILayout() {
        jPanel.setLayout(new MigLayout());

        jLabelTitle.setText("Formulir Pendaftaran Admin");
        jLabelTitle.setFont(font);
        jPanel.add(jLabelTitle,"wrap");

        jPanel.add(jLabelIcon,"gap 110,wrap");

        jLabelOrder.setText("Masukkan Data Anda!");
        jPanel.add(jLabelOrder,"newline 10, wrap 2");

        jLabelName.setText("Nama Lengkap");
        jPanel.add(jLabelName,"wrap");
        jPanel.add(jTextFieldName, "growx, pushx, wrap");

        jLabelPhoneNo.setText("No. Telp");
        jPanel.add(jLabelPhoneNo,"wrap");
        jTextFieldPhoneNo.setText("");
        jPanel.add(jTextFieldPhoneNo, "pushx, growx, wrap");

        jLabelUsername.setText("Username");
        jPanel.add(jLabelUsername,"wrap");
        jPanel.add(jTextFieldUsername,"pushx, growx, wrap");

        jLabelPassword.setText("Password");
        jPanel.add(jLabelPassword, "wrap");
        jPanel.add(jPasswordField,"pushx, growx, wrap");

        jButtonBack.setText("Kembali");
        jPanel.add(jButtonBack, "newline 10, split 2, grow, spanx");

        jButtonRegister.setText("Tambahkan");
        jPanel.add(jButtonRegister, "grow, spanx");

        jFrame.setTitle("Aplikasi Penyewaan Lapangan WAM Sport Center");
        jFrame.setContentPane(jPanel);
        jFrame.setSize(300,410);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void prepareUIEventListeners() {
        jButtonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextFieldName.setText("");
                jTextFieldPhoneNo.setText("");
                jTextFieldUsername.setText("");
                jPasswordField.setText("");

                LoginMenu loginMenu = LoginMenu.getInstance();
                loginMenu.show();
                hide();
            }
        });
        jButtonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adminName = jTextFieldName.getText();
                String phoneNo = jTextFieldPhoneNo.getText();
                String username = jTextFieldUsername.getText();
                String password = new String (jPasswordField.getPassword());

                if(adminName.isEmpty() || phoneNo.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Data anda tidak boleh kosong!", "Message", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    Admin admin = new Admin(adminName, username, password, phoneNo);
                    try {
                        AdminDBManager.addAdmin(admin);
                        JOptionPane.showMessageDialog(null, "Data anda telah tercatat");
                        LoginMenu loginMenu = LoginMenu.getInstance();
                        loginMenu.show();
                        hide();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null,"Penambahan Data Admin Gagal! "+ex.getMessage());
                    }
                }
                jTextFieldName.setText("");
                jTextFieldPhoneNo.setText("");
                jTextFieldUsername.setText("");
                jPasswordField.setText("");
            }
        });
    }
}

package com.WAM.view;

import com.WAM.model.Client;
import com.WAM.viewdatamanager.AllClientViewDataManager;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;


public class ClientRegistrationForm {
    private static ClientRegistrationForm instance;

    JFrame jFrame;
    JPanel jPanel;
    JButton jButtonAdd;
    JButton jButtonCancel;
    JButton jButtonUpdate;
    JLabel jLabelOrder;
    JLabel jLabelName;
    JLabel jLabelPhoneNo;
    JLabel jLabelSportType;
    JLabel jLabelFieldType;
    JLabel jLabelDate;
    JLabel jLabelTime;
    JLabel jLabelDP;
    JLabel jLabelDash;
    JLabel jLabelTitle;
    JLabel jLabelIcon;
    JTextField jTextFieldName;
    JTextField jTextFieldPhoneNo;
    JTextField jTextFieldDP;
    JSpinner jSpinnerDate;
    JComboBox <String> jComboBoxSportType;
    JComboBox <String> jComboBoxFieldType;
    JComboBox <String> jComboBoxStartTime;
    JComboBox <String> jComboBoxFinishTime;
    Font font;

    AllClientViewDataManager allClientViewDataManager;

    public static ClientRegistrationForm getInstance(){
        if (instance == null) {
            instance = new ClientRegistrationForm();
        }
        return instance;
    }

    public void show() {
        this.jFrame.setVisible(true);
    }

    public void hide() {
        this.jFrame.setVisible(false);
    }

    private ClientRegistrationForm() {
        allClientViewDataManager = new AllClientViewDataManager();
        initializeComponents();
        prepareUILayout();
        prepareUIEventListeners();
    }

    private void initializeComponents(){
        String sportType[] = {"Basket", "Futsal"};
        String fieldType[] = {"Indoor01", "Indoor02", "Outdoor"};
        String time[] = {"08.00","09.00","10.00","11.00","12.00","13.00","14.00","15.00","16.00","17.00","18.00","19.00","20.00","21.00","22.00"};
        jPanel = new JPanel();
        jButtonAdd = new JButton();
        jButtonCancel = new JButton();
        jButtonUpdate = new JButton();
        jFrame = new JFrame();
        jLabelOrder = new JLabel();
        jLabelName = new JLabel();
        jLabelPhoneNo = new JLabel();
        jLabelSportType = new JLabel();
        jLabelFieldType = new JLabel();
        jLabelDate = new JLabel();
        jLabelTime = new JLabel();
        jLabelDP = new JLabel();
        jLabelDash = new JLabel();
        jLabelTitle = new JLabel();
        jLabelIcon = new JLabel(new ImageIcon("iconForm.png"));
        jTextFieldName = new JTextField();
        jTextFieldPhoneNo = new JTextField();
        jTextFieldDP = new JTextField();
        jSpinnerDate = new JSpinner();
        jComboBoxSportType = new JComboBox<>(sportType);
        jComboBoxFieldType = new JComboBox<>(fieldType);
        jComboBoxStartTime = new JComboBox<>(time);
        jComboBoxFinishTime = new JComboBox<>(time);
        font = new Font("Roboto", Font.BOLD, 20);
    }

    private void prepareUILayout() {
        jPanel.setLayout(new MigLayout());

        jLabelTitle.setText("Formulir Pendaftaran");
        jLabelTitle.setFont(font);
        jPanel.add(jLabelTitle,"gap 30, wrap");

        jPanel.add(jLabelIcon,"gap 110,wrap");

        jLabelOrder.setText("Masukkan Data Anda!");
        jPanel.add(jLabelOrder,"newline 10, wrap 2");

        jLabelName.setText("Nama Penyewa");
        jPanel.add(jLabelName, "wrap");
        jTextFieldName.setText("");
        jPanel.add(jTextFieldName, "growx, pushx, wrap");

        jLabelPhoneNo.setText("No. Telp");
        jPanel.add(jLabelPhoneNo, "wrap");
        jTextFieldPhoneNo.setText("");
        jPanel.add(jTextFieldPhoneNo, "pushx, growx, wrap");

        jLabelSportType.setText("Tipe Olahraga");
        jPanel.add(jLabelSportType,"wrap");
        jPanel.add(jComboBoxSportType,"pushx, growx, wrap");

        jLabelFieldType.setText("Tipe Lapangan");
        jPanel.add(jLabelFieldType,"wrap");
        jPanel.add(jComboBoxFieldType,"pushx, growx, wrap");

        jLabelDate.setText("Tanggal");
        jPanel.add(jLabelDate,"wrap");

        Date today = new Date();
        jSpinnerDate = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
        JSpinner.DateEditor editor = new JSpinner.DateEditor(jSpinnerDate, "dd/MM/yy");
        jSpinnerDate.setEditor(editor);
        jPanel.add(jSpinnerDate, "pushx, growx, wrap");

        jLabelTime.setText("Jam");
        jPanel.add(jLabelTime, "wrap");
        jPanel.add(jComboBoxStartTime, "spanx, growx,split 3");
        jLabelDash.setText("-");
        jPanel.add(jLabelDash);
        jPanel.add(jComboBoxFinishTime, "spanx, growx, wrap");

        jLabelDP.setText("DP (Rp)");
        jPanel.add(jLabelDP,"wrap");
        jTextFieldDP.setText("");
        jPanel.add(jTextFieldDP, "pushx, growx, wrap");

        jButtonCancel.setText("Batalkan");
        jPanel.add(jButtonCancel, "newline 10, split 2, grow, spanx");

        jButtonAdd.setText("Tambahkan");
        jPanel.add(jButtonAdd, "grow, spanx");

        jFrame.setTitle("Aplikasi Penyewaan Lapangan WAM Sport Center");
        jFrame.setContentPane(jPanel);
        jFrame.setSize(300,575);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void prepareUIEventListeners() {
        jButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllClientView allClientView = AllClientView.getInstance();
                allClientView.show();
                hide();
                jTextFieldName.setText("");
                jTextFieldPhoneNo.setText("");
                jTextFieldDP.setText("");
                jSpinnerDate.setValue(new Date());
            }
        });
        jButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clientName = jTextFieldName.getText();
                String phoneNo = jTextFieldPhoneNo.getText();
                String sportType = (String)jComboBoxSportType.getSelectedItem();
                String fieldType = (String)jComboBoxFieldType.getSelectedItem();
                String dateSelected = String.valueOf(jSpinnerDate.getValue());
                String date1 = dateSelected.substring(0,10);
                String year = dateSelected.substring(24,28);
                String date = date1 + " " + year;
                String startTime = (String)jComboBoxStartTime.getSelectedItem();
                String finishTime = (String)jComboBoxFinishTime.getSelectedItem();
                String DP = jTextFieldDP.getText();

                if(clientName.isEmpty() || phoneNo.isEmpty() || DP.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Data anda tidak boleh kosong!", "Message", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    Client client = new Client(clientName, phoneNo, sportType, fieldType, date, startTime, finishTime, DP);
                    try {
                        allClientViewDataManager.addClient(client);
                        JOptionPane.showMessageDialog(null, "Data anda telah tercatat");
                        AllClientView allClientView = AllClientView.getInstance();
                        allClientView.show();
                        hide();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null,"Penambahan Data Client Gagal! "+ex.getMessage());
                    }
                }
                jTextFieldName.setText("");
                jTextFieldPhoneNo.setText("");
                jTextFieldDP.setText("");
                jSpinnerDate.setValue(new Date());
            }
        });
    }
}

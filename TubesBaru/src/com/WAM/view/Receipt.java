package com.WAM.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

public class Receipt {
    private static Receipt instance;

    JFrame jFrame;
    JPanel jPanel;
    JButton jButtonPrint;
    JButton jButtonBack;
    JTextArea jTextArea;

    public static Receipt getInstance(){
        if (instance == null) {
            instance = new Receipt();
        }
        return instance;
    }

    public void show() {
        this.jFrame.setVisible(true);
    }

    public void hide() {
        this.jFrame.setVisible(false);
    }

    private Receipt() {
        initializeComponents();
        prepareUILayout();
        prepareUIEventListeners();
    }

    private void initializeComponents(){
        jPanel = new JPanel();
        jButtonPrint = new JButton();
        jButtonBack = new JButton();
        jFrame = new JFrame();
        jTextArea = new JTextArea();
    }

    private void prepareUILayout() {
        AllClientView allClientView = AllClientView.getInstance();
        jPanel.setLayout(new MigLayout());


        jPanel.add(jTextArea, "span, growx, growy, wrap");
        jTextArea.setText("\t            WAM Sport Center \n" +
                        "\t     Jl. Jepara X / 47 Surabaya\n" +
                        "*********************************************************************\n"+
                        "\t                  Booking\n"+
                        "*********************************************************************\n\n\n"+
                        "Nama \t\t: " + allClientView.getTableName() +"\n"+
                        "No.Telp \t\t: " + allClientView.getTablePhoneNo() +"\n"+
                        "Tipe Olahraga \t\t: " + allClientView.getTableSportType() +"\n"+
                        "Tipe Lapangan \t\t: " + allClientView.getTableFieldType() +"\n"+
                        "Tanggal \t\t: " + allClientView.getTableDate() +"\n"+
                        "Jam \t\t: " + allClientView.getTableStartTime() + " - " + allClientView.getTableFinishTime() +"\n"+
                        "DP \t\t: Rp" + allClientView.getTableDP() +"\n\n\n"+
                        "*********************************************************************\n"+
                        "\t                 Terima Kasih");

        jButtonBack.setText("Kembali");
        jPanel.add(jButtonBack, "skip, split 2, growx, span");

        jButtonPrint.setText("Print");
        jPanel.add(jButtonPrint, "growx, span");

        jFrame.setTitle("Aplikasi Penyewaan Lapangan WAM Sport Center");
        jFrame.setContentPane(jPanel);
        jFrame.setSize(380,400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void prepareUIEventListeners() {
        jButtonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllClientView allClientView = AllClientView.getInstance();
                allClientView.show();
                hide();
            }
        });
        jButtonPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    boolean print = (jTextArea).print();
                    if(!print){
                        JOptionPane.showMessageDialog(null, "Gagal Mencetak");
                    }
                }catch (PrinterException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        ;});
    }

;}

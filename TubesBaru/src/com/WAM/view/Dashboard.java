package com.WAM.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard {
    private static Dashboard instance;

    JFrame jFrame;
    JPanel jPanel;
    JButton jButtonNext;
    JButton jButtonLogout;
    JLabel jLabelTitle;
    JLabel jLabelPriceList;
    JLabel jLabelMonFri;
    JLabel jLabelSatSun;
    JLabel jLabelSchedule;
    JLabel jLabelScheduleTime;
    JLabel jLabelFutsalIndoorImage;
    JLabel jLabelBasketIndoorImage;
    JLabel jLabelAddressTitle;
    JLabel jLabelAddress;
    JList jListPriceSatSun;
    JList jListPriceMonFri;
    Font font;

    public static Dashboard getInstance(){
        if (instance == null) {
            instance = new Dashboard();
        }

        return instance;
    }

    public void show() {
        this.jFrame.setVisible(true);
    }

    public void hide() {
        this.jFrame.setVisible(false);
    }

    private Dashboard() {
        initializeComponents();
        prepareUILayout();
        prepareUIEventListeners();
    }

    private void initializeComponents(){
        jPanel = new JPanel();
        jFrame = new JFrame();
        jButtonNext = new JButton();
        jButtonLogout = new JButton();
        jLabelTitle = new JLabel();
        jLabelPriceList = new JLabel();
        jLabelMonFri = new JLabel();
        jLabelSatSun = new JLabel();
        jLabelSchedule = new JLabel();
        jLabelScheduleTime = new JLabel();
        jLabelAddress = new JLabel();
        jLabelAddressTitle = new JLabel();
        jLabelFutsalIndoorImage = new JLabel(new ImageIcon("futsal.png"));
        jLabelBasketIndoorImage = new JLabel(new ImageIcon("lapangan-basket.png"));
        jListPriceMonFri = new JList();
        jListPriceSatSun = new JList();
        font = new Font("Roboto", Font.BOLD, 20);
    }

    private void prepareUILayout(){
        jPanel.setLayout(new MigLayout());

        jLabelTitle.setText("Penyewaan Lapangan WAM Sport Center");
        jLabelTitle.setFont(font);
        jPanel.add(jLabelTitle, "wrap 2");

        jPanel.add(jLabelFutsalIndoorImage, "newline 10, split 2");
        jPanel.add(jLabelBasketIndoorImage, "gap 35, wrap");

        jLabelAddressTitle.setText("Alamat (No Telpon)");
        jPanel.add(jLabelAddressTitle, "wrap");
        jLabelAddress.setText("Jl. Jepara X / 47 Surabaya (085649415981)");
        jPanel.add(jLabelAddress, "wrap");

        jLabelSchedule.setText("Jam Operasional :");
        jPanel.add(jLabelSchedule, "newline 10, wrap");
        jLabelScheduleTime.setText("Setiap hari, 08.00 - 22.00");
        jPanel.add(jLabelScheduleTime, "wrap");

        jLabelPriceList.setText("Pricelist Lapangan WAM Sport Center :");
        jPanel.add(jLabelPriceList, "newline 10, wrap");
        jLabelMonFri.setText("Senin-Jumat :");
        jPanel.add(jLabelMonFri, "split 2");
        jLabelSatSun.setText("Sabtu-Minggu :");
        jPanel.add(jLabelSatSun, "gap 170, wrap");

        String PriceListMonFri[] = {
                "1. Lapangan Basket Indoor  = Rp 120.000",
                "2. Lapangan Basket Outdoor = Rp 100.000",
                "3. Lapangan Futsal Indoor  = Rp 100.000",
                "4. Lapangan Futsal Outdoor = Rp 80.000",
        };
        jListPriceMonFri = new JList(PriceListMonFri);
        jPanel.add(jListPriceMonFri, "split 2, grow");

        String PriceListSatSun[] = {
                "1. Lapangan Basket Indoor  = Rp 150.000",
                "2. Lapangan Basket Outdoor = Rp 130.000",
                "3. Lapangan Futsal Indoor  = Rp 130.000",
                "4. Lapangan Futsal Outdoor = Rp 110.000",
        };

        jListPriceSatSun = new JList(PriceListSatSun);
        jPanel.add(jListPriceSatSun, "wrap");

        jButtonLogout.setText("Keluar");
        jPanel.add(jButtonLogout,"newline 10, split 2, growx,span");

        jButtonNext.setText("Lanjut");
        jPanel.add(jButtonNext, "growx, span");

        jFrame.setTitle("Aplikasi Penyewaan Lapangan WAM Sport Center");
        jFrame.setContentPane(jPanel);
        jFrame.setSize(515,500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void prepareUIEventListeners() {
        jButtonNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllClientView allClientView = AllClientView.getInstance();
                allClientView.show();
                hide();
            }
        });
        jButtonLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginMenu loginMenu = LoginMenu.getInstance();
                loginMenu.show();
                hide();
            }
        });
    }
}

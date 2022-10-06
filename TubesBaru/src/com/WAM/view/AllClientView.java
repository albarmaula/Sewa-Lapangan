package com.WAM.view;

import com.WAM.viewdatamanager.AllClientViewDataManager;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.print.PrinterException;

public class AllClientView {
    private static AllClientView instance;

    JFrame jFrame;
    JPanel jPanel;
    JTable jTable;
    JScrollPane jScrollPane;
    JButton jButtonAdd;
    JButton jButtonDelete;
    JButton jButtonRefresh;
    JButton jButtonSearchByName;
    JButton jButtonPrint;
    JButton jButtonEdit;
    JButton jButtonPrintSelectedData;
    JButton jButtonBack;
    JLabel jLabelList;
    JLabel jLabelSearch;
    JTextField jTextFieldSearch;
    TableRowSorter sorter;
    private String tableName;
    private String tablePhoneNo;
    private String tableSportType;
    private String tableFieldType;
    private String tableDate;
    private String tableStartTime;
    private String tableFinishTime;
    private String tableDP;

    AllClientViewDataManager allClientViewDataManager;

    public static AllClientView getInstance(){
        if (instance == null) {
            instance = new AllClientView();
        }

        return instance;
    }

    public void show() {
        this.jFrame.setVisible(true);
    }

    public void hide() {
        this.jFrame.setVisible(false);
    }

    private AllClientView() {

        allClientViewDataManager = new AllClientViewDataManager();
        initializeComponents();
        prepareUILayout();
        prepareUIData();
        prepareUIEventListeners();
    }

    private void initializeComponents(){
        jPanel = new JPanel();
        jTable = new JTable();
        jButtonAdd = new JButton();
        jButtonDelete = new JButton();
        jButtonRefresh = new JButton();
        jButtonSearchByName = new JButton();
        jButtonPrint = new JButton();
        jButtonEdit = new JButton();
        jButtonPrintSelectedData = new JButton();
        jButtonBack = new JButton();
        jFrame = new JFrame();
        jLabelList = new JLabel();
        jLabelSearch = new JLabel();
        jTextFieldSearch = new JTextField();
    }

    private void prepareUILayout(){
        jPanel.setLayout(new MigLayout());

        jLabelSearch.setText("Pencarian data");
        jPanel.add(jLabelSearch, "wrap");

        jTextFieldSearch.setText("");
        jPanel.add(jTextFieldSearch,"growx, pushx, wrap" );

        jLabelList.setText("Daftar Penyewa Lapangan WAM Sport Center");
        jPanel.add(jLabelList, "wrap 2");

        jScrollPane = new JScrollPane(jTable);
        jPanel.add(jScrollPane, "spanx 4, h 250, grow, pushx, wrap");

        jButtonRefresh.setText("Refresh Tabel");
        jPanel.add(jButtonRefresh, "growx, span, wrap");

        jButtonDelete.setText("Hapus Data");
        jPanel.add(jButtonDelete, "split 3, growx, span");

        jButtonEdit.setText("Edit Data");
        jPanel.add(jButtonEdit, "growx, span");

        jButtonAdd.setText("Tambah Data");
        jPanel.add(jButtonAdd, "growx, span, wrap");

        jButtonPrint.setText("Print Seluruh Data");
        jPanel.add(jButtonPrint, "split 2, growx, span");

        jButtonPrintSelectedData.setText("Print Data Pilihan");
        jPanel.add(jButtonPrintSelectedData," growx, span, wrap");

        jButtonBack.setText("Kembali");
        jPanel.add(jButtonBack);

        jFrame.setTitle("Aplikasi Penyewaan Lapangan WAM Sport Center");
        jFrame.setContentPane(jPanel);
        jFrame.setSize(640,480);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void prepareUIData() {
        DefaultTableModel tableModel = allClientViewDataManager.getAllClient();
        sorter = new TableRowSorter<>(tableModel);
        if (tableModel != null) {
            jTable.setModel(tableModel);
            jTable.setRowSorter(sorter);
        } else {
            JOptionPane.showMessageDialog(null, "Gagal mengambil data client. Silahkan cek kembali koneksi internet anda.");
            System.exit(0);
        }
    }

    private void prepareUIEventListeners() {
        jButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientRegistrationForm clientRegistrationForm = ClientRegistrationForm.getInstance();
                clientRegistrationForm.show();
                hide();
            }
        });
        jButtonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jTable.getSelectedRow();
                if (selectedRow >= 0) {
                    DefaultTableModel defaultTableModel = (DefaultTableModel) jTable.getModel();
                    Vector vector = defaultTableModel.getDataVector().get(selectedRow);

                    String name = (String) vector.get(0);
                    int confirmDeleteResult = JOptionPane.showConfirmDialog(null,
                            "Hapus Data Client dengan nama " + name + " ?",
                            "Konfirmasi Hapus Data",
                            JOptionPane.OK_CANCEL_OPTION);

                    if (confirmDeleteResult == JOptionPane.OK_OPTION){
                        try {
                            allClientViewDataManager.deleteClient(name);
                            prepareUIData();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null,"Penghapusan Data Client Gagal! "+ex.getMessage());
                        }
                    }
                }

            }
        });
        jButtonRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Anda telah me-refresh tabel");
                prepareUIData();
            }
        });
        jTextFieldSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(jTextFieldSearch.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                search(jTextFieldSearch.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                search(jTextFieldSearch.getText());
            }
            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }
        });
        jButtonPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //https://docs.oracle.com/javase/tutorial/uiswing/misc/printtable.html
                try{
                    boolean print = (jTable).print();
                    if(!print){
                        JOptionPane.showMessageDialog(null, "Gagal Mencetak");
                    }
                }catch (PrinterException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        jButtonPrintSelectedData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
                tableName = tableModel.getValueAt(jTable.getSelectedRow(), 0).toString();
                tablePhoneNo = tableModel.getValueAt(jTable.getSelectedRow(), 1).toString();
                tableSportType = (String) tableModel.getValueAt(jTable.getSelectedRow(), 2);
                tableFieldType = (String) tableModel.getValueAt(jTable.getSelectedRow(), 3);
                tableDate = (String) tableModel.getValueAt(jTable.getSelectedRow(), 4);
                tableStartTime = (String) tableModel.getValueAt(jTable.getSelectedRow(), 5);
                tableFinishTime = (String) tableModel.getValueAt(jTable.getSelectedRow(), 6);
                tableDP = tableModel.getValueAt(jTable.getSelectedRow(), 7).toString();
                hide();
            }
        });
        jButtonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
                String tableName = tableModel.getValueAt(jTable.getSelectedRow(), 0).toString();
                String tablePhoneNo = tableModel.getValueAt(jTable.getSelectedRow(), 1).toString();
                Object tableSportType = (Object) tableModel.getValueAt(jTable.getSelectedRow(), 2);
                Object tableFieldType = (Object) tableModel.getValueAt(jTable.getSelectedRow(), 3);
                Object tableStartTime = (Object) tableModel.getValueAt(jTable.getSelectedRow(), 5);
                Object tableFinishTime = (Object) tableModel.getValueAt(jTable.getSelectedRow(), 6);
                String tableDP = tableModel.getValueAt(jTable.getSelectedRow(), 7).toString();

                ClientRegistrationForm clientRegistrationForm = ClientRegistrationForm.getInstance();
                clientRegistrationForm.jTextFieldName.setText(tableName);
                clientRegistrationForm.jTextFieldPhoneNo.setText(tablePhoneNo);
                clientRegistrationForm.jComboBoxSportType.setSelectedItem(tableSportType);
                clientRegistrationForm.jComboBoxFieldType.setSelectedItem(tableFieldType);
                clientRegistrationForm.jComboBoxStartTime.setSelectedItem(tableStartTime);
                clientRegistrationForm.jComboBoxFinishTime.setSelectedItem(tableFinishTime);
                clientRegistrationForm.jTextFieldDP.setText(tableDP);
                clientRegistrationForm.show();
                hide();
                JOptionPane.showMessageDialog(null, "Masukkan kembali tanggal pemesanan");

                //delete data lama
                int selectedRow = jTable.getSelectedRow();
                if (selectedRow >= 0) {
                    DefaultTableModel defaultTableModel = (DefaultTableModel) jTable.getModel();
                    Vector vector = defaultTableModel.getDataVector().get(selectedRow);

                    String name = (String) vector.get(0);
                    try {
                        allClientViewDataManager.deleteClient(name);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,"Data gagal diupdate! "+ex.getMessage());
                    }
                }
            }
        });
        jButtonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashboard dashboard = Dashboard.getInstance();
                dashboard.show();
                hide();
            }
        });
    }

    public String getTableName() {
        return tableName;
    }

    public String getTablePhoneNo() {
        return tablePhoneNo;
    }

    public String getTableSportType() {
        return tableSportType;
    }

    public String getTableFieldType() {
        return tableFieldType;
    }

    public String getTableDate() {
        return tableDate;
    }

    public String getTableStartTime() {
        return tableStartTime;
    }

    public String getTableFinishTime() {
        return tableFinishTime;
    }

    public String getTableDP() {
        return tableDP;
    }
}

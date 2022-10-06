package com.WAM.viewdatamanager;

import com.WAM.model.Client;
import com.WAM.databasemanager.ClientDBManager;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.Vector;

public class AllClientViewDataManager {
    public DefaultTableModel getAllClient(){
        try {
            Vector<Client> allClient = ClientDBManager.getAllClient();

            Vector<String> tableHeader = new Vector<String>();
            tableHeader.add("Nama");
            tableHeader.add("No. Telp");
            tableHeader.add("Tipe Olahraga");
            tableHeader.add("Tipe Lapangan");
            tableHeader.add("Tanggal");
            tableHeader.add("Jam Mulai");
            tableHeader.add("Jam Selesai");
            tableHeader.add("DP (Rp)");

            // data of the table
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            for (int i = 0; i < allClient.size(); i++) {
                Client client = allClient.get(i);

                Vector<Object> vector = new Vector<Object>();
                vector.add((String)client.getClientName());
                vector.add((String)client.getPhoneNo());
                vector.add((String)client.getSportType());
                vector.add((String)client.getFieldType());
                vector.add((String)client.getDate());
                vector.add((String)client.getStartTime());
                vector.add((String)client.getFinishTime());
                vector.add((String)client.getDP());

                data.add(vector);
            }

            DefaultTableModel defaultTableModel = new DefaultTableModel(data, tableHeader);

            return defaultTableModel;
        } catch (Exception e) {
            return null;
        }
    }

    public static int deleteClient(String name) throws SQLException {
        return ClientDBManager.deleteClient(name);
    }

    public static int addClient(Client client) throws SQLException {
        return ClientDBManager.addClient(client);
    }
}

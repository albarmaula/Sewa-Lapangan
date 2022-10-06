package com.WAM.databasemanager;

import com.WAM.model.Client;
import com.WAM.util.DBManager;

import java.util.Vector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClientDBManager {
    public static Vector<Client> getAllClient() throws SQLException {
        String query = "select * from DataClient";
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        Vector<Client> allClient = new Vector();

        while (resultSet.next()) {
            Client client = new Client();
            client.setClientName(resultSet.getString("name"));
            client.setPhoneNo(resultSet.getString("phoneNo"));
            client.setSportType(resultSet.getString("sportType"));
            client.setFieldType(resultSet.getString("fieldType"));
            client.setDate(resultSet.getString("date"));
            client.setStartTime(resultSet.getString("startTime"));
            client.setFinishTime(resultSet.getString("finishTime"));
            client.setDP(resultSet.getString("DP"));

            allClient.add(client);
        }

        return allClient;
    }

    public static int addClient(Client client) throws SQLException {
        String query = "INSERT INTO DataClient(name, phoneNo, sportType, fieldType, date, startTime, finishTime,DP) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
        preparedStatement.setString(1, client.getClientName());
        preparedStatement.setString(2, client.getPhoneNo());
        preparedStatement.setString(3, client.getSportType());
        preparedStatement.setString(4, client.getFieldType());
        preparedStatement.setString(5, client.getDate());
        preparedStatement.setString(6, client.getStartTime());
        preparedStatement.setString(7, client.getFinishTime());
        preparedStatement.setString(8, client.getDP());

        int affectedRows = preparedStatement.executeUpdate();
        return affectedRows;
    }

    public static int deleteClient(String name) throws SQLException {
        String query = "delete from DataClient where name=?";
        PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
        preparedStatement.setString(1, name);

        int affectedRows = preparedStatement.executeUpdate();
        return affectedRows;
    }

}

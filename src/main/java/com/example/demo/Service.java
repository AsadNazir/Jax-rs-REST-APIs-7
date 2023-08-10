package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Service {
    Connection C;

    public Service() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            C = DbPoolingHikariCP.getDataSource().getConnection();
        } catch (Exception E) {
            E.printStackTrace();
        }

    }

    public Object getALlRecords() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();


            PreparedStatement ps = this.C.prepareStatement(CustomerSQL.getAllCustomers);
            ResultSet rs = ps.executeQuery();

            // Convert ResultSet to a List<Map<String, Object>>
            List<Map<String, Object>> resultList = new ArrayList<>();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(i);
                    row.put(columnName, value);
                }
                resultList.add(row);
            }
            return objectMapper.writeValueAsString(resultList);
        } catch (Exception E) {
            E.printStackTrace();
        }

        return "Error";
    }

    public boolean deleteRecord(int id) {
        try {

            try (PreparedStatement preparedStatement = this.C.prepareStatement(CustomerSQL.deleteCustomer)) {
                preparedStatement.setInt(1, id);
                int rowsAffected = preparedStatement.executeUpdate();

                // Check if any rows were affected (record deleted)
                if (rowsAffected > 0) {
                    return true; // Record successfully deleted
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false; // Error occurred or record not found
    }

    public boolean upadateRecord(int id, Customer C) {
        try {

            PreparedStatement ps = this.C.prepareStatement(CustomerSQL.updateCustomer);
            ps.setString(1, C.getName());
            ps.setDouble(2, C.getPurchaseAmount());
            ps.setString(3, C.getCity());
            ps.setInt(4, C.getAge());
            ps.setString(5, C.getGender().toString());
            ps.setInt(6, id);

            int no = ps.executeUpdate();
            if (no > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean insertRecord(Customer C) {
        try {
            PreparedStatement ps = this.C.prepareStatement(CustomerSQL.insertCustomer);
            ps.setString(1, C.getName());
            ps.setDouble(2, C.getPurchaseAmount());
            ps.setString(3, C.getCity());
            ps.setInt(4, C.getAge());
            ps.setString(5, C.getGender());

            int no = ps.executeUpdate();
            if (no > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }


}

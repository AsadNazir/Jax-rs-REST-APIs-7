package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Customer {
    private String name;
    private int age;
    private String city;
    private String gender;
    private double purchaseAmount;

    // Constructor
    public Customer(String name, int age, String city, String gender, double purchaseAmount) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.gender = gender;
        this.purchaseAmount = purchaseAmount;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(double purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    @Override
    public String toString() {
        return "Customer{" + "name='" + name + '\'' + ", age=" + age + ", city='" + city + '\'' + ", gender=" + gender + ", purchaseAmount=" + purchaseAmount + '}';
    }

    // Gender enum
    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public static List<Customer> generateRandomCustomerList(int count) {
        List<Customer> customerList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String[] names = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hannah", "Isaac", "Jane"};
            String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Miami", "San Francisco", "Seattle"};
            Gender[] genders = Gender.values();

            String randomName = names[random.nextInt(names.length)];
            int randomAge = random.nextInt(50) + 20; // Age between 20 and 69
            String randomCity = cities[random.nextInt(cities.length)];
            Gender randomGender = genders[random.nextInt(genders.length)];
            double randomPurchaseAmount = random.nextDouble() * 1000.0; // Purchase amount between 0.0 and 1000.0

            Customer customer = new Customer(randomName, randomAge, randomCity, randomGender.toString(), randomPurchaseAmount);
            customerList.add(customer);
        }

        return customerList;
    }

    public static boolean Insert(Customer Cs, Connection C) throws SQLException {
        String query = "INSERT INTO customers (name, age, city, gender, purchaseamount) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = C.prepareStatement(query);
        preparedStatement.setString(1, Cs.getName());
        preparedStatement.setInt(2, Cs.getAge());
        preparedStatement.setString(3, Cs.getCity());
        preparedStatement.setString(4, Cs.getGender().toString());
        preparedStatement.setDouble(5, Cs.getPurchaseAmount());

        int no = preparedStatement.executeUpdate();

        return no > 0;

    }

    public static void delete(int id, Connection C) throws SQLException
    {
        String query = "DELETE from customers where id = ?";
        PreparedStatement preparedStatement = C.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
    public static ResultSet getAll(Connection C) throws SQLException
    {
        String query = "select * from customers";
        PreparedStatement preparedStatement = C.prepareStatement(query);
        return preparedStatement.executeQuery();

    }



}

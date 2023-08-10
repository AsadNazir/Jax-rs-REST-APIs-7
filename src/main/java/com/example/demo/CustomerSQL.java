package com.example.demo;

public class CustomerSQL {
    public final static String insertCustomer ="insert into customers (name, purchaseamount, city, age, gender) values (?, ?,  ?, ?, ?)";
    public final static String deleteCustomer ="DELETE FROM customers WHERE id = ?";
    public final static String updateCustomer ="update customers set name =?, purchaseamount=?, city= ?, age =?, gender= ? where id =?";
    public final static String getAllCustomers ="select * from customers";

}

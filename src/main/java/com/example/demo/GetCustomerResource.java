package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/getCustomer")
public class GetCustomerResource {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Produces("application/json")
    public String getCustomer() {
       Service S = new Service();
        return (String) S.getALlRecords();
    }
}

package com.example.demo;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/insert")

public class InsertRecordResource {
    @POST
    @Produces("text/plain")
    public Response insert (@QueryParam("name") String name, @QueryParam("age") int age, @QueryParam("amount")double amount, @QueryParam("city") String city, @QueryParam("gender") String gender) {

        Service S = new Service();
        boolean res = S.insertRecord(new Customer(name,age,city,gender,amount));

        if (res)
        {
           return Response.status(Response.Status.OK).build();

        }

        return Response.status(Response.Status.CONFLICT).entity("Error").build();


    }
}
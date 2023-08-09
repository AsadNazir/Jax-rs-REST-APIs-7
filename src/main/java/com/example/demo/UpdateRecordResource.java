package com.example.demo;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/update")
public class UpdateRecordResource {
    @PUT
    @Produces("text/plain")
    public Response update(@QueryParam("id")int id,@QueryParam("name") String name, @QueryParam("age") int age, @QueryParam("amount")double amount, @QueryParam("city") String city, @QueryParam("gender") String gender) {

        Service S = new Service();

        boolean res =S.upadateRecord(id, new Customer(name,age,city,gender,amount));
        if(!res)
        {
            Response.status(Response.Status.CREATED).entity("Error").build();
        }
        return  Response.ok().build();
    }
}
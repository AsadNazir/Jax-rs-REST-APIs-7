package com.example.demo;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/CustomerResource")
public class CustomerResource {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/insert")
    public Response insert (@QueryParam("name") String name, @QueryParam("age") int age, @QueryParam("amount")double amount, @QueryParam("city") String city, @QueryParam("gender") String gender) {

        Service S = new Service();
        boolean res = S.insertRecord(new Customer(name,age,city,gender,amount));

//        200 OK
        if (res) return Response.status(Response.Status.OK).build();

        //Error
        return Response.status(Response.Status.CONFLICT).entity("Error").build();

    }


    @DELETE
    @Path("/delete")

    public Response delete(@QueryParam("id") int id)
    {
        Service S = new Service();

        boolean res = S.deleteRecord(id);

        //Returning 200OK
        if(res) return Response.status(Response.Status.OK).build();

        //Not found
        return  Response.status(Response.Status.NOT_FOUND).entity("Error").build();
    }

    @Path("/update")
    @PUT
    public Response update(@QueryParam("id")int id,@QueryParam("name") String name, @QueryParam("age") int age, @QueryParam("amount")double amount, @QueryParam("city") String city, @QueryParam("gender") String gender)
    {

        Service S = new Service();

        boolean res =S.upadateRecord(id, new Customer(name,age,city,gender,amount));

        //200 OK
        if(res) return Response.status(Response.Status.OK).build();

        //Not found
        return  Response.status(Response.Status.NOT_FOUND).entity("Error").build();
    }

    @GET
    @Path("/getAll")

    public String getAll()
    {
        return (String) new Service().getALlRecords();
    }

}




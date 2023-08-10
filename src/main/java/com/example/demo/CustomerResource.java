package com.example.demo;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

@Path("/CustomerResource")
public class CustomerResource {
    private static final Logger logger = LoggerFactory.getLogger(CustomerResource.class);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/insert")
    public Response insert (@QueryParam("name") String name, @QueryParam("age") int age, @QueryParam("amount")double amount, @QueryParam("city") String city, @QueryParam("gender") String gender) {

        //Logger
        logger.info(new Date().toString() + "_POST_REQUEST-RECIEVED_InsertRecord");


        Service S = new Service();
        boolean res = S.insertRecord(new Customer(name,age,city,gender,amount));

        //200 OK
        if (res) {

            logger.info(new Date().toString() + "_POST_Success_RecordInserted");
            return Response.status(Response.Status.OK).build();

        }

        //Error
        logger.info(new Date().toString() + "_POST_Error_RecordNotInserted");
        return   Response.status(Response.Status.CONFLICT).entity("Error").build();


    }


    @DELETE
    @Path("/delete")

    public Response delete(@QueryParam("id") int id)
    {
        Service S = new Service();

        boolean res = S.deleteRecord(id);

        //200 OK
        if (res) {

            logger.info(new Date().toString() + "_DELETE_Success_RecordDeleted");
            return Response.status(Response.Status.OK).build();

        }

        //Error

        logger.info(new Date().toString() + "_DELETE_Error_RecordNotFound");
        return   Response.status(Response.Status.CONFLICT).entity("Error").build();

    }

    @Path("/update")
    @PUT
    public Response update(@QueryParam("id")int id,@QueryParam("name") String name, @QueryParam("age") int age, @QueryParam("amount")double amount, @QueryParam("city") String city, @QueryParam("gender") String gender)
    {

        Service S = new Service();

        boolean res =S.upadateRecord(id, new Customer(name,age,city,gender,amount));

        //200 OK
        if (res) {

            logger.info(new Date().toString() + "_PUT_Success_RecordUpdated");
            return Response.status(Response.Status.OK).build();

        }

        //Error

        logger.info(new Date().toString() + "_PUT_Error_RecordNotFound");
        return   Response.status(Response.Status.CONFLICT).entity("Error").build();

    }

    @GET
    @Path("/getAll")

    public String getAll()
    {
        logger.info(new Date().toString() + "_GET_Success_RecordsFetched");
        return (String) new Service().getALlRecords();
    }

}




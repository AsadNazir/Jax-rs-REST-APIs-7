package com.example.demo;
import com.example.demo.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/delete")
public class DeleteRecordResource {
    private ObjectMapper objectMapper = new ObjectMapper();

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRecord(@QueryParam("id") int id) {
        Service service = new Service();
        boolean success = service.deleteRecord(id);

        try {
            // Create a JSON response object
            String jsonResponse = objectMapper.writeValueAsString(
                    new Object() {
                        public final boolean success2 = success;
                        public final String message = success ? "Record deleted successfully" : "Record not found or an error occurred";
                    }
            );

            return Response.ok(jsonResponse, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}

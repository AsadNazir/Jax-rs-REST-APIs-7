package com.example.demo;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import javax.print.attribute.SetOfIntegerSyntax;
import java.io.IOException;
import java.util.Base64;

@Provider
public class Security implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BASIC_PREFIX = "Basic ";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Extract the Authorization header from the request
        String authHeader = requestContext.getHeaderString(AUTHORIZATION_HEADER);

        if (authHeader != null && authHeader.startsWith(BASIC_PREFIX)) {
            // Decode and parse the credentials
            System.out.println(authHeader);
            String credentials = authHeader.substring(BASIC_PREFIX.length());
            byte[] decoded = Base64.getDecoder().decode(credentials);
            String[] usernamePassword = new String(decoded).split(":");
            System.out.println(usernamePassword[0]+"  "+usernamePassword[1]);
            // Perform authentication logic
            if (isValidCredentials(usernamePassword[0], usernamePassword[1])) {
                return; // Allow the request to proceed
            }
        }

        // Deny the request with a 401 Unauthorized response
        Response unauthorizedResponse = Response.status(Response.Status.UNAUTHORIZED)
                .header("WWW-Authenticate", "Basic realm=\"My API\"")
                .entity("Unauthorized access")
                .build();
        requestContext.abortWith(unauthorizedResponse);
    }

    private boolean isValidCredentials(String username, String password) {
        // Perform your authentication and authorization checks here
        // For simplicity, you can compare against hardcoded credentials
        return "root".equals(username) && "root".equals(password);
    }
}

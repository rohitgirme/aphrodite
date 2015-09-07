package com.restserver;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorld {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Student getMessage() {
        return new Student("Rohit", "Girme", 29, 007);
    }
}
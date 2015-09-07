package com.restserver;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("service")
public class ServletApplication extends ResourceConfig {

    /**
     * Here we inherit from ResourceConfig which is an extension of Application.
     * ResourceConfig is provided by Jersey for Servlet 3.0.
     * In the constructor, we define all the classes or Resources that will handle the requests.
     * These are then provided to the getClasses method of Application.
      */
    public ServletApplication() {
        packages("com.restserver");
    }
}

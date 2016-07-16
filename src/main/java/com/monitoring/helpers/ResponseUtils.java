package com.monitoring.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.core.Response;

/**
 * Created by venisac
 */
public class ResponseUtils {

    public static Response getResponse(Object result) {
        ObjectMapper mapper = new ObjectMapper();

        String response = null;
        try {
            response =  mapper.writeValueAsString(result);
        } catch (Exception e) {
            //TODO implement logging
            throw new IllegalStateException("No doughnut for you");
        }

        return Response.ok().entity(response).build();
    }
}

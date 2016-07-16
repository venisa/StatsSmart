package com.monitoring.endpoints;

import com.monitoring.helpers.ResponseUtils;
import com.monitoring.helpers.SQL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by venisac
 */
@Path("/hosts")
public class Hosts {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHosts() {

        return ResponseUtils.getResponse(SQL.getHosts());
    }
}

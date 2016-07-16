package com.monitoring.endpoints;

import com.monitoring.helpers.ResponseUtils;
import com.monitoring.helpers.SQL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by venisac
 */
@Path("/host")
public class HostServlet {

    @GET
    @Path("{hostId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHost(@PathParam("hostId") String hostId) {

        return ResponseUtils.getResponse(SQL.getAvgStatistics());
    }

    @GET
    @Path("{hostId}/{dateTime}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatistics(@PathParam("hostId") String hostId, @PathParam("dateTime") String dateTime) {
        return ResponseUtils.getResponse(SQL.getStatistics());
    }

}

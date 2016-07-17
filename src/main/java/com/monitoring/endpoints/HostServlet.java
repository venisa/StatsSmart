package com.monitoring.endpoints;

import com.monitoring.helpers.Datasource;
import com.monitoring.helpers.ResponseUtils;
import com.monitoring.helpers.SQLHelper;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by venisac
 */
@Path("/hosts")
public class HostServlet {

    private final com.monitoring.helpers.SQLHelper SQLHelper;

    @Inject
    public HostServlet(Datasource dataSource) {
        SQLHelper = new SQLHelper(dataSource.getDatabaseConnection());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHosts() {

        return ResponseUtils.getResponse(SQLHelper.getHosts());
    }

    @GET
    @Path("{hostId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSummary(@PathParam("hostId") String hostId) {
        return ResponseUtils.getResponse(SQLHelper.getAverage(hostId));
    }

    @GET
    @Path("{hostId}/latest")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatistics(@PathParam("hostId") String hostId) {
        return ResponseUtils.getResponse(SQLHelper.getAverage(hostId));
    }

}

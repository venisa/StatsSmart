package com.monitoring.endpoints;

import com.monitoring.helpers.Datasource;
import com.monitoring.helpers.ResponseUtils;
import com.monitoring.helpers.SQLHelper;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by venisac
 */
@Path("/summary")
public class SummaryServlet {

    private final com.monitoring.helpers.SQLHelper SQLHelper;

    @Inject
    public SummaryServlet(Datasource dataSource) {
        SQLHelper = new SQLHelper(dataSource.getDatabaseConnection());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSummary() {

        return ResponseUtils.getResponse(SQLHelper.getSummary());
    }
}

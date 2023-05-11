package ru.asgubin.rsrdb.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.asgubin.rsrdb.model.CcfGroup;
import ru.asgubin.rsrdb.service.DaoCcsGroup;
import ru.asgubin.rsrdb.service.DataSourceFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Path("/{db}/ccfGroup")
public class CcfGroupResource {

    private final DaoCcsGroup daoCcsGroup = DaoCcsGroup.getInstance();

    private static final Logger LOG = LoggerFactory.getLogger(CcfGroupResource.class);

    @GET
    @Path("/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByNum(@PathParam("num") int num, @PathParam("db") String dbName)
            throws SQLException {

        if (!DataSourceFactory.getInstance().getDataSource().getDatabaseName().equals(dbName)) {
            DataSourceFactory.getInstance().getDataSource().setDatabaseName(dbName);
            LOG.debug("Set database name " + dbName);
        }

        Optional<CcfGroup> ccfGroup = daoCcsGroup.find(num);
        ccfGroup.orElseThrow(() -> new IllegalArgumentException("num = " + num));

        return Response.ok(ccfGroup.get()).build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@PathParam("db") String dbName)
            throws SQLException {

        if (!DataSourceFactory.getInstance().getDataSource().getDatabaseName().equals(dbName)) {
            DataSourceFactory.getInstance().getDataSource().setDatabaseName(dbName);
            LOG.debug("Set database name " + dbName);
        }

        List<CcfGroup> ccfGroupList = daoCcsGroup.findAll();

        if (ccfGroupList == null) {
            throw new IllegalArgumentException();
        }

        return Response.ok(ccfGroupList).build();
    }
}

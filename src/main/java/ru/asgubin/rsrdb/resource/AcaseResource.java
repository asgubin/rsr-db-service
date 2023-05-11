package ru.asgubin.rsrdb.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.asgubin.rsrdb.model.Acase;
import ru.asgubin.rsrdb.service.DaoAcase;
import ru.asgubin.rsrdb.service.DataSourceFactory;

import java.sql.SQLException;
import java.util.Optional;

@Path("/{db}/acases")
public class AcaseResource {

    private final DaoAcase daoAcase = DaoAcase.getInstance();

    private static final Logger LOG = LoggerFactory.getLogger(AcaseResource.class);

    @GET
    @Path("/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByNum(@PathParam("num") int num, @PathParam("db") String dbName) throws SQLException {

        if (!DataSourceFactory.getInstance().getDataSource().getDatabaseName().equals(dbName)) {
            DataSourceFactory.getInstance().getDataSource().setDatabaseName(dbName);
            LOG.debug("Set database name " + dbName);
        }

        Optional<Acase> acase = daoAcase.find(num);
        acase.orElseThrow(() -> new IllegalArgumentException("num = " + num));

        return Response.ok(acase.get())
                .build();
    }
}

package ru.asgubin.rsrdb.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.asgubin.rsrdb.model.Param;
import ru.asgubin.rsrdb.service.DaoParam;
import ru.asgubin.rsrdb.service.DataSourceFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Path("/{db}/params")
public class ParamResource {

    private final DaoParam daoParam = DaoParam.getInstance();

    private static final Logger LOG = LoggerFactory.getLogger(ParamResource.class);

    @GET
    @Path("/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByNum(@PathParam("num") int num, @PathParam("db") String dbName)
            throws SQLException {

        if (!DataSourceFactory.getInstance().getDataSource().getDatabaseName().equals(dbName)) {
            DataSourceFactory.getInstance().getDataSource().setDatabaseName(dbName);
            LOG.debug("Set database name " + dbName);
        }

        Optional<Param> param = daoParam.find(num);
        param.orElseThrow(() -> new IllegalArgumentException("num = " + num));

        return Response.ok(param.get()).build();
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") String id, @PathParam("db") String dbName)
            throws SQLException {

        if (!DataSourceFactory.getInstance().getDataSource().getDatabaseName().equals(dbName)) {
            DataSourceFactory.getInstance().getDataSource().setDatabaseName(dbName);
            LOG.debug("Set database name " + dbName);
        }

        Optional<Param> param = daoParam.findById(id);
        param.orElseThrow(() -> new IllegalArgumentException("ID = " + id));

        return Response.ok(param.get()).build();
    }

    @GET
    @Path("ccfGroup/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCcfParamsByCcfGroupId(@PathParam("id") int id, @PathParam("db") String dbName)
            throws SQLException {

        if (!DataSourceFactory.getInstance().getDataSource().getDatabaseName().equals(dbName)) {
            DataSourceFactory.getInstance().getDataSource().setDatabaseName(dbName);
            LOG.debug("Set database name " + dbName);
        }

        List<Param> paramList = daoParam.findCcgParamsByCcfGroupId(id);

        if (paramList == null) {
            throw new IllegalArgumentException("id = " + id);
        }

        return Response.ok(paramList).build();
    }

    @GET
    @Path("event/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findParamsByEventNum(@PathParam("num") int num, @PathParam("db") String dbName)
            throws SQLException {

        if (!DataSourceFactory.getInstance().getDataSource().getDatabaseName().equals(dbName)) {
            DataSourceFactory.getInstance().getDataSource().setDatabaseName(dbName);
            LOG.debug("Set database name " + dbName);
        }

        List<Param> paramList = daoParam.findParamsByEventNum(num);

        if (paramList == null) {
            throw new IllegalArgumentException("num = " + num);
        }

        return Response.ok(paramList).build();
    }
}

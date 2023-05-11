package ru.asgubin.rsrdb.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.asgubin.rsrdb.service.DaoEvent;
import ru.asgubin.rsrdb.model.Event;
import ru.asgubin.rsrdb.service.DataSourceFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Path("/{db}/events")
public class EventResource {

    private final DaoEvent daoEvent = DaoEvent.getInstance();

    private static final String TARGET = "%@*";
    private static final String REPLACEMENT = "/";

    private static final Logger LOG = LoggerFactory.getLogger(EventResource.class);

    @GET
    @Path("/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByNum(@PathParam("num") int num, @PathParam("db") String dbName)
            throws SQLException {

        if (!DataSourceFactory.getInstance().getDataSource().getDatabaseName().equals(dbName)) {
            DataSourceFactory.getInstance().getDataSource().setDatabaseName(dbName);
            LOG.debug("Set database name " + dbName);
        }

        Optional<Event> event = daoEvent.find(num);
        event.orElseThrow(() -> new IllegalArgumentException("num = " + num));

        return Response.ok(event.get())
                .build();
    }

    @GET
    @Path("/type/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findEventsByType(@PathParam("type") int type, @PathParam("db") String dbName)
            throws SQLException {

        if (!DataSourceFactory.getInstance().getDataSource().getDatabaseName().equals(dbName)) {
            DataSourceFactory.getInstance().getDataSource().setDatabaseName(dbName);
            LOG.debug("Set database name " + dbName);
        }

        List<Event> eventList = daoEvent.findEventsByType(type);

        if (eventList == null) {
            throw new IllegalArgumentException("type = " + type);
        }

        return Response.ok(eventList).build();
    }

    @GET
    @Path("ccfGroup/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCcfEventsByCcfGroupId(@PathParam("id") int id, @PathParam("db") String dbName)
            throws SQLException {

        if (!DataSourceFactory.getInstance().getDataSource().getDatabaseName().equals(dbName)) {
            DataSourceFactory.getInstance().getDataSource().setDatabaseName(dbName);
            LOG.debug("Set database name " + dbName);
        }

        List<Event> eventList = daoEvent.findCcfEventsByCcfGroupId(id);

        if (eventList == null) {
            throw new IllegalArgumentException("id = " + id);
        }

        return Response.ok(eventList).build();
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findEventsByEventId(@PathParam("id") String id, @PathParam("db") String dbName)
            throws SQLException {

        if (!DataSourceFactory.getInstance().getDataSource().getDatabaseName().equals(dbName)) {
            DataSourceFactory.getInstance().getDataSource().setDatabaseName(dbName);
            LOG.debug("Set database name " + dbName);
        }

        List<Event> eventList = daoEvent.findEventsByEventId(id.replace(TARGET, REPLACEMENT));

        if (eventList == null) {
            throw new IllegalArgumentException("id = " + id);
        }

        return Response.ok(eventList).build();
    }
}

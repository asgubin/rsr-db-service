package ru.asgubin.rsrdb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.asgubin.rsrdb.model.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class DaoEvent implements EventDao {

    private static final Logger LOG = LoggerFactory.getLogger(DaoEvent.class);

    private DaoEvent() {

    }

    private static class SingletonHelper {
        private static final DaoEvent INSTANCE = new DaoEvent();
    }

    public static DaoEvent getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public Optional<Event> find(Integer numEvent) throws SQLException {

        String sql = "SELECT Type,Num,ID,Text,Tag,Symbol,Model,State,CalcType," +
                "Mean,InitEnabl,EditDate,EditUid,ReviewDate,ReviewUid," +
                "ApprovedDate,ApprovedUid,flag FROM Events WHERE Num = ?";

        Connection conn = DataSourceFactory.getInstance().getConnection();
        LOG.debug("Get connection to db");

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, numEvent);
        ResultSet resultSet = statement.executeQuery();
        LOG.debug("Query to db: " + sql + "; Num = " + numEvent);

        if (resultSet.next()) {
            short type        = resultSet.getShort  ("Type");
            int num           = resultSet.getInt    ("Num");
            String id         = resultSet.getString ("ID");
            String text       = resultSet.getString ("Text");
            short tag         = resultSet.getShort  ("Tag");
            short symbol      = resultSet.getShort  ("Symbol");
            short model       = resultSet.getShort  ("Model");
            short state       = resultSet.getShort  ("State");
            short calcType    = resultSet.getShort  ("CalcType");
            double mean       = resultSet.getDouble ("Mean");
            short initEnabl   = resultSet.getShort  ("InitEnabl");
            Date editDate     = resultSet.getDate   ("EditDate");
            int editUid       = resultSet.getInt    ("EditUid");
            Date reviewDate   = resultSet.getDate   ("ReviewDate");
            int reviewUid     = resultSet.getInt    ("ReviewUid");
            Date approvedDate = resultSet.getDate   ("ApprovedDate");
            int approvedUid   = resultSet.getInt    ("ApprovedUid");
            boolean flag      = resultSet.getBoolean("flag");

            LOG.debug("Return Event WHERE Num = " + numEvent);
            return Optional.of(
                    new Event(type, num, id, text, tag, symbol,
                    model, state, calcType, mean, initEnabl, editDate,
                    editUid, reviewDate, reviewUid, approvedDate, approvedUid, flag));
        }

        LOG.debug("Return empty Event");
        return Optional.empty();
    }

    public List<Event> findCcfEventsByCcfGroupId(Integer ccfGroupId)
            throws SQLException {

        List<Event> eventList = new ArrayList<>();

        String sql = "SELECT Type,Num,ID,Text,Tag,Symbol,Model,State,CalcType," +
                "Mean,InitEnabl,EditDate,EditUid,ReviewDate,ReviewUid," +
                "ApprovedDate,ApprovedUid,flag FROM Events WHERE Events.Num IN " +
                "(SELECT EventNum FROM CCGBasic WHERE CCGBasic.CCGNum = ?)";

        Connection conn = DataSourceFactory.getInstance().getConnection();
        LOG.debug("Get connection to db");

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, ccfGroupId);
        ResultSet resultSet = statement.executeQuery();
        LOG.debug("Query to db: " + sql + "; CCFGroup.ID = " + ccfGroupId);

        while (resultSet.next()) {
            short type        = resultSet.getShort  ("Type");
            int num           = resultSet.getInt    ("Num");
            String id         = resultSet.getString ("ID");
            String text       = resultSet.getString ("Text");
            short tag         = resultSet.getShort  ("Tag");
            short symbol      = resultSet.getShort  ("Symbol");
            short model       = resultSet.getShort  ("Model");
            short state       = resultSet.getShort  ("State");
            short calcType    = resultSet.getShort  ("CalcType");
            double mean       = resultSet.getDouble ("Mean");
            short initEnabl   = resultSet.getShort  ("InitEnabl");
            Date editDate     = resultSet.getDate   ("EditDate");
            int editUid       = resultSet.getInt    ("EditUid");
            Date reviewDate   = resultSet.getDate   ("ReviewDate");
            int reviewUid     = resultSet.getInt    ("ReviewUid");
            Date approvedDate = resultSet.getDate   ("ApprovedDate");
            int approvedUid   = resultSet.getInt    ("ApprovedUid");
            boolean flag      = resultSet.getBoolean("flag");

            Event event = new Event(type, num, id, text, tag, symbol,
                    model, state, calcType, mean, initEnabl, editDate,
                    editUid, reviewDate, reviewUid, approvedDate, approvedUid, flag);

            eventList.add(event);
        }

        return eventList;
    }

    public List<Event> findEventsByType(Integer eventsType)
            throws SQLException {

        List<Event> eventList = new ArrayList<>();

        String sql = "SELECT Type,Num,ID,Text,Tag,Symbol,Model,State,CalcType," +
                "Mean,InitEnabl,EditDate,EditUid,ReviewDate,ReviewUid," +
                "ApprovedDate,ApprovedUid,flag FROM Events WHERE Type = ?";

        Connection conn = DataSourceFactory.getInstance().getConnection();
        LOG.debug("Get connection to db");

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, eventsType);
        ResultSet resultSet = statement.executeQuery();
        LOG.debug("Query to db: " + sql + "; Events.Type = " + eventsType);

        while (resultSet.next()) {
            short type        = resultSet.getShort  ("Type");
            int num           = resultSet.getInt    ("Num");
            String id         = resultSet.getString ("ID");
            String text       = resultSet.getString ("Text");
            short tag         = resultSet.getShort  ("Tag");
            short symbol      = resultSet.getShort  ("Symbol");
            short model       = resultSet.getShort  ("Model");
            short state       = resultSet.getShort  ("State");
            short calcType    = resultSet.getShort  ("CalcType");
            double mean       = resultSet.getDouble ("Mean");
            short initEnabl   = resultSet.getShort  ("InitEnabl");
            Date editDate     = resultSet.getDate   ("EditDate");
            int editUid       = resultSet.getInt    ("EditUid");
            Date reviewDate   = resultSet.getDate   ("ReviewDate");
            int reviewUid     = resultSet.getInt    ("ReviewUid");
            Date approvedDate = resultSet.getDate   ("ApprovedDate");
            int approvedUid   = resultSet.getInt    ("ApprovedUid");
            boolean flag      = resultSet.getBoolean("flag");

            Event event = new Event(type, num, id, text, tag, symbol,
                    model, state, calcType, mean, initEnabl, editDate,
                    editUid, reviewDate, reviewUid, approvedDate, approvedUid, flag);

            eventList.add(event);
        }

        return eventList;
    }

    public List<Event> findEventsByEventId(String eventId) throws SQLException {
        List<Event> eventList = new ArrayList<>();

        String sql = "SELECT Type,Num,ID,Text,Tag,Symbol,Model,State,CalcType," +
                "Mean,InitEnabl,EditDate,EditUid,ReviewDate,ReviewUid," +
                "ApprovedDate,ApprovedUid,flag FROM Events WHERE ID = ?";

        Connection conn = DataSourceFactory.getInstance().getConnection();
        LOG.debug("Get connection to db");

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, eventId);
        ResultSet resultSet = statement.executeQuery();
        LOG.debug("Query to db: " + sql + "; Events.Id = " + eventId);

        while (resultSet.next()) {
            short type        = resultSet.getShort  ("Type");
            int num           = resultSet.getInt    ("Num");
            String id         = resultSet.getString ("ID");
            String text       = resultSet.getString ("Text");
            short tag         = resultSet.getShort  ("Tag");
            short symbol      = resultSet.getShort  ("Symbol");
            short model       = resultSet.getShort  ("Model");
            short state       = resultSet.getShort  ("State");
            short calcType    = resultSet.getShort  ("CalcType");
            double mean       = resultSet.getDouble ("Mean");
            short initEnabl   = resultSet.getShort  ("InitEnabl");
            Date editDate     = resultSet.getDate   ("EditDate");
            int editUid       = resultSet.getInt    ("EditUid");
            Date reviewDate   = resultSet.getDate   ("ReviewDate");
            int reviewUid     = resultSet.getInt    ("ReviewUid");
            Date approvedDate = resultSet.getDate   ("ApprovedDate");
            int approvedUid   = resultSet.getInt    ("ApprovedUid");
            boolean flag      = resultSet.getBoolean("flag");

            Event event = new Event(type, num, id, text, tag, symbol,
                    model, state, calcType, mean, initEnabl, editDate,
                    editUid, reviewDate, reviewUid, approvedDate, approvedUid, flag);

            eventList.add(event);
        }

        return eventList;
    }
}

package ru.asgubin.rsrdb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.asgubin.rsrdb.model.Acase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class DaoAcase implements AcaseDao {

    private static final Logger LOG = LoggerFactory.getLogger(DaoAcase.class);

    private DaoAcase() {

    }

    private static class SingletonHelper {
        private static final DaoAcase INSTANCE = new DaoAcase();
    }

    public static DaoAcase getInstance() {
        return DaoAcase.SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<Acase> find(Integer numAcase) throws SQLException {
        String sql = "SELECT Type,Num,ID,Text,Tag,ResType,Mean,P05,P95,TextRes," +
                "GERes,BERes,ExchRes,Unit,EditDate,EditUid,ReviewDate,ReviewUid," +
                "ApprovedDate,ApprovedUid,flag FROM Acase WHERE Num = ?";

        Connection conn = DataSourceFactory.getInstance().getConnection();
        LOG.debug("Get connection to db");

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, numAcase);
        ResultSet resultSet = statement.executeQuery();
        LOG.debug("Query to db: " + sql + "; Num = " + numAcase);

        if (resultSet.next()) {
            short type        = resultSet.getShort  ("Type");
            int num           = resultSet.getInt    ("Num");
            String id         = resultSet.getString ("ID");
            String text       = resultSet.getString ("Text");
            short tag         = resultSet.getShort  ("Tag");
            short resType     = resultSet.getShort  ("ResType");
            double mean       = resultSet.getDouble ("Mean");
            double p05        = resultSet.getDouble ("P05");
            double p95        = resultSet.getDouble ("P95");
            short textRes     = resultSet.getShort  ("TextRes");
            short geRes       = resultSet.getShort  ("GERes");
            short beRes       = resultSet.getShort  ("BERes");
            short exchRes     = resultSet.getShort  ("ExchRes");
            short unit        = resultSet.getShort  ("Unit");
            Date editDate     = resultSet.getDate   ("EditDate");
            int editUid       = resultSet.getInt    ("EditUid");
            Date reviewDate   = resultSet.getDate   ("ReviewDate");
            int reviewUid     = resultSet.getInt    ("ReviewUid");
            Date approvedDate = resultSet.getDate   ("ApprovedDate");
            int approvedUid   = resultSet.getInt    ("ApprovedUid");
            boolean flag      = resultSet.getBoolean("flag");

            LOG.debug("Return Event WHERE Num = " + numAcase);
            return Optional.of(
                    new Acase(type, num, id, text, tag, resType,
                            mean, p05, p95, textRes, geRes, beRes, exchRes, unit, editDate,
                            editUid, reviewDate, reviewUid, approvedDate, approvedUid, flag));
        }

        LOG.debug("Return empty Acase");
        return Optional.empty();
    }
}

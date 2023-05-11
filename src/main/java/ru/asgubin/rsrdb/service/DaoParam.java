package ru.asgubin.rsrdb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.asgubin.rsrdb.model.Param;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class DaoParam implements ParamDao {

    private static final Logger LOG = LoggerFactory.getLogger(DaoParam.class);

    private DaoParam() {
    }

    private static class SingletonHelper {
        private static final DaoParam INSTANCE = new DaoParam();
    }

    public static DaoParam getInstance() {
        return DaoParam.SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<Param> find(Integer numParam) throws SQLException {

        String sql = "SELECT Type,Num,ID,Text,Tag,Mean,DistType,DistPar1,DistPar2," +
                "DistPar3,Unit,Median,P05,P95,VarCoeff,EditDate,EditUid,ReviewDate," +
                "ReviewUid,ApprovedDate,ApprovedUid,flag FROM Params WHERE Num = ?";

        Connection conn = DataSourceFactory.getInstance().getConnection();
        LOG.debug("Get connection to db");

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, numParam);
        ResultSet resultSet = statement.executeQuery();
        LOG.debug("Query to db: " + sql + "; Num = " + numParam);

        if (resultSet.next()) {

            short type        = resultSet.getShort  ("Type");
            int num           = resultSet.getInt    ("Num");
            String id         = resultSet.getString ("ID");
            String text       = resultSet.getString ("Text");
            short tag         = resultSet.getShort  ("Tag");
            float mean        = resultSet.getFloat  ("Mean");
            short distType    = resultSet.getShort  ("DistType");
            float distPar1    = resultSet.getFloat  ("DistPar1");
            float distPar2    = resultSet.getFloat  ("DistPar2");
            float distPar3    = resultSet.getFloat  ("DistPar3");
            short unit        = resultSet.getShort  ("Unit");
            float median      = resultSet.getFloat  ("Median");
            float p05         = resultSet.getFloat  ("P05");
            float p95         = resultSet.getFloat  ("P95");
            float varCoeff    = resultSet.getFloat  ("VarCoeff");
            Date editDate     = resultSet.getDate   ("EditDate");
            int editUid       = resultSet.getInt    ("EditUid");
            Date reviewDate   = resultSet.getDate   ("ReviewDate");
            int reviewUid     = resultSet.getInt    ("ReviewUid");
            Date approvedDate = resultSet.getDate   ("ApprovedDate");
            int approvedUid   = resultSet.getInt    ("ApprovedUid");
            boolean flag      = resultSet.getBoolean("flag");

            LOG.debug("Return Param WHERE Num = " + numParam);

            return Optional.of(
                    new Param(type, num, id, text, tag, mean, distType,
                            distPar1, distPar2, distPar3, unit, median,
                            p05, p95, varCoeff, editDate, editUid, reviewDate,
                            reviewUid, approvedDate, approvedUid, flag));
        }

        LOG.debug("Return empty Param");
        return Optional.empty();
    }

    public Optional<Param> findById(String idParam) throws SQLException {

        String sql = "SELECT Type,Num,ID,Text,Tag,Mean,DistType,DistPar1,DistPar2," +
                "DistPar3,Unit,Median,P05,P95,VarCoeff,EditDate,EditUid,ReviewDate," +
                "ReviewUid,ApprovedDate,ApprovedUid,flag FROM Params WHERE ID = ?";

        Connection conn = DataSourceFactory.getInstance().getConnection();
        LOG.debug("Get connection to db");

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, idParam);
        ResultSet resultSet = statement.executeQuery();
        LOG.debug("Query to db: " + sql + "; ID = " + idParam);

        if (resultSet.next()) {

            short type        = resultSet.getShort  ("Type");
            int num           = resultSet.getInt    ("Num");
            String id         = resultSet.getString ("ID");
            String text       = resultSet.getString ("Text");
            short tag         = resultSet.getShort  ("Tag");
            float mean        = resultSet.getFloat  ("Mean");
            short distType    = resultSet.getShort  ("DistType");
            float distPar1    = resultSet.getFloat  ("DistPar1");
            float distPar2    = resultSet.getFloat  ("DistPar2");
            float distPar3    = resultSet.getFloat  ("DistPar3");
            short unit        = resultSet.getShort  ("Unit");
            float median      = resultSet.getFloat  ("Median");
            float p05         = resultSet.getFloat  ("P05");
            float p95         = resultSet.getFloat  ("P95");
            float varCoeff    = resultSet.getFloat  ("VarCoeff");
            Date editDate     = resultSet.getDate   ("EditDate");
            int editUid       = resultSet.getInt    ("EditUid");
            Date reviewDate   = resultSet.getDate   ("ReviewDate");
            int reviewUid     = resultSet.getInt    ("ReviewUid");
            Date approvedDate = resultSet.getDate   ("ApprovedDate");
            int approvedUid   = resultSet.getInt    ("ApprovedUid");
            boolean flag      = resultSet.getBoolean("flag");

            LOG.debug("Return Param WHERE ID = " + idParam);

            return Optional.of(
                    new Param(type, num, id, text, tag, mean, distType,
                            distPar1, distPar2, distPar3, unit, median,
                            p05, p95, varCoeff, editDate, editUid, reviewDate,
                            reviewUid, approvedDate, approvedUid, flag));
        }

        LOG.debug("Return empty Param");
        return Optional.empty();
    }

    public List<Param> findCcgParamsByCcfGroupId(Integer ccfGroupId)
            throws SQLException {

        List<Param> paramList = new ArrayList<>();

        String sql = "SELECT Type,Num,ID,Text,Tag,Mean,DistType,DistPar1,DistPar2," +
                "DistPar3,Unit,Median,P05,P95,VarCoeff,EditDate,EditUid,ReviewDate," +
                "ReviewUid,ApprovedDate,ApprovedUid,flag FROM Params WHERE Params.Num IN " +
                "(SELECT DISTINCT ParNum FROM CCFEventPar WHERE CCFEventPar.EventNum IN " +
                "(SELECT EventNum FROM CCFRec WHERE CCFRec.CCGNum = ?) AND CCFEventPar.ParType > 40)";

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
            float mean        = resultSet.getFloat  ("Mean");
            short distType    = resultSet.getShort  ("DistType");
            float distPar1    = resultSet.getFloat  ("DistPar1");
            float distPar2    = resultSet.getFloat  ("DistPar2");
            float distPar3    = resultSet.getFloat  ("DistPar3");
            short unit        = resultSet.getShort  ("Unit");
            float median      = resultSet.getFloat  ("Median");
            float p05         = resultSet.getFloat  ("P05");
            float p95         = resultSet.getFloat  ("P95");
            float varCoeff    = resultSet.getFloat  ("VarCoeff");
            Date editDate     = resultSet.getDate   ("EditDate");
            int editUid       = resultSet.getInt    ("EditUid");
            Date reviewDate   = resultSet.getDate   ("ReviewDate");
            int reviewUid     = resultSet.getInt    ("ReviewUid");
            Date approvedDate = resultSet.getDate   ("ApprovedDate");
            int approvedUid   = resultSet.getInt    ("ApprovedUid");
            boolean flag      = resultSet.getBoolean("flag");

            Param param = new Param(type, num, id, text, tag, mean, distType,
                    distPar1, distPar2, distPar3, unit, median,
                    p05, p95, varCoeff, editDate, editUid, reviewDate,
                    reviewUid, approvedDate, approvedUid, flag);

            paramList.add(param);
        }

        return paramList;
    }

    public List<Param> findParamsByEventNum(Integer eventNum)
            throws SQLException {

        List<Param> paramList = new ArrayList<>();

        String sql = "SELECT Type,Num,ID,Text,Tag,Mean,DistType,DistPar1,DistPar2," +
                "DistPar3,Unit,Median,P05,P95,VarCoeff,EditDate,EditUid,ReviewDate," +
                "ReviewUid,ApprovedDate,ApprovedUid,flag FROM Params WHERE Num IN " +
                "(SELECT ParNum FROM EventPar WHERE ParNum IS NOT NULL AND EventNum = ?)";

        Connection conn = DataSourceFactory.getInstance().getConnection();
        LOG.debug("Get connection to db");

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, eventNum);
        ResultSet resultSet = statement.executeQuery();
        LOG.debug("Query to db: " + sql + "; Event.Num = " + eventNum);

        while (resultSet.next()) {

            short type        = resultSet.getShort  ("Type");
            int num           = resultSet.getInt    ("Num");
            String id         = resultSet.getString ("ID");
            String text       = resultSet.getString ("Text");
            short tag         = resultSet.getShort  ("Tag");
            float mean        = resultSet.getFloat  ("Mean");
            short distType    = resultSet.getShort  ("DistType");
            float distPar1    = resultSet.getFloat  ("DistPar1");
            float distPar2    = resultSet.getFloat  ("DistPar2");
            float distPar3    = resultSet.getFloat  ("DistPar3");
            short unit        = resultSet.getShort  ("Unit");
            float median      = resultSet.getFloat  ("Median");
            float p05         = resultSet.getFloat  ("P05");
            float p95         = resultSet.getFloat  ("P95");
            float varCoeff    = resultSet.getFloat  ("VarCoeff");
            Date editDate     = resultSet.getDate   ("EditDate");
            int editUid       = resultSet.getInt    ("EditUid");
            Date reviewDate   = resultSet.getDate   ("ReviewDate");
            int reviewUid     = resultSet.getInt    ("ReviewUid");
            Date approvedDate = resultSet.getDate   ("ApprovedDate");
            int approvedUid   = resultSet.getInt    ("ApprovedUid");
            boolean flag      = resultSet.getBoolean("flag");

            Param param = new Param(type, num, id, text, tag, mean, distType,
                    distPar1, distPar2, distPar3, unit, median,
                    p05, p95, varCoeff, editDate, editUid, reviewDate,
                    reviewUid, approvedDate, approvedUid, flag);

            paramList.add(param);
        }

        return paramList;
    }
}

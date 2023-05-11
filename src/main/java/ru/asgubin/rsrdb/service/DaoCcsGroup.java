package ru.asgubin.rsrdb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.asgubin.rsrdb.model.CcfGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class DaoCcsGroup implements CcfGroupDao{

    private static final Logger LOG = LoggerFactory.getLogger(DaoCcsGroup.class);

    private DaoCcsGroup() {

    }

    private static class SingletonHelper {
        private static final DaoCcsGroup INSTANCE = new DaoCcsGroup();
    }

    public static DaoCcsGroup getInstance() {
        return DaoCcsGroup.SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<CcfGroup> find(Integer numCcfGroup) throws SQLException {

        String sql = "SELECT Type,Num,ID,Text,Tag,CCFModel," +
                "EditDate,EditUid,ReviewDate,ReviewUid,ApprovedDate," +
                "ApprovedUid,flag,CCFAlpha8Bound FROM CCFGroup WHERE Num = ?";

        Connection conn = DataSourceFactory.getInstance().getConnection();
        LOG.debug("Get connection to db");

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, numCcfGroup);
        ResultSet resultSet = statement.executeQuery();
        LOG.debug("Query to db: " + sql + "; Num = " + numCcfGroup);

        if (resultSet.next()) {

            short type         = resultSet.getShort  ("Type");
            int num            = resultSet.getInt    ("Num");
            String id          = resultSet.getString ("ID");
            String text        = resultSet.getString ("Text");
            short tag          = resultSet.getShort  ("Tag");
            short ccfModel     = resultSet.getShort  ("CCFModel");
            Date editDate      = resultSet.getDate   ("EditDate");
            int editUid        = resultSet.getInt    ("EditUid");
            Date reviewDate    = resultSet.getDate   ("ReviewDate");
            int reviewUid      = resultSet.getInt    ("ReviewUid");
            Date approvedDate  = resultSet.getDate   ("ApprovedDate");
            int approvedUid    = resultSet.getInt    ("ApprovedUid");
            boolean flag       = resultSet.getBoolean("flag");
            int ccfAlpha8Bound = resultSet.getInt    ("CCFAlpha8Bound");

            LOG.debug("Return CCSGroup WHERE Num = " + numCcfGroup);
            return Optional.of(
                    new CcfGroup(type, num, id, text, tag, ccfModel,
                            editDate, editUid, reviewDate, reviewUid,
                            approvedDate, approvedUid, flag, ccfAlpha8Bound));
        }

        LOG.debug("Return empty CCSGroup");
        return Optional.empty();
    }

    public List<CcfGroup> findAll() throws SQLException {

        List<CcfGroup> ccfGroupList = new ArrayList<>();

        String sql = "SELECT Type,Num,ID,Text,Tag,CCFModel," +
                "EditDate,EditUid,ReviewDate,ReviewUid,ApprovedDate," +
                "ApprovedUid,flag,CCFAlpha8Bound FROM CCFGroup";

        Connection conn = DataSourceFactory.getInstance().getConnection();
        LOG.debug("Get connection to db");

        Statement statement = conn.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        LOG.debug("Query to db: " + sql);

        while (resultSet.next()) {

            short type         = resultSet.getShort  ("Type");
            int num            = resultSet.getInt    ("Num");
            String id          = resultSet.getString ("ID");
            String text        = resultSet.getString ("Text");
            short tag          = resultSet.getShort  ("Tag");
            short ccfModel     = resultSet.getShort  ("CCFModel");
            Date editDate      = resultSet.getDate   ("EditDate");
            int editUid        = resultSet.getInt    ("EditUid");
            Date reviewDate    = resultSet.getDate   ("ReviewDate");
            int reviewUid      = resultSet.getInt    ("ReviewUid");
            Date approvedDate  = resultSet.getDate   ("ApprovedDate");
            int approvedUid    = resultSet.getInt    ("ApprovedUid");
            boolean flag       = resultSet.getBoolean("flag");
            int ccfAlpha8Bound = resultSet.getInt    ("CCFAlpha8Bound");

            CcfGroup ccfGroup = new CcfGroup(type, num, id, text, tag,
                    ccfModel, editDate, editUid, reviewDate, reviewUid,
                    approvedDate, approvedUid, flag, ccfAlpha8Bound);

            ccfGroupList.add(ccfGroup);
        }

        return ccfGroupList;
    }
}

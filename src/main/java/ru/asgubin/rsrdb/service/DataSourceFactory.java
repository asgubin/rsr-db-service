package ru.asgubin.rsrdb.service;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.asgubin.rsrdb.utils.AppProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceFactory {
    private final SQLServerDataSource dataSource;

    private static final Logger LOG = LoggerFactory.getLogger(DataSourceFactory.class);

    private DataSourceFactory() {
        Properties properties = AppProperties.getProperties();
        SQLServerDataSource dataSource = new SQLServerDataSource();

        dataSource.setServerName(properties.getProperty("serverName"));
        dataSource.setInstanceName(properties.getProperty("instanceName"));
        dataSource.setPortNumber(Integer.parseInt(properties.getProperty("portNumber")));
        dataSource.setDatabaseName(properties.getProperty("databaseName"));
        dataSource.setTrustServerCertificate(Boolean.parseBoolean(properties.getProperty("trustCertificate")));
        dataSource.setUser(properties.getProperty("user"));
        dataSource.setPassword(properties.getProperty("password"));

        this.dataSource = dataSource;
    }

    public static DataSourceFactory getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final DataSourceFactory INSTANCE = new DataSourceFactory();
    }

    public Connection getConnection() throws SQLException {
        LOG.debug("Connection to DB " + dataSource.getDatabaseName());
        return dataSource.getConnection();
    }

    public SQLServerDataSource getDataSource() {
        return dataSource;
    }
}

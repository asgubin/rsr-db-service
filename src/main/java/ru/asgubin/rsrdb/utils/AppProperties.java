package ru.asgubin.rsrdb.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public final class AppProperties {

    private static final Logger LOG = LoggerFactory.getLogger(AppProperties.class);

    private final Properties properties;

    private AppProperties() {

        Properties properties = new Properties();
        LOG.debug("Create empty properties");

        String rootPath = Objects
                .requireNonNull(Thread.currentThread()
                        .getContextClassLoader()
                        .getResource("application.properties"))
                .getPath();
        LOG.debug("Get root path");


        LOG.debug("Going load application.properties file");
        try(InputStream input = new FileInputStream(rootPath)) {

            properties.load(input);

        } catch (IOException e) {
            LOG.error("Can't load application.properties " + e);
        }
        LOG.debug("Application.properties file successful load");

        this.properties = properties;
    }

    private static class ReadPropertiesHolder {
        private static final AppProperties INSTANCE = new AppProperties();
    }

    public static Properties getProperties() {
        //LOG.debug("ReadProperties.access() called");
        return ReadPropertiesHolder.INSTANCE.properties;
    }
}

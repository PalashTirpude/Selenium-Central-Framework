package com.central.framework.genericutils;

import lombok.extern.slf4j.Slf4j;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public final class LoadProperties {

    // Private constructor to prevent instantiation
    private LoadProperties() {}

    /**
     * Loads and returns properties from a given file path.
     *
     * @param filePath Absolute or relative path to the .properties file
     * @return Properties object loaded with key-value pairs
     * @throws RuntimeException if the file cannot be read
     */
    public static Properties fromFile(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            log.error("Error loading property file from filepath {}",filePath, e);
            throw new RuntimeException("Failed to load properties file: " + filePath, e);
        }
        return properties;
    }
}
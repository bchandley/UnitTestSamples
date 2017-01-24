/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bchandley.testing.unittestsamples;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;

/**
 *
 * @author bchandley
 */
public class FileProcessor {

    public ProcessFileResponse processFile(ProcessFileRequest request) throws SQLException {
        if (request == null) {
            throw new IllegalArgumentException("ProcessFileRequest is null");
        }
        if (request.advetiserCode == null || request.advetiserCode.trim().equals("")) {
            throw new IllegalArgumentException("AdvetiserCode is not provided");
        }
        if (request.publisherCode == null || request.publisherCode.trim().equals("")) {
            throw new IllegalArgumentException("publisherCode is not provided");
        }
        if (request.fileName == null || request.fileName.trim().equals("")) {
            throw new IllegalArgumentException("fileName is not provided");
        }
        if (request.filePath == null || request.filePath.trim().equals("")) {
            throw new IllegalArgumentException("filePath is not provided");
        }

        Path p = Paths.get(request.fileName, request.filePath);
        if (!Files.exists(p)) {
            throw new IllegalArgumentException("The file specified file does not exist (" + p.toString() + ")");
        }

        ProcessFileResponse response = new ProcessFileResponse();
        Properties connectionProps = new Properties();
        connectionProps.put("user", "app");
        connectionProps.put("password", "app");

        String selectAdvertiserSql = "SELECT id FROM files.advertiser where advertiserCode=?";
        String selectPublisherSql = "SELECT id FROM files.publisher  where publisherCode=?";
        String registerFileSql = "INSERT INTO    files.registered_file (path,fileName,publisher_id,advertiser_id,createddate) VALUES (?,?,?,?,?)";
        String baseConnString = "jdbc:mysql://localhost:3306/files?useSSL=false";
        try (
                Connection conn = DriverManager.getConnection(baseConnString, connectionProps);
                PreparedStatement selectAdvertiser = conn.prepareStatement(selectAdvertiserSql);
                PreparedStatement selectPublisher = conn.prepareStatement(selectPublisherSql);
                PreparedStatement registerFile = conn.prepareStatement(registerFileSql, Statement.RETURN_GENERATED_KEYS);) {
            selectAdvertiser.setString(1, request.advetiserCode);
            selectPublisher.setString(1, request.publisherCode);

            registerFile.setString(1, request.filePath);
            registerFile.setString(2, request.fileName);

            ResultSet advertiserResults = selectAdvertiser.executeQuery();
            if (advertiserResults == null || !advertiserResults.first()) {
                throw new IllegalArgumentException("Advertiser Code could not be found '" + request.advetiserCode + "'");
            }
            ResultSet publisherResults = selectPublisher.executeQuery();
            if (publisherResults == null || !publisherResults.first()) {
                throw new IllegalArgumentException("publisher Code could not be found '" + request.publisherCode + "'");
            }
            registerFile.setInt(3, advertiserResults.getInt(1));
            registerFile.setInt(4, publisherResults.getInt(1));

            java.util.Date utilDate = new java.util.Date();
            registerFile.setDate(5, new Date(utilDate.getTime()));
            registerFile.executeUpdate();
            ResultSet rs = registerFile.getGeneratedKeys();
            if(rs.next())
                response.responseId=rs.getInt(1);
            
        }

        return response;

    }
}

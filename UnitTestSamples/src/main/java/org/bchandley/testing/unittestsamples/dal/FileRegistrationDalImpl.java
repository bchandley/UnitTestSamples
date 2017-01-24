/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bchandley.testing.unittestsamples.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bchandley.testing.unittestsamples.model.RegisteredFile;


public class FileRegistrationDalImpl implements FileRegistrationDal {

    /**
     * Saves the RegisteredFile to the database
     * @param registeredFile
     * @return 
     */
    @Override
    public RegisteredFile save(RegisteredFile registeredFile) {
        
        
        Integer returnVal = null;
        String registerFileSql = "INSERT INTO    files.registered_file (path,fileName,publisher_id,advertiser_id,createddate) VALUES (?,?,?,?,?)";
        Properties connectionProps = new Properties();
        connectionProps.put("user", "app");
        connectionProps.put("password", "app");
        String baseConnString = "jdbc:mysql://localhost:3306/files?useSSL=false";

        try (
                Connection conn = DriverManager.getConnection(baseConnString, connectionProps);
                PreparedStatement registerFile = conn.prepareStatement(registerFileSql);) {
            
            registerFile.setString(1, registeredFile.getFilePath());
            registerFile.setString(2, registeredFile.getFileName());
            registerFile.setInt(3, registeredFile.getAdvertiserId());
            registerFile.setInt(4, registeredFile.getPublisherId());
            
            java.util.Date utilDate = new java.util.Date();
            registerFile.setDate(5, new Date(utilDate.getTime()));
            registerFile.executeUpdate();
            ResultSet rs = registerFile.getGeneratedKeys();
            if(rs.next())
                registeredFile.setRegisteredFileId(rs.getInt(1));
            
            

        } catch (SQLException ex) {
            Logger.getLogger(AdvertiserDalImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registeredFile;
        
    }
    
}

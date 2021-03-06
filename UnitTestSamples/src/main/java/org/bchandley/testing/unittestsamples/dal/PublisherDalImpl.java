/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bchandley.testing.unittestsamples.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PublisherDalImpl implements PublisherDal {

    @Override
    public Integer getIdByCode(String code) {
        Integer returnVal = null;
        String selectPublisherSql = "SELECT id FROM files.publisher  where publisherCode=?";
        Properties connectionProps = new Properties();
        connectionProps.put("user", "app");
        connectionProps.put("password", "app");
        String baseConnString = "jdbc:mysql://localhost:3306/files?useSSL=false";

        try (
                Connection conn = DriverManager.getConnection(baseConnString, connectionProps);
                PreparedStatement selectAdvertiser = conn.prepareStatement(selectPublisherSql);) {
            selectAdvertiser.setString(1, code);
            ResultSet advertiserResults = selectAdvertiser.executeQuery();
            if (advertiserResults == null || !advertiserResults.first()) {
                throw new IllegalArgumentException("Publisher Code could not be found '" + code + "'");
            }else{
                returnVal = (advertiserResults.getInt(1) == 0) ? null : advertiserResults.getInt(1);             
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdvertiserDalImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnVal;
    }
    
}

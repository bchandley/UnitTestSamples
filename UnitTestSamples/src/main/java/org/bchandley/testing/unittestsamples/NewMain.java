/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bchandley.testing.unittestsamples;

import org.bchandley.testing.unittestsamples.model.ProcessFileRequest;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bchandley
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        FileProcessor f= new FileProcessor();
        ProcessFileRequest request  = new ProcessFileRequest();
        request.advetiserCode="ADVERT";
        request.publisherCode="PUB";
        
        request.fileName="C:\\temp";
        request.filePath="test.txt";
        
        try {
            f.processFile(request);
        } catch (SQLException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
}

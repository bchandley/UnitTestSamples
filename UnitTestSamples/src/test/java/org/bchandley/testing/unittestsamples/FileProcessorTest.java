/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bchandley.testing.unittestsamples;

import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author bchandley
 */
public class FileProcessorTest {
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
            
    /**
     * Test of processFile method, of class FileProcessor.
     */
    @Test
    public void processFile_validInputValid_RegistersFile() throws Exception {
        //ARRABGE
        FileProcessor sut= new FileProcessor();
        ProcessFileRequest request  = GetValidRequest();                
        //ACT
        ProcessFileResponse resp=  sut.processFile(request);        
        //ASSERT
        assertNotNull("The file shoudl have been registered. The Id is not set",resp.responseId);
    }

    @Test
    public void processFile_AdvertiswerCodeNull_ThrowsException() throws Exception {
        //ARRANGE
        FileProcessor sut= new FileProcessor();
        ProcessFileRequest request  = GetValidRequest();                
        request.advetiserCode=null;
    
        
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(containsString("AdvetiserCode is not provided"));
       
        //ACT        
        ProcessFileResponse resp=  sut.processFile(request);                        
    }
    
    @Test
    public void processFile_publisherCodeNull_ThrowsException() throws Exception {
        //ARRANGE
        FileProcessor sut= new FileProcessor();
        ProcessFileRequest request  = GetValidRequest();                
        request.publisherCode=null;
    
        
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(containsString("publisherCode is not provided"));
       
        //ACT        
        ProcessFileResponse resp=  sut.processFile(request);                        
    }
    
    @Test
    public void processFile_fileNameNull_ThrowsException() throws Exception {
        //ARRANGE
        FileProcessor sut= new FileProcessor();
        ProcessFileRequest request  = GetValidRequest();                
        request.fileName=null;
    
        
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(containsString("fileName is not provided"));
       
        //ACT        
        ProcessFileResponse resp=  sut.processFile(request);                        
    }
    
    @Test
    public void processFile_filePathNull_ThrowsException() throws Exception {
        //ARRANGE
        FileProcessor sut= new FileProcessor();
        ProcessFileRequest request  = GetValidRequest();                
        request.filePath=null;
    
        
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(containsString("filePath is not provided"));
       
        //ACT        
        ProcessFileResponse resp=  sut.processFile(request);                        
    }
    
    
    //These are hard to write. setup, and restoring state is complicated
    // public void processFile_filePathDoesNotExist_ThrowsException() throws Exception 
    
    // public void processFile_AdvertiserDoesNotExist_ThrowsException() throws Exception 
    
    // public void processFile_PublisherDoesNotExist_ThrowsException() throws Exception 
    
    // public void processFile_DBNotAvailiable_ThrowsException() throws Exception 
    
    /**
     * Creates a valid request for a call to FileProcessor.processFile
     * @return 
     */
    private ProcessFileRequest GetValidRequest() {
        ProcessFileRequest request  = new ProcessFileRequest();
        //trhese are in my DB
        request.advetiserCode="ADVERT";
        request.publisherCode="PUB";
        //this exists on my machine
        request.fileName="C:\\temp";
        request.filePath="test.txt";            
        return request;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bchandley.testing.unittestsamples;

import org.bchandley.testing.unittestsamples.model.ProcessFileResponse;
import org.bchandley.testing.unittestsamples.model.ProcessFileRequest;
import java.sql.SQLException;
import java.util.Calendar;

import org.bchandley.testing.unittestsamples.dal.AdvertiserDal;
import org.bchandley.testing.unittestsamples.dal.AdvertiserDalImpl;
import org.bchandley.testing.unittestsamples.dal.FileRegistrationDal;
import org.bchandley.testing.unittestsamples.dal.FileRegistrationDalImpl;
import org.bchandley.testing.unittestsamples.dal.PublisherDal;
import org.bchandley.testing.unittestsamples.dal.PublisherDalImpl;
import org.bchandley.testing.unittestsamples.mamagers.FileSystemManager;
import org.bchandley.testing.unittestsamples.mamagers.FileSystemManagerImpl;
import org.bchandley.testing.unittestsamples.model.RegisteredFile;



/**
 *
 * @author bchandley
 */
public class FileProcessor {
    
    /**
     * Manages data for advertiser data access
     */
    private AdvertiserDal advertiserDal = new AdvertiserDalImpl();
    
    /**
     * Manages data for advertiser data access
     */    
    private PublisherDal publisherDal = new PublisherDalImpl();
    
    /**
     * Manages data access operations for 
     */
    private FileRegistrationDal fileRegistrationDal = new FileRegistrationDalImpl();
    
    /**
     * Manages file-related operations
     */
    private FileSystemManager fileSystemManager = new FileSystemManagerImpl();
    
    
    /**
     * Process the fi=                                 
     * +le and
     * @param request
     * @return
     * @throws SQLException 
     */
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
        
        if (!fileSystemManager.FileExists(request.fileName, request.filePath)) {
            throw new IllegalArgumentException("The file '"+  request.fileName + "' does not exist in directory: " +request.filePath );
        }

        ProcessFileResponse response = new ProcessFileResponse();
        Integer advertiserId= advertiserDal.getIdByCode(request.advetiserCode);
        Integer publisherId= publisherDal.getIdByCode(request.publisherCode);
        RegisteredFile registeredFile = new RegisteredFile();
        registeredFile.setFileName(request.fileName);
        registeredFile.setFilePath(request.filePath);
        registeredFile.setAdvertiserId(advertiserId);
        registeredFile.setPublisherId(publisherId);
        registeredFile.setRegisteredDate(Calendar.getInstance());
   
        return response;

    }

    /**
     * Manages data for advertiser data access
     * @param advertiserDal the advertiserDal to set
     */
    protected void setAdvertiserDal(AdvertiserDal advertiserDal) {
        this.advertiserDal = advertiserDal;
    }

    /**
     * Manages data for advertiser data access
     * @param publisherDal the publisherDal to set
     */
    protected void setPublisherDal(PublisherDal publisherDal) {
        this.publisherDal = publisherDal;
    }

    /**
     * Manages data access operations for
     * @param fileRegistrationDal the fileRegistrationDal to set
     */
    protected void setFileRegistrationDal(FileRegistrationDal fileRegistrationDal) {
        this.fileRegistrationDal = fileRegistrationDal;
    }

    /**
     * Manages file-related operations
     * @param fileSystemManager the fileSystemManager to set
     */
    protected void setFileSystemManager(FileSystemManager fileSystemManager) {
        this.fileSystemManager = fileSystemManager;
    }
}

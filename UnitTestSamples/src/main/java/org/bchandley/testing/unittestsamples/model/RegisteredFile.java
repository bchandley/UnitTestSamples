/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bchandley.testing.unittestsamples.model;

import java.util.Calendar;

/**
 *
 * @author bchandley
 */
public class RegisteredFile {
    /**
     * The File Name to register
     */
    private String fileName;
    /**
     * The path to the file to register
     */    
    private String filePath;    
    /**
     * The advertiser Id associated with the file
     */
    private Integer advertiserId;
    /**
     * The publisher Id associated with the file
     */
    private Integer publisherId;
    /**
     * When the file was registered
     */
    private Calendar registeredDate;
    
    
    private Integer registeredFileId;

    /**
     * The File Name to register
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * The File Name to register
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * The path to the file to register
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * The path to the file to register
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * The advertiser Id associated with the file
     * @return the advertiserId
     */
    public Integer getAdvertiserId() {
        return advertiserId;
    }

    /**
     * The advertiser Id associated with the file
     * @param advertiserId the advertiserId to set
     */
    public void setAdvertiserId(Integer advertiserId) {
        this.advertiserId = advertiserId;
    }

    /**
     * The publisher Id associated with the file
     * @return the publisherId
     */
    public Integer getPublisherId() {
        return publisherId;
    }

    /**
     * The publisher Id associated with the file
     * @param publisherId the publisherId to set
     */
    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    /**
     * When the file was registered
     * @return the registeredDate
     */
    public Calendar getRegisteredDate() {
        return registeredDate;
    }

    /**
     * When the file was registered
     * @param registeredDate the registeredDate to set
     */
    public void setRegisteredDate(Calendar registeredDate) {
        this.registeredDate = registeredDate;
    }

    /**
     * @return the registeredFileId
     */
    public Integer getRegisteredFileId() {
        return registeredFileId;
    }

    /**
     * @param registeredFileId the registeredFileId to set
     */
    public void setRegisteredFileId(Integer registeredFileId) {
        this.registeredFileId = registeredFileId;
    }
    
}

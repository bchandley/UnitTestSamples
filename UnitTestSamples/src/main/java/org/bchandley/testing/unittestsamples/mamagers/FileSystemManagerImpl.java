/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bchandley.testing.unittestsamples.mamagers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * an implementatino of FileSystemManager that uses java.nio.file.*.* to interact with the file system
 * @author bchandley
 */
public class FileSystemManagerImpl implements FileSystemManager {

    /**
     * Indicates if the specified file can be found in the specified directory.
     * @param filePath the directory to loo in for the file.
     * @param fileName the name of the file
     * @return true if the file exists. false if not
     */
    @Override
    public boolean FileExists(String filePath, String fileName) {
        Path path = Paths.get(filePath, fileName);
        return Files.exists(path);
    }
    
}

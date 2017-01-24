/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bchandley.testing.unittestsamples.dal;

import org.bchandley.testing.unittestsamples.model.RegisteredFile;

/**
 *
 * @author bchandley
 */
public interface FileRegistrationDal {
    RegisteredFile save(RegisteredFile registeredFile);
    
}

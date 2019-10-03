/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_layer;

import database_layer.SubjectDB;
import java.util.List;
import model.SubjectModel;

/**
 * Business class of Subject
 */
public class SubjectBusiness {

    private final SubjectDB subjectDB = new SubjectDB();

    /**
     * Get all subject ID
     *
     * @return List of subject ID
     */
    public List<String> getIDs() {
        return subjectDB.getIDs();
    }
    
    /**
     * Get subject by ID
     * 
     * @param id subject ID
     * @return Subject
     */
    public SubjectModel getByID(String id) {
        return subjectDB.getByID(id);
    }
}

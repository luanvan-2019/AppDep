/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_layer;

import database_layer.TeacherDB;
import java.util.List;
import java.util.stream.Collectors;
import model.TeacherModel;

/**
 * Business class of teacher
 */
public class TeacherBusiness {
    
    private final TeacherDB teacherDB = new TeacherDB();
    
    /**
     * Get all teachers info
     * 
     * @return List of teachers
     */
    public List<TeacherModel> getAll() {
        return teacherDB.getAll();
    }
    
    /**
     * Get list of teacher ID
     * 
     * @return List of teacher ID
     */
    public List<String> getIDs() {
        List<TeacherModel> teachers = teacherDB.getAll();
        List<String> ids = teachers.stream().map(x -> x.getId()).collect(Collectors.toList());
        return ids;
    }
    
    /**
     * Get teacher info by ID
     *
     * @param id teacher ID
     * @return Teacher info
     */
    public TeacherModel getByID(String id) {
        return teacherDB.getByID(id);
    }
}

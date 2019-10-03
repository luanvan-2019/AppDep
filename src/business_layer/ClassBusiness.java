/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_layer;

import database_layer.ClassDB;
import java.util.List;
import java.util.stream.Collectors;
import model.ClassModel;

/**
 * Business of class info
 */
public class ClassBusiness {

    private final ClassDB classDB = new ClassDB();

    private final StudentBusiness studentBS = new StudentBusiness();

    /**
     * Get all class info
     *
     * @return List of ClassModel
     */
    public List<ClassModel> getAll() {
        return classDB.getAll();
    }

    /**
     * Get class by ID
     *
     * @param id class ID
     * @return Class info
     */
    public ClassModel getByID(String id) {
        return classDB.getByID(id);
    }

    /**
     * Search class info by key
     *
     * @param key key for search
     * @return list of class
     */
    public List<ClassModel> searchClass(String key) {
        return classDB.searchClass(key);
    }

    /**
     * Get all class ID
     *
     * @return list of class ID
     */
    public List<String> getIDs() {
        List<ClassModel> list = classDB.getAll();
        List<String> ids = list.stream().map(x -> x.getId()).collect(Collectors.toList());
        return ids;
    }

    /**
     * Update class info
     *
     * @param model class info
     * @return rows updated
     */
    public int update(ClassModel model) {
        return classDB.update(model);
    }

    /**
     * Delete class info
     *
     * @param classID class ID
     * @return rows deleted
     */
    public int delete(String classID) {
        return classDB.delete(classID);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_layer;

import database_layer.ChangeClassDB;
import database_layer.ConductDB;
import database_layer.RewardDB;
import database_layer.StudentDB;
import java.util.List;
import java.util.stream.Collectors;
import model.ChangeClassModel;
import model.ConductModel;
import model.RewardModel;
import model.StudentModel;

/**
 * Business class of Student
 */
public class StudentBusiness {

    private final StudentDB studentDB = new StudentDB();

    private final RewardDB rewardDB = new RewardDB();

    private final ChangeClassDB changeClassDB = new ChangeClassDB();
    
    private final ConductDB conductDB = new ConductDB();

    /**
     * Get student by id
     *
     * @param id the id
     * @return student info
     */
    public StudentModel getStudentByID(String id) {
        return studentDB.getStudentByID(id);
    }

    /**
     * Get all students
     *
     * @return List of students
     */
    public List<StudentModel> getAll() {
        return studentDB.getAll();
    }

    /**
     * Get students in the class
     *
     * @param classID class ID
     * @return List of students
     */
    public List<StudentModel> getStudentsInClass(String classID) {
        return studentDB.getStudentsInClass(classID);
    }

    /**
     * Get students by key search
     *
     * @param key key for searching
     * @return List of students
     */
    public List<StudentModel> searchStudent(String key) {
        return studentDB.searchStudent(key);
    }

    /**
     * Get all student ID
     *
     * @return List of student ID
     */
    public List<String> getIDs() {
        List<StudentModel> students = studentDB.getAll();
        List<String> ids = students.stream().map(x -> {
            return x.getId();
        }).collect(Collectors.toList());
        return ids;
    }

    /**
     * Insert student into QLSinhVien table
     *
     * @param student student info
     * @return rows inserted
     */
    public int insert(StudentModel student) {
        return studentDB.insert(student);
    }

    /**
     * Update student info
     *
     * @param student student info
     * @return rows updated
     */
    public int update(StudentModel student) {
        return studentDB.update(student);
    }

    /**
     * Delete student
     *
     * @param studentID Student ID
     * @return rows deleted
     */
    public int delete(String studentID) {
        return studentDB.delete(studentID);
    }

    /**
     * Get all rewards info
     *
     * @return List of rewards info
     */
    public List<RewardModel> getAllRewards() {
        return rewardDB.getAll();
    }

    /**
     * Insert data into QLThuongPhat table
     *
     * @param reward reward info
     * @return row inserted
     */
    public int insertReward(RewardModel reward) {
        return rewardDB.insert(reward);
    }

    /**
     * Update data in QLThuongPhat table
     *
     * @param reward reward info
     * @return row updated
     */
    public int updateReward(RewardModel reward) {
        return rewardDB.update(reward);
    }

    /**
     * Delete data from QLThuongPhat table
     *
     * @param id reward ID
     * @return row deleted
     */
    public int deleteReward(int id) {
        return rewardDB.delete(id);
    }

    /**
     * Get all change class info
     *
     * @return List of ChangeClassModel
     */
    public List<ChangeClassModel> getAllChangeClass() {
        return changeClassDB.getAll();
    }

    /**
     * Insert change class info into QLChuyenLop table
     *
     * @param model Change class info
     * @return rows inserted
     */
    public int insertChangeClass(ChangeClassModel model) {
        return changeClassDB.insert(model);
    }

    /**
     * Update change class info in QLChuyenLop table
     *
     * @param model Change class info
     * @return rows updated
     */
    public int updateChangeClass(ChangeClassModel model) {
        return changeClassDB.update(model);
    }

    /**
     * Delete change class info
     *
     * @param id change class ID
     * @return rows deleted
     */
    public int deleteChangeClass(int id) {
        return changeClassDB.delete(id);
    }
    
    /**
     * Get conduct info by student ID
     *
     * @param studentID student ID
     * @return Conduct info
     */
    public ConductModel getConductByStudentID(String studentID) {
        return conductDB.getByStudentID(studentID);
    }
}

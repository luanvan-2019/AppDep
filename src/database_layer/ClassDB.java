/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_layer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ClassModel;

/**
 * Class for getting data from QLLop table
 */
public class ClassDB {

    private final ConnectDB connectDB = new ConnectDB();

    /**
     * Get all class info
     *
     * @return list of class
     */
    public List<ClassModel> getAll() {
        List<ClassModel> list = new ArrayList<>();
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return list;
        }

        try {
            CallableStatement call = conn.prepareCall("SELECT * FROM QLLop");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                ClassModel model = new ClassModel();
                model.setId(rs.getString("MaLop"));
                model.setName(rs.getString("TenLop"));
                model.setCourse(rs.getString("KhoaHoc"));
                model.setNumberOfStudent(rs.getInt("SiSo"));
                model.setMonitor(rs.getString("LopTruong"));
                model.setTeacher(rs.getString("GVCN"));
                list.add(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    /**
     * Get class by ID
     *
     * @param id class ID
     * @return Class info
     */
    public ClassModel getByID(String id) {
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        ClassModel model = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM QLLOP WHERE MaLop=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                model = new ClassModel();
                model.setId(rs.getString("MaLop"));
                model.setName(rs.getString("TenLop"));
                model.setCourse(rs.getString("KhoaHoc"));
                model.setNumberOfStudent(rs.getInt("SiSo"));
                model.setMonitor(rs.getString("LopTruong"));
                model.setTeacher(rs.getString("GVCN"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return model;
    }

    /**
     * Search class info by key
     *
     * @param key key for searching
     * @return list of class
     */
    public List<ClassModel> searchClass(String key) {
        List<ClassModel> list = new ArrayList<>();
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return list;
        }

        try {
            String sql = "SELECT * FROM QLLop WHERE MaLop=? OR TenLop like ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, key);
            String likeValue = "%" + key + "%";
            ps.setString(2, likeValue);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClassModel model = new ClassModel();
                model.setId(rs.getString("MaLop"));
                model.setName(rs.getString("TenLop"));
                model.setCourse(rs.getString("KhoaHoc"));
                model.setNumberOfStudent(rs.getInt("SiSo"));
                model.setMonitor(rs.getString("LopTruong"));
                model.setTeacher(rs.getString("GVCN"));
                list.add(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    /**
     * Update class info
     *
     * @param model class info
     * @return rows updated
     */
    public int update(ClassModel model) {
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        int row = 0;
        String sql = "UPDATE QLLop SET TenLop=?, KhoaHoc=?, SiSo=?, LopTruong=?, GVCN=? WHERE MaLop=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, model.getName());
            ps.setString(2, model.getCourse());
            ps.setInt(3, model.getNumberOfStudent());
            ps.setString(4, model.getMonitor());
            ps.setString(5, model.getTeacher());
            ps.setString(6, model.getId());
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return row;
    }

    /**
     * Delete class info
     *
     * @param classID class ID
     * @return rows deleted
     */
    public int delete(String classID) {
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        int row = 0;
        String sql = "DELETE FROM QLLop WHERE MaLop=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, classID);
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return row;
    }
}

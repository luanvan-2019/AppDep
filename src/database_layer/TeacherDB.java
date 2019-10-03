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
import model.TeacherModel;

/**
 * Class for getting data from QLGV table
 */
public class TeacherDB {

    private final ConnectDB connectDB = new ConnectDB();

    /**
     * Get all teachers info
     *
     * @return List of teachers
     */
    public List<TeacherModel> getAll() {
        List<TeacherModel> list = new ArrayList<>();
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return list;
        }

        try {
            CallableStatement call = conn.prepareCall("SELECT * FROM QLGV");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                TeacherModel model = new TeacherModel();
                model.setId(rs.getString("MaGV"));
                model.setName(rs.getString("HoTenGV"));
                model.setBirthday(rs.getDate("NgaySinh"));
                model.setGender(rs.getInt("GioiTinh"));
                model.setPhoneNumber(rs.getString("DienThoai"));
                model.setEmail(rs.getString("Email"));
                model.setAddress(rs.getString("DiaChi"));
                model.setPlaceOfBirth(rs.getString("NoiSinh"));
                model.setDegree(rs.getString("TrinhDo"));
                list.add(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    /**
     * Get teacher info by ID
     *
     * @param id teacher ID
     * @return Teacher info
     */
    public TeacherModel getByID(String id) {
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        TeacherModel model = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM QLGV WHERE MaGV=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                model = new TeacherModel();
                model.setId(rs.getString("MaGV"));
                model.setName(rs.getString("HoTenGV"));
                model.setBirthday(rs.getDate("NgaySinh"));
                model.setGender(rs.getInt("GioiTinh"));
                model.setPhoneNumber(rs.getString("DienThoai"));
                model.setEmail(rs.getString("Email"));
                model.setAddress(rs.getString("DiaChi"));
                model.setPlaceOfBirth(rs.getString("NoiSinh"));
                model.setDegree(rs.getString("TrinhDo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return model;
    }
}

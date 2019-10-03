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
import model.SubjectModel;

/**
 * Class for getting data from QLMonHoc table
 */
public class SubjectDB {

    private final ConnectDB connectDB = new ConnectDB();

    /**
     * Get all subject ID
     *
     * @return List of subject ID
     */
    public List<String> getIDs() {
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        List<String> ids = new ArrayList<>();
        try {
            CallableStatement call = conn.prepareCall("SELECT MaMH FROM QLMonHoc");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                ids.add(rs.getString("MaMH"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ids;
    }

    /**
     * Get subject by ID
     *
     * @param id subject ID
     * @return Subject
     */
    public SubjectModel getByID(String id) {
        SubjectModel subject = null;
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM QLMonHoc WHERE MaMH=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                subject = new SubjectModel();
                subject.setId(rs.getString("MaMH"));
                subject.setName(rs.getString("TenMH"));
                subject.setDuration(rs.getInt("ThoiLuong"));
                subject.setTheory(rs.getInt("LyThuyet"));
                subject.setPractice(rs.getInt("ThucHanh"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return subject;
    }

}

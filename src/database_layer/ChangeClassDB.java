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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ChangeClassModel;

/**
 * Class for getting data from QLChuyenLop table
 */
public class ChangeClassDB {

    private final ConnectDB connectDB = new ConnectDB();

    /**
     * Get all change class info
     *
     * @return List of ChangeClassModel
     */
    public List<ChangeClassModel> getAll() {
        List<ChangeClassModel> list = new ArrayList<>();
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return list;
        }

        try {
            CallableStatement call = conn.prepareCall("SELECT * FROM QLChuyenLop");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                ChangeClassModel model = new ChangeClassModel();
                model.setId(rs.getInt("ID"));
                model.setStudentID(rs.getString("MaSV"));
                model.setClassID(rs.getString("MaLop"));
                model.setInDate(rs.getDate("NgayDen"));
                model.setOutDate(rs.getDate("NgayDi"));
                list.add(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChangeClassDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    /**
     * Insert change class info into QLChuyenLop table
     *
     * @param model Change class info
     * @return rows inserted
     */
    public int insert(ChangeClassModel model) {
        int row = 0;
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        String sql = "INSERT INTO QLChuyenLop(MaSV, MaLop, NgayDen, NgayDi) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, model.getStudentID());
            ps.setString(2, model.getClassID());
            if (model.getInDate() != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String inDate = format.format(model.getInDate());
                ps.setString(3, inDate);
            } else {
                ps.setString(3, null);
            }
            if (model.getOutDate() != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String outDate = format.format(model.getOutDate());
                ps.setString(4, outDate);
            } else {
                ps.setString(4, null);
            }

            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChangeClassDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return row;
    }

    /**
     * Update change class info in QLChuyenLop table
     *
     * @param model Change class info
     * @return rows updated
     */
    public int update(ChangeClassModel model) {
        int row = 0;
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        String sql = "UPDATE QLChuyenLop SET MaSV=?, MaLop=?, NgayDen=?, NgayDi=? WHERE ID=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, model.getStudentID());
            ps.setString(2, model.getClassID());
            if (model.getInDate() != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String inDate = format.format(model.getInDate());
                ps.setString(3, inDate);
            } else {
                ps.setString(3, null);
            }
            if (model.getOutDate() != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String outDate = format.format(model.getOutDate());
                ps.setString(4, outDate);
            } else {
                ps.setString(4, null);
            }
            ps.setInt(5, model.getId());

            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChangeClassDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return row;
    }

    /**
     * Delete change class info
     *
     * @param id change class ID
     * @return rows deleted
     */
    public int delete(int id) {
        int row = 0;
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        String sql = "DELETE FROM QLChuyenLop WHERE ID=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChangeClassDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return row;
    }
}

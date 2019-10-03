/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_layer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ConductModel;

/**
 * Class for getting data from QLHanhKiem table
 */
public class ConductDB {

    private final ConnectDB connectDB = new ConnectDB();

    /**
     * Get conduct info by student ID
     *
     * @param studentID student ID
     * @return Conduct info
     */
    public ConductModel getByStudentID(String studentID) {
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        ConductModel conduct = null;

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM QLHanhKiem WHERE MaSV=?");
            ps.setString(1, studentID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                conduct = new ConductModel();
                conduct.setSem1(rs.getString("Ky1"));
                conduct.setSem2(rs.getString("Ky2"));
                conduct.setSem3(rs.getString("Ky3"));
                conduct.setSem4(rs.getString("Ky4"));
                conduct.setSem5(rs.getString("Ky5"));
                conduct.setSem6(rs.getString("Ky6"));
                conduct.setSem7(rs.getString("Ky7"));
                conduct.setSem8(rs.getString("Ky8"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConductDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conduct;
    }
}

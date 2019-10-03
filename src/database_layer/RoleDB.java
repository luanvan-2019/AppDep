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
import model.RoleModel;

/**
 * Class for getting data from QLPhanQuyen table
 */
public class RoleDB {

    private final ConnectDB connectDB = new ConnectDB();

    /**
     * Get login info according to user name and password
     *
     * @param userName user name
     * @param password password
     * @return QLPhanQuyen info
     */
    public RoleModel getLoginInfo(String userName, String password) {
        RoleModel info = null;
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM QLPhanQuyen WHERE TaiKhoan=? AND MatKhau=?");
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                info = new RoleModel();
                info.setName(rs.getString("HoTen"));
                info.setRole(rs.getInt("Quyen"));
                info.setAccount(rs.getString("TaiKhoan"));
                info.setPassword(rs.getString("MatKhau"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return info;
    }

    /**
     * Update password
     *
     * @param account account
     * @param oldPassword old password
     * @param newPassword new password
     * @return row effected
     */
    public int updatePassword(String account, String oldPassword, String newPassword) {
        int row = 0;
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE QLPhanQuyen SET MatKhau=? WHERE TaiKhoan=? AND MatKhau=?");
            ps.setString(1, newPassword);
            ps.setString(2, account);
            ps.setString(3, oldPassword);
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
}

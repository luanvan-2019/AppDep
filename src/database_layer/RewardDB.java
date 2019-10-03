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
import model.RewardModel;

/**
 * Class for getting data from QLThuongPhat table
 */
public class RewardDB {

    private final ConnectDB connectDB = new ConnectDB();

    /**
     * Get all rewards info
     *
     * @return List of rewards info
     */
    public List<RewardModel> getAll() {
        List<RewardModel> rewards = new ArrayList<>();
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return rewards;
        }

        try {
            CallableStatement call = conn.prepareCall("SELECT * FROM QLThuongPhat");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                RewardModel reward = new RewardModel();
                reward.setId(rs.getInt("MaKyLuat"));
                reward.setStudentID(rs.getString("MaSV"));
                reward.setRewardType(rs.getInt("KhenThuong"));
                reward.setContent(rs.getString("NoiDung"));
                reward.setReason(rs.getString("LyDo"));
                rewards.add(reward);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RewardDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rewards;
    }

    /**
     * Insert data into QLThuongPhat table
     *
     * @param reward reward info
     * @return row inserted
     */
    public int insert(RewardModel reward) {
        int row = 0;
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        String sql = "INSERT INTO QLThuongPhat(MaSV, KhenThuong, NoiDung, LyDo) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, reward.getStudentID());
            ps.setInt(2, reward.getRewardType());
            ps.setString(3, reward.getContent());
            ps.setString(4, reward.getReason());
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RewardDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return row;
    }

    /**
     * Update data in QLThuongPhat table
     *
     * @param reward reward info
     * @return row updated
     */
    public int update(RewardModel reward) {
        int row = 0;
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        String sql = "UPDATE QLThuongPhat SET MaSV=?, KhenThuong=?, NoiDung=?, LyDo=? WHERE MaKyLuat=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, reward.getStudentID());
            ps.setInt(2, reward.getRewardType());
            ps.setString(3, reward.getContent());
            ps.setString(4, reward.getReason());
            ps.setInt(5, reward.getId());
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RewardDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return row;
    }

    /**
     * Delete data from QLThuongPhat table
     *
     * @param id reward ID
     * @return row deleted
     */
    public int delete(int id) {
        int row = 0;
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        String sql = "DELETE FROM QLThuongPhat WHERE MaKyLuat=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RewardDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return row;
    }
}

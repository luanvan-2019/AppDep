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
import model.ScoreModel;

/**
 * Class for getting data from QLDiem table
 */
public class ScoreDB {

    private final ConnectDB connectDB = new ConnectDB();

    /**
     * Get all scores info
     *
     * @return List of scores info
     */
    public List<ScoreModel> getAll() {
        List<ScoreModel> scores = new ArrayList<>();

        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            CallableStatement call = conn.prepareCall("SELECT * FROM QLDiem");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                ScoreModel model = new ScoreModel();
                model.setStudentID(rs.getString("MaSV"));
                model.setSubjectID(rs.getString("MaMH"));
                model.setProcessScore(rs.getFloat("DiemQuaTrinh"));
                model.setFinalScore(rs.getFloat("DiemKTHP"));
                model.setTotalScore(rs.getFloat("DiemTongKet"));
                scores.add(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return scores;
    }

    /**
     * Get scores by student ID
     *
     * @param id student ID
     * @return List of scores
     */
    public List<ScoreModel> getByStudentID(String id) {
        List<ScoreModel> scores = new ArrayList<>();

        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM QLDiem WHERE MaSV=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ScoreModel model = new ScoreModel();
                model.setStudentID(rs.getString("MaSV"));
                model.setSubjectID(rs.getString("MaMH"));
                model.setProcessScore(rs.getFloat("DiemQuaTrinh"));
                model.setFinalScore(rs.getFloat("DiemKTHP"));
                model.setTotalScore(rs.getFloat("DiemTongKet"));
                scores.add(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return scores;
    }

    /**
     * Get scores by subject ID
     *
     * @param id subject ID
     * @return List of scores
     */
    public List<ScoreModel> getBySubjectID(String id) {
        List<ScoreModel> scores = new ArrayList<>();

        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM QLDiem WHERE MaMH=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ScoreModel model = new ScoreModel();
                model.setStudentID(rs.getString("MaSV"));
                model.setSubjectID(rs.getString("MaMH"));
                model.setProcessScore(rs.getFloat("DiemQuaTrinh"));
                model.setFinalScore(rs.getFloat("DiemKTHP"));
                model.setTotalScore(rs.getFloat("DiemTongKet"));
                scores.add(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return scores;
    }

    /**
     * Get score info by student ID and subject ID
     *
     * @param studentID student ID
     * @param subjectID subject ID
     * @return List of scores info
     */
    public ScoreModel getByID(String studentID, String subjectID) {
        ScoreModel model = null;

        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM QLDiem WHERE MaSV=? and MaMH=?");
            ps.setString(1, studentID);
            ps.setString(2, subjectID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                model = new ScoreModel();
                model.setStudentID(rs.getString("MaSV"));
                model.setSubjectID(rs.getString("MaMH"));
                model.setProcessScore(rs.getFloat("DiemQuaTrinh"));
                model.setFinalScore(rs.getFloat("DiemKTHP"));
                model.setTotalScore(rs.getFloat("DiemTongKet"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return model;
    }

    /**
     * Insert a score info into QLDiem table
     *
     * @param score score info
     * @return rows inserted
     */
    public int insert(ScoreModel score) {
        int row = 0;
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        String sql = "INSERT INTO QLDiem VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, score.getStudentID());
            ps.setString(2, score.getSubjectID());
            ps.setFloat(3, score.getProcessScore());
            ps.setFloat(4, score.getFinalScore());
            ps.setFloat(5, score.getTotalScore());
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return row;
    }

    /**
     * Update score info
     *
     * @param score score info
     * @return rows updated
     */
    public int update(ScoreModel score) {
        int row = 0;
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        String sql = "UPDATE QLDiem SET DiemQuaTrinh=?, DiemKTHP=?, DiemTongKet=? WHERE MaSV=? AND MaMH=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setFloat(1, score.getProcessScore());
            ps.setFloat(2, score.getFinalScore());
            ps.setFloat(3, score.getTotalScore());
            ps.setString(4, score.getStudentID());
            ps.setString(5, score.getSubjectID());
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return row;
    }

    /**
     * Delete score info
     *
     * @param score score info
     * @return rows deleted
     */
    public int delete(ScoreModel score) {
        int row = 0;
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        String sql = "DELETE FROM QLDiem WHERE MaSV=? AND MaMH=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, score.getStudentID());
            ps.setString(2, score.getSubjectID());
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return row;
    }

}

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
import model.StudentModel;
import org.apache.commons.lang3.StringUtils;

/**
 * Class for getting data from QLSinhVien table
 */
public class StudentDB {

    private final ConnectDB connectDB = new ConnectDB();

    /**
     * Get student by id
     *
     * @param id the id
     * @return student info
     */
    public StudentModel getStudentByID(String id) {
        StudentModel student = null;
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM QLSinhVien WHERE MaSV=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student = new StudentModel();
                student.setId(rs.getString("MaSV"));
                student.setName(rs.getString("HoTenSV"));
                student.setClassID(rs.getString("MaLop"));
                student.setBirthday(rs.getDate("NgaySinh"));
                student.setGender(rs.getInt("GioiTinh"));
                student.setPhoneNumber(rs.getString("DienThoai"));
                student.setEmail(rs.getString("Email"));
                student.setAddress(rs.getString("DiaChi"));
                student.setPlaceOfBirth(rs.getString("NoiSinh"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return student;
    }

    /**
     * Get all students
     *
     * @return List of students
     */
    public List<StudentModel> getAll() {
        List<StudentModel> students = new ArrayList<>();
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            CallableStatement call = conn.prepareCall("SELECT * FROM QLSinhVien");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                StudentModel student = new StudentModel();
                student.setId(rs.getString("MaSV"));
                student.setName(rs.getString("HoTenSV"));
                student.setClassID(rs.getString("MaLop"));
                student.setBirthday(rs.getDate("NgaySinh"));
                student.setGender(rs.getInt("GioiTinh"));
                student.setPhoneNumber(rs.getString("DienThoai"));
                student.setEmail(rs.getString("Email"));
                student.setAddress(rs.getString("DiaChi"));
                student.setPlaceOfBirth(rs.getString("NoiSinh"));
                students.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return students;
    }

    /**
     * Get students in the class
     *
     * @param classID class ID
     * @return List of students
     */
    public List<StudentModel> getStudentsInClass(String classID) {
        List<StudentModel> students = new ArrayList<>();
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM QLSinhVien WHERE MaLop=?");
            ps.setString(1, classID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentModel student = new StudentModel();
                student.setId(rs.getString("MaSV"));
                student.setName(rs.getString("HoTenSV"));
                student.setClassID(rs.getString("MaLop"));
                student.setBirthday(rs.getDate("NgaySinh"));
                student.setGender(rs.getInt("GioiTinh"));
                student.setPhoneNumber(rs.getString("DienThoai"));
                student.setEmail(rs.getString("Email"));
                student.setAddress(rs.getString("DiaChi"));
                student.setPlaceOfBirth(rs.getString("NoiSinh"));
                students.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return students;
    }

    /**
     * Get students by key search
     *
     * @param key key for searching
     * @return List of students
     */
    public List<StudentModel> searchStudent(String key) {
        List<StudentModel> students = new ArrayList<>();
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM QLSinhVien WHERE MaSV=? OR HoTenSV like ?");
            ps.setString(1, key);
            String likeValue = "%" + key + "%";
            ps.setString(2, likeValue);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentModel student = new StudentModel();
                student.setId(rs.getString("MaSV"));
                student.setName(rs.getString("HoTenSV"));
                student.setClassID(rs.getString("MaLop"));
                student.setBirthday(rs.getDate("NgaySinh"));
                student.setGender(rs.getInt("GioiTinh"));
                student.setPhoneNumber(rs.getString("DienThoai"));
                student.setEmail(rs.getString("Email"));
                student.setAddress(rs.getString("DiaChi"));
                student.setPlaceOfBirth(rs.getString("NoiSinh"));
                students.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return students;
    }

    /**
     * Insert student into QLSinhVien table
     *
     * @param student student info
     * @return rows inserted
     */
    public int insert(StudentModel student) {
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        int row = 0;
        String sql = "INSERT INTO QLSinhVien VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, student.getId());
            ps.setString(2, student.getName());
            if (student.getBirthday() != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String birthday = format.format(student.getBirthday());
                ps.setString(3, birthday);
            } else {
                ps.setString(3, null);
            }
            ps.setInt(4, student.getGender());
            ps.setString(5, student.getPhoneNumber());
            ps.setString(6, student.getEmail());
            ps.setString(7, student.getAddress());
            ps.setString(8, student.getPlaceOfBirth());
            if (!StringUtils.isBlank(student.getClassID())) {
                ps.setString(9, student.getClassID());
            } else {
                ps.setString(9, null);
            }
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return row;
    }

    /**
     * Update student info
     *
     * @param student student info
     * @return rows updated
     */
    public int update(StudentModel student) {
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        int row = 0;
        String sql = "UPDATE QLSinhVien SET HoTenSV=?, NgaySinh=?, GioiTinh=?, DienThoai=?, Email=?, DiaChi=?, NoiSinh=?, MaLop=? WHERE MaSV=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, student.getName());
            if (student.getBirthday() != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String birthday = format.format(student.getBirthday());
                ps.setString(2, birthday);
            } else {
                ps.setString(2, null);
            }
            ps.setInt(3, student.getGender());
            ps.setString(4, student.getPhoneNumber());
            ps.setString(5, student.getEmail());
            ps.setString(6, student.getAddress());
            ps.setString(7, student.getPlaceOfBirth());
            if (!StringUtils.isBlank(student.getClassID())) {
                ps.setString(8, student.getClassID());
            } else {
                ps.setString(8, null);
            }
            ps.setString(9, student.getId());
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return row;
    }

    /**
     * Delete student
     *
     * @param studentID Student ID
     * @return rows deleted
     */
    public int delete(String studentID) {
        Connection conn = connectDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Connect database failed", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        int row = 0;
        String sql = "DELETE FROM QLSinhVien WHERE MaSV=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, studentID);
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return row;
    }

}

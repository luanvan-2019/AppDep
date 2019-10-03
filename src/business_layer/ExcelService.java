/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_layer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Service support export excel
 */
public class ExcelService {

    /**
     * Export students info into excel file
     *
     * @param file Excel file
     * @param students List of students
     * @return true if export successful, otherwise false
     */
    public boolean exportStudent(String file, List<List<Object>> students) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Student detail");
        // Create header
        createStudentHeader(sheet);

        // Add data to workbook
        int rowNum = 1;
        for (int i = 0; i < students.size(); i++) {
            Row row = sheet.createRow(rowNum);
            int cellNum = 0;
            for (Object value : students.get(i)) {
                if (value == null) {
                    row.createCell(cellNum).setCellValue("");
                } else if (value instanceof String) {
                    row.createCell(cellNum).setCellValue((String) value);
                } else if (value instanceof Integer) {
                    row.createCell(cellNum).setCellValue((int) value);
                } else if (value instanceof Date) {
                    row.createCell(cellNum).setCellValue("" + (Date) value);
                }
                cellNum++;
            }
            rowNum++;
        }

        // Write data to excel file
        try (FileOutputStream out = new FileOutputStream(new File(file))) {
            workbook.write(out);
        } catch (IOException ex) {
            Logger.getLogger(ExcelService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                workbook.close();
            } catch (IOException ex) {
                Logger.getLogger(ExcelService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    /**
     * Export score info into excel file
     *
     * @param file Excel file
     * @param className Name of class
     * @param StudentID Student ID
     * @param scores List of scores
     * @param studentName Name of Student
     * @return true if export successful, otherwise false
     */
    public boolean exportScore(String file, String className, String StudentID,
            String studentName, List<List<Object>> scores) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Score detail");
        // Create header
        createScoreHeader(sheet, className, StudentID, studentName);

        // Add data to workbook
        int rowNum = 5;
        for (int i = 0; i < scores.size(); i++) {
            Row row = sheet.createRow(rowNum);
            int cellNum = 0;
            for (Object value : scores.get(i)) {
                if (value == null) {
                    row.createCell(cellNum).setCellValue("");
                } else if (value instanceof String) {
                    row.createCell(cellNum).setCellValue((String) value);
                } else if (value instanceof Float) {
                    row.createCell(cellNum).setCellValue((float) value);
                }
                cellNum++;
            }
            rowNum++;
        }

        // Write data to excel file
        try (FileOutputStream out = new FileOutputStream(new File(file))) {
            workbook.write(out);
        } catch (IOException ex) {
            Logger.getLogger(ExcelService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                workbook.close();
            } catch (IOException ex) {
                Logger.getLogger(ExcelService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    /**
     * Export conduct info into excel file
     *
     * @param file Excel file
     * @param className Name of class
     * @param StudentID Student ID
     * @param conducts List of conducts
     * @param studentName Name of Student
     * @return true if export successful, otherwise false
     */
    public boolean exportConduct(String file, String className, String StudentID,
            String studentName, List<List<Object>> conducts) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Conduct detail");
        // Create header
        createConductHeader(sheet, className, StudentID, studentName);

        // Add data to workbook
        int rowNum = 5;
        for (int i = 0; i < conducts.size(); i++) {
            Row row = sheet.createRow(rowNum);
            int cellNum = 0;
            for (Object value : conducts.get(i)) {
                if (value == null) {
                    row.createCell(cellNum).setCellValue("");
                } else if (value instanceof String) {
                    row.createCell(cellNum).setCellValue((String) value);
                }
                cellNum++;
            }
            rowNum++;
        }

        // Write data to excel file
        try (FileOutputStream out = new FileOutputStream(new File(file))) {
            workbook.write(out);
        } catch (IOException ex) {
            Logger.getLogger(ExcelService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                workbook.close();
            } catch (IOException ex) {
                Logger.getLogger(ExcelService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    private void createStudentHeader(XSSFSheet sheet) {
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("MSSV");
        row.createCell(1).setCellValue("Họ Tên");
        row.createCell(2).setCellValue("Ngày Sinh");
        row.createCell(3).setCellValue("Giới Tính");
        row.createCell(4).setCellValue("Điện Thoại");
        row.createCell(5).setCellValue("Email");
        row.createCell(6).setCellValue("Địa Chỉ");
        row.createCell(7).setCellValue("Nơi Sinh");
        row.createCell(8).setCellValue("Lớp");
    }

    private void createScoreHeader(XSSFSheet sheet, String className,
            String StudentID, String studentName) {
        Row row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("Lớp:");
        row1.createCell(1).setCellValue(className);
        Row row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("Mã SV:");
        row2.createCell(1).setCellValue(StudentID);
        Row row3 = sheet.createRow(2);
        row3.createCell(0).setCellValue("Tên SV:");
        row3.createCell(1).setCellValue(studentName);

        // Create blank row
        sheet.createRow(3);

        Row row4 = sheet.createRow(4);
        row4.createCell(0).setCellValue("Môn Học");
        row4.createCell(1).setCellValue("Điểm Quá Trình");
        row4.createCell(2).setCellValue("Điểm KTHP");
        row4.createCell(3).setCellValue("Điểm Tổng Kết");
    }

    private void createConductHeader(XSSFSheet sheet, String className,
            String StudentID, String studentName) {
        Row row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("Lớp:");
        row1.createCell(1).setCellValue(className);
        Row row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("Mã SV:");
        row2.createCell(1).setCellValue(StudentID);
        Row row3 = sheet.createRow(2);
        row3.createCell(0).setCellValue("Tên SV:");
        row3.createCell(1).setCellValue(studentName);

        // Create blank row
        sheet.createRow(3);

        Row row4 = sheet.createRow(4);
        row4.createCell(0).setCellValue("Học Kỳ");
        row4.createCell(1).setCellValue("Hạnh Kiểm");
    }
}

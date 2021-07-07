package dz.djezzydevs.hrplaning.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import dz.djezzydevs.hrplaning.dto.Event;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class UserExcelExporter {
    private XSSFWorkbook workbook;

    private XSSFSheet sheet;

    private List<Event> listUsers;

    public UserExcelExporter(List<Event> listUsers) {
        this.listUsers = listUsers;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Planing");

        Row row = sheet.createRow(0);

//        sheet.setColumnWidth(0, 25 * 256);
//        sheet.setColumnWidth(1, 25 * 256);
//        sheet.setColumnWidth(2, 25 * 256);

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);


        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);

        createCell(row, 0, "Date planing", style);
        createCell(row, 1, "   Nom & Prenom   ", style);
     //   createCell(row, 2, "Session ", style);
      //  createCell(row, 3, "Roles", style);
     //   createCell(row, 4, "Enabled", style);

    }




    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }


    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Event user : listUsers) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, user.getDate(), style);
            createCell(row, columnCount++, user.getTitle(), style);
         //   createCell(row, columnCount++, user.getId(), style);


        }
    }


    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }


    public DataSource exportToBinary() throws IOException {
        writeHeaderLine();
        writeDataLines();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos); // write excel data to a byte array
        bos.close();
//https://stackoverflow.com/questions/16851199/sending-an-e-mail-through-java-application-with-excel-file-attached-not-workin// Now use your ByteArrayDataSource as


        DataSource fds = new ByteArrayDataSource(bos.toByteArray(), "application/vnd.ms-excel");

        return fds;

    }
}
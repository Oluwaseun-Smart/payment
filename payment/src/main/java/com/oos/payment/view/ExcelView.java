package com.oos.payment.view;

import com.oos.payment.entities.Payment;
import com.oos.payment.entities.User;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelView extends AbstractXlsView{

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"cec-payment-file.xls\"");

        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) model.get("users");

        // create excel xls sheet
        Sheet sheet = workbook.createSheet("Payments Detail");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);


        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("SURNAME");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("OTHER NAMES");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("MATRIC NUMBER");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("SCHOOL");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("DEPARTMENT");
        header.getCell(4).setCellStyle(style);
        header.createCell(5).setCellValue("LEVEL");
        header.getCell(5).setCellStyle(style);
        header.createCell(6).setCellValue("SESSION");
        header.getCell(6).setCellStyle(style);
        header.createCell(7).setCellValue("EMAIL");
        header.getCell(7).setCellStyle(style);
        header.createCell(8).setCellValue("PHONE");
        header.getCell(8).setCellStyle(style);
        header.createCell(9).setCellValue("PAYMENT");
        header.getCell(9).setCellStyle(style);
        header.createCell(10).setCellValue("TRANSACTION ID");
        header.getCell(10).setCellStyle(style);
        header.createCell(11).setCellValue("STATUS");
        header.getCell(11).setCellStyle(style);
        header.createCell(12).setCellValue("DATE");
        header.getCell(12).setCellStyle(style);
        header.createCell(13).setCellValue("AMOUNT");
        header.getCell(13).setCellStyle(style);
        header.createCell(14).setCellValue("TRANSACTION FEE");
        header.getCell(14).setCellStyle(style);
        header.createCell(15).setCellValue("TOTAL");
        header.getCell(15).setCellStyle(style);


        int rowCount = 1;

        for(User user : users){
            Row userRow =  sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(user.getSurname());
            userRow.createCell(1).setCellValue(user.getOthername());
            userRow.createCell(2).setCellValue(user.getMatric());
            userRow.createCell(3).setCellValue(user.getSchool());
            userRow.createCell(4).setCellValue(user.getDepartment());
            userRow.createCell(5).setCellValue(user.getLevel());
            userRow.createCell(6).setCellValue(user.getSes());
            userRow.createCell(7).setCellValue(user.getEmail());
            userRow.createCell(8).setCellValue(user.getPhone());
            userRow.createCell(9).setCellValue(user.getPayments().toString());
            userRow.createCell(10).setCellValue(user.getRef());
            userRow.createCell(11).setCellValue(user.getStatus());
            userRow.createCell(12).setCellValue(user.getDate());
            userRow.createCell(13).setCellValue(user.getAmount());
            userRow.createCell(14).setCellValue(user.getFee());
            userRow.createCell(15).setCellValue(user.getTotal());

        }

    }

}
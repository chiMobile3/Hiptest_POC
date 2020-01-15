//package com.iMobile3.framework.utilities;
//
//import java.io.*;
//import java.util.*;
//import org.apache.commons.collections4.ListUtils;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.*;
//
//
//
//
////https://poi.apache.org/components/spreadsheet/quick-guide.html
//public class ExcelManager {
//
//    public static void writeExcel(String[] set) throws Exception {
//        wipeFirst50Cells();
//        set = RemoveNullsFromStringList(set);
//        String excelFilePath = "Resources//AutomationData.xlsx";
//        try (InputStream inp = new FileInputStream(excelFilePath)) {
//
//            Workbook wb = WorkbookFactory.create(inp);
//            Sheet sheet = wb.getSheet("temp");
//            for (Row row : sheet) {
//                for (Cell cell : row) {
//                    for (int i = 0; i< set.length; i++)
//                    {
//                        sheet.createRow((short)i).createCell((short)0).setCellValue(set[i]);
//                    }
//                }
//            }
//            // Write the output to a file
//            try (OutputStream fileOut = new FileOutputStream(excelFilePath)) {
//                wb.write(fileOut);
//            }
//        }
//    }
//
//    public static void wipeFirst50Cells() throws Exception {
//        String excelFilePath = "Resources//AutomationData.xlsx";
//        try (InputStream inp = new FileInputStream(excelFilePath)) {
//            Workbook wb = WorkbookFactory.create(inp);
//            Sheet sheet = wb.getSheet("temp");
//            for (Row row : sheet) {
//                for (Cell cell : row) {
//                    for (int i = 0; i< 50; i++)
//                    {
//                        sheet.createRow((short)i).createCell((short)0).setCellValue("xxxx");
//                    }
//                }
//            }
//            // Write the output to a file
//            try (OutputStream fileOut = new FileOutputStream(excelFilePath)) {
//                wb.write(fileOut);
//            }
//        }
//    }
//
//    public static boolean compareTempFileSet(String [] inputSet, int expectedSize) throws Exception {
//        boolean match = false;
//        String excelFilePath = "Resources//AutomationData.xlsx";
//        FileInputStream inp = new FileInputStream(excelFilePath);
//        //this 51 number corresponds to the number of rows allocated in the temp sheet
//        String [] setFromExcelTempSheet = new String[51];
//        Workbook wb = new XSSFWorkbook(inp);
//        Sheet sheet = wb.getSheet("temp");
//        Iterator< Row > rowItr = sheet.iterator();
//        String s = "";
//        int rowNum = 0;
//        int i = 0;
//        boolean emptyCell = false;
//        while (!emptyCell) {
//            Row row = rowItr.next();
//            Iterator < Cell > cellItr = row.iterator();
//            System.out.print(rowNum + ". ");
//            while (cellItr.hasNext()){
//                Cell cell = cellItr.next();
//                if (cell.getCellType() == CellType.STRING || cell.getCellType() == CellType.NUMERIC) {
//                    s = cell.getStringCellValue();
//                    if(s.equals("xxxx")){
//                        emptyCell = true;
//                        break;
//                    }
//                    setFromExcelTempSheet[i] = s;
//                    i++;
//                } else {
//                    emptyCell = true;
//                    //break;
//                }
//            }
//            rowNum++;
//        }
//        setFromExcelTempSheet = RemoveNullsFromStringList(setFromExcelTempSheet);
//
//        //if this size at this point doesnt match we can make match false... otherwise compare the entire set
//        //convert string list to arraylist so we can utilize the
//        List<String> arrayInputSet = Arrays.asList( inputSet );
//        List<String> arraySetFromExcelTempSheet = Arrays.asList( setFromExcelTempSheet );
//
//        match = ListUtils.isEqualList(arrayInputSet, arraySetFromExcelTempSheet);
//
//        //close
//        wb.close();
//        inp.close();
//        //wipe the temp file
//        wipeFirst50Cells();
//        return match;
//    }
//
//    public static String[] RemoveNullsFromStringList(String[] set) throws Exception
//    {
//        String[] theArray = set;
//        theArray = Arrays.stream(theArray)
//                .filter(s -> (s != null && s.length() > 0))
//                .toArray(String[]::new);
//        return theArray;
//    }
//
//    public static String GetFullUserName(String userName) throws IOException {
//        String s = "";
//        String excelFilePath = "Resources//AutomationData.xlsx";
//        FileInputStream inp = new FileInputStream(excelFilePath);
//        Workbook wb = new XSSFWorkbook(inp);
//        Sheet sheet = wb.getSheet("Main");
//        // Decide which rows to process
//        int rowStart = 1;
//        int rowEnd = Math.max(501, sheet.getLastRowNum());
//        for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
//            Row r = sheet.getRow(rowNum);
//            if (r == null) {
//                // This whole row is empty
//                // Handle it as needed
//                continue;
//            }
//            int lastColumn = 1;
//            for (int cn = 0; cn < lastColumn; cn++) {
//                Cell c = r.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//                if (c == null) {
//                    // The spreadsheet is empty in this cell
//                } else {
//                    String cString = c.toString();
//                    if (cString.equalsIgnoreCase(userName))
//                    {
//                        //go to the next column and set that cell as s
//                        s=r.getCell(1).toString();
//                    }
//                }
//            }
//        }
//        return s;
//    }
//
//    public static String GetPassword(String userName) throws IOException {
//        String s = "";
//        String excelFilePath = "Resources//AutomationData.xlsx";
//        FileInputStream inp = new FileInputStream(excelFilePath);
//        Workbook wb = new XSSFWorkbook(inp);
//        Sheet sheet = wb.getSheet("Main");
//        // Decide which rows to process
//        int rowStart = 1;
//        int rowEnd = Math.max(501, sheet.getLastRowNum());
//        for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
//            Row r = sheet.getRow(rowNum);
//            if (r == null) {
//                // This whole row is empty
//                // Handle it as needed
//                continue;
//            }
//            int lastColumn = 1;
//            for (int cn = 0; cn < lastColumn; cn++) {
//                Cell c = r.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//                if (c == null) {
//                    // The spreadsheet is empty in this cell
//                } else {
//                    String cString = c.toString();
//                    if (cString.equalsIgnoreCase(userName))
//                    {
//                        //go to the next column and set that cell as s
//                        s=r.getCell(2).toString();
//                    }
//                }
//            }
//        }
//        return s;
//    }
//    //returns a String [] set with both the email and password. The input string is the username
//    public static String[] GetAccount(String userName) throws IOException {
//        String [] s = new String[1];
//        String excelFilePath = "Resources//AutomationData.xlsx";
//        FileInputStream inp = new FileInputStream(excelFilePath);
//        Workbook wb = new XSSFWorkbook(inp);
//        Sheet sheet = wb.getSheet("Sheet1");
//        // Decide which rows to process
//        int rowStart = 1;
//        int rowEnd = Math.max(501, sheet.getLastRowNum());
//        for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
//            Row r = sheet.getRow(rowNum);
//            if (r == null) {
//                // This whole row is empty
//                // Handle it as needed
//                continue;
//            }
//            int lastColumn = 1;
//            for (int cn = 0; cn < lastColumn; cn++) {
//                Cell c = r.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//                if (c == null) {
//                    // The spreadsheet is empty in this cell
//                } else {
//                    String cString = c.toString();
//                    if (cString.equals(userName))
//                    {
//                        //go to the next column and set that cell as s
//                        s[1] = r.getCell(1).toString();
//                        s[2] = r.getCell(1).toString();
//                    }
//                }
//            }
//        }
//        return s;
//    }
//}
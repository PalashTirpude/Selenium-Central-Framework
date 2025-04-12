package com.central.framework.excelutils;




import com.central.framework.excelutils.annotation.ExcelColumn;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

public class ExcelUtil {

    // Write list of objects to Excel
    public static <T> void writeToExcel(String filePath, String sheetName,int headerRowIndex, List<T> dataList, Class<T> clazz) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);

        Field[] fields = clazz.getDeclaredFields();

        // Create Header Row
        Row header = sheet.createRow(headerRowIndex);
        int colIdx = 0;
        for (Field field : fields) {
            ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
            if (excelColumn != null) {
                Cell cell = header.createCell(colIdx++);
                cell.setCellValue(excelColumn.name());
            }
        }

        // Fill Data
        int rowIdx = 1;
        for (T obj : dataList) {
            Row row = sheet.createRow(rowIdx++);
            colIdx = 0;
            for (Field field : fields) {
                ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
                if (excelColumn != null) {
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    Cell cell = row.createCell(colIdx++);
                    if (value != null) {
                        if (value instanceof Number) {
                            cell.setCellValue(((Number) value).doubleValue());
                        } else if (value instanceof Boolean) {
                            cell.setCellValue((Boolean) value);
                        } else if (value instanceof Date) {
                            CellStyle dateStyle = workbook.createCellStyle();
                            CreationHelper createHelper = workbook.getCreationHelper();
                            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
                            cell.setCellStyle(dateStyle);
                            cell.setCellValue((Date) value);
                        } else {
                            cell.setCellValue(value.toString());
                        }
                    }
                }
            }
        }

        try (FileOutputStream out = new FileOutputStream(filePath)) {
            workbook.write(out);
        }
        workbook.close();
    }

    // Read Excel into list of objects
    public static <T> List<T> readFromExcel(String filePath, String sheetName,int headerRowIndex, Class<T> clazz) throws Exception {
        List<T> resultList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) throw new IllegalArgumentException("Sheet not found");

            Row headerRow = sheet.getRow(headerRowIndex);
            Map<String, Integer> colIndexMap = new HashMap<>();

            for (Cell cell : headerRow) {
                colIndexMap.put(cell.getStringCellValue(), cell.getColumnIndex());
            }

            Field[] fields = clazz.getDeclaredFields();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                T obj = clazz.getDeclaredConstructor().newInstance();

                for (Field field : fields) {
                    ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
                    if (excelColumn != null) {
                        field.setAccessible(true);
                        int colIndex = colIndexMap.get(excelColumn.name());
                        Cell cell = row.getCell(colIndex);

                        if (cell != null) {
                            Class<?> fieldType = field.getType();

                            switch (cell.getCellType()) {
                                case STRING -> {
                                    if (fieldType == String.class) {
                                        field.set(obj, cell.getStringCellValue());
                                    } else if (fieldType == int.class || fieldType == Integer.class) {
                                        field.set(obj, Integer.parseInt(cell.getStringCellValue()));
                                    } else if (fieldType == double.class || fieldType == Double.class) {
                                        field.set(obj, Double.parseDouble(cell.getStringCellValue()));
                                    } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                                        field.set(obj, Boolean.parseBoolean(cell.getStringCellValue()));
                                    }
                                }
                                case NUMERIC -> {
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        if (fieldType == Date.class) {
                                            field.set(obj, cell.getDateCellValue());
                                        }
                                    } else {
                                        if (fieldType == int.class || fieldType == Integer.class) {
                                            field.set(obj, (int) cell.getNumericCellValue());
                                        } else if (fieldType == double.class || fieldType == Double.class) {
                                            field.set(obj, cell.getNumericCellValue());
                                        } else if (fieldType == String.class) {
                                            field.set(obj, String.valueOf(cell.getNumericCellValue()));
                                        }
                                    }
                                }
                                case BOOLEAN -> {
                                    if (fieldType == boolean.class || fieldType == Boolean.class) {
                                        field.set(obj, cell.getBooleanCellValue());
                                    }
                                }
                                case BLANK -> field.set(obj, null);
                            }
                        }
                    }
                }
                resultList.add(obj);
            }
        }

        return resultList;
    }
}

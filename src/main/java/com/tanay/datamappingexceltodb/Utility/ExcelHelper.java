package com.tanay.datamappingexceltodb.Utility;
import com.tanay.datamappingexceltodb.Entity.EmployeeEntity;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        assert contentType != null;
        return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

    }


    //convert excel to list of products

    public static List<EmployeeEntity> convertExcelToEmployee(InputStream is) {
        List<EmployeeEntity> list = new ArrayList<>();

        try {


            Workbook wb = new HSSFWorkbook(is);

            Sheet sheet = wb.createSheet("data");

            int rowNumber = 0;

            for (Row row : sheet) {
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                EmployeeEntity emp = new EmployeeEntity();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                        case 0:
                            emp.setId((long) cell.getNumericCellValue());
                            break;
                        case 1:
                            emp.setFirstName(cell.getStringCellValue());
                            break;
                        case 2:
                            emp.setLastName(cell.getStringCellValue());
                            break;
                        case 3:
                            emp.setEmail(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }

                list.add(emp);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }


}

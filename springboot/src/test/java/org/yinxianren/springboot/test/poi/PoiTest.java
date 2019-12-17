package org.yinxianren.springboot.test.poi;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PoiTest {

    @Test
    public void ReadExcel() throws IOException {
        File xlsFile = new File("C:\\Users\\yxr\\Desktop\\参数管理.xlsx");
        // 工作表
        Workbook workbook = WorkbookFactory.create(xlsFile);
        // 表个数。
        int numberOfSheets = workbook.getNumberOfSheets();
        // 遍历表。
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            // 行数。
            int rowNumbers = sheet.getLastRowNum() + 1;
            // Excel第一行。
            Row temp = sheet.getRow(0);
            if (temp == null) {
                continue;
            }
            int cells = temp.getPhysicalNumberOfCells();
            // 读数据。
            for (int row = 0; row < rowNumbers; row++) {
                Row r = sheet.getRow(row);
                for (int col = 0; col < cells; col++) {
                    if( null == r.getCell(col) )  continue;
                    System.out.print(r.getCell(col).toString()+" ");
                }
                // 换行。
                System.out.println();
            }
        }
    }

}

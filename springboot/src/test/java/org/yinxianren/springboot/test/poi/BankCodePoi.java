package org.yinxianren.springboot.test.poi;

import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yinxianren.springboot.service.BankCodeDbService;
import org.yinxianren.springboot.table.BankCodeTable;
import org.yinxianren.springboot.tools.Println;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankCodePoi implements Println {

    @Resource
    private BankCodeDbService bankCodeDbService;

    @Test
    public void test() throws IOException {
        File xlsFile = new File("C:\\Users\\yxr\\Desktop\\参数管理.xlsx");
        // 工作表
        Workbook workbook = WorkbookFactory.create(xlsFile);
        // 表个数。
        int numberOfSheets = workbook.getNumberOfSheets();
        //获取第三个页面
        Sheet sheet = workbook.getSheetAt(2);
        // 行数。
        int rowNumbers = sheet.getLastRowNum();
        // Excel第一行。
//        Row temp = sheet.getRow(0);
        Set<BankCodeTable> set = new HashSet<>();
        for(int i = 0; i< rowNumbers ; i++ ){
            Row row = sheet.getRow(i);
            int cells = row.getPhysicalNumberOfCells();
            BankCodeTable bct = new BankCodeTable();
            for(int j = 0 ; j < cells ; j++ ){
                Cell cell = row.getCell(j);
                if(null == cell) continue;
                if( j == 0 ) bct.setBankShortName(cell.toString()).setBankFullName(cell.toString());
                else if ( j == 1 ) bct.setBankCode( cell.toString() );
                else if ( j == 4 && null != cell )  bct.setRemark( cell.toString() );
            }
            bct.setCreateTime(new Date()).setUpdateTime(new Date());
//            println(bct.toString());
            set.add(bct);
        }
        bankCodeDbService.saveBatch(set);
    }

}

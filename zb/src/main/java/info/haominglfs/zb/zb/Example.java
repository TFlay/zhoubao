package info.haominglfs.zb.zb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;

public class Example {

    public static void main(String[] args) {

        System.out.println("请输入任务:");
        Scanner sc = new Scanner(System.in);
        String job = sc.nextLine();
        DateTime dt = new DateTime();
        int day = dt.getDayOfMonth();
        System.out.println(day);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        int rowNum = 0;
        int colNum = 0;
        try {
            fis = new FileInputStream(new File(PropertiesHelper.getInstance().get("zb-file")));
            Workbook wb = new HSSFWorkbook(fis);
            Sheet sheet0 = wb.getSheetAt(1);
            for (int j = 0; j < sheet0.getLastRowNum(); j++) {
                HSSFRow row = (HSSFRow) sheet0.getRow(j);
                if (null != row) {
                    for (int k = 0; k < row.getLastCellNum(); k++) {
                        HSSFCell cell = row.getCell(k);
                        if (null != cell) {
                            switch (cell.getCellType()) {
                            case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                                break;
                            case HSSFCell.CELL_TYPE_STRING: // 字符串
                                if ((day + "日").equals(cell.getStringCellValue())) {
                                    System.err.println("这一会kkkkkk");
                                    System.out.println("行号:" + j + "====列号:" + k);
                                    rowNum = j;
                                    colNum = k;
                                }
                                break;
                            case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                                break;
                            case HSSFCell.CELL_TYPE_FORMULA: // 公式
                                break;
                            case HSSFCell.CELL_TYPE_BLANK: // 空值
                                break;
                            case HSSFCell.CELL_TYPE_ERROR: // 故障
                                break;
                            default:
                                break;
                            }
                        }
                    }
                }
            }
            for(int i=4;i<14;i++){
                if(i==7){
                    continue;
                }
                sheet0.getRow(rowNum+1).getCell(colNum+i).setCellValue("平台标准版");
                sheet0.getRow(rowNum+4).getCell(colNum+i).setCellValue(job);
            }
            wb.setForceFormulaRecalculation(true);//使原公式生效
            fos = new FileOutputStream(new File(PropertiesHelper.getInstance().get("zb-file")));
            wb.write(fos);
            wb.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        

    }

}

package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    private String path;
    private FileInputStream fileInputStream;
    private Sheet sheet;
    private Workbook workbook;

    public ExcelUtil(String dosyaYolu, String sheetName) {

        this.path = dosyaYolu;

        try {
            fileInputStream = new FileInputStream(path);
            workbook = WorkbookFactory.create(fileInputStream);
            this.sheet = workbook.getSheet(sheetName);
            Assert.assertNotNull(String.valueOf(sheet), "Page 'sheetName' not found");
        } catch (FileNotFoundException e) {
            System.out.println("file path not found");
        } catch (IOException e) {
            e.getMessage();
        }

    }

    // 1-) Tabloda bulunan toplam sutun sayisi bulma
    public int getNumberOfColumns() {

        return sheet.getRow(0).getPhysicalNumberOfCells();
    }


    // 2-) Tablodaki Toplam satir sayisini alma (index ile degil direkt olarak istenilen Number)
    public int getNumberOfRows() {
        return sheet.getLastRowNum() + 1;
    }


    // 3) Tabloda kullanilan satir sayisini alma (index ile degil direkt olarak istenilen Number)
    public int getNumberUsedOfRows() {
        return sheet.getPhysicalNumberOfRows();
    }


    // 4) Istenen Satirdaki Datalari alma (index ile degil direkt olarak istenilen Number)
    public List<String> getElementsAtTheRow(int rowNumber) {
        List<String> allElements = new ArrayList<String>();
        for (int i = 0; i < getNumberOfColumns(); i++) {
            try {
                allElements.add(sheet.getRow(rowNumber - 1).getCell(i).getStringCellValue());
            } catch (NullPointerException e) {
                allElements.add(" ");
            }
        }
        return allElements;
    }


    // 5) Satir ve Sutun girerek datayi alma (index ile degil direkt olarak istenilen Number)
    public String getCellData(int rowNumber, int cellNumber) {
        String cell = "";
        try {
            cell = sheet.getRow(rowNumber - 1).getCell(cellNumber - 1).getStringCellValue();
        } catch (NullPointerException e) {
            cell = " ";
        }
        return cell;
    }



    // 6) Datalari Multi Dimensional Array Seklinde Alma (index ile degil direkt olarak istenilen Number)
    public String[][] getAllDataAsArray() {
        String allData[][] = new String[getNumberOfRows()][getNumberOfColumns()];
        for (int i = 0; i < getNumberOfRows(); i++) {
            for (int j = 0; j < getNumberOfColumns(); j++) {
                allData[i][j] = getCellData(i + 1, j + 1);
            }
        }
        return allData;
    }


    // 7) Datalari Map Seklinde Almak (index ile degil direkt olarak istenilen Number)
    public Map<String, String> getDataAsMap() {
        Map<String, String> allData = new LinkedHashMap<>();
        String key = "";
        String value = "";
        for (int i = 0; i < getNumberOfRows(); i++) {
            key = sheet.getRow(i).toString();
            for (int j = 0; j < getNumberOfColumns(); j++) {
                value += getElementsAtTheRow(j).toString() + " ";
            }
            allData.put(key, value);
        }
        return allData;
    }


    // 7) Tabloya Data Girmek ve Guncellemek icin (index ile degil direkt olarak istenilen Number)
    public void setCellData(int row, int cell, String value) {
        if (sheet.getRow(row - 1).getCell(cell - 1) == null) {
            sheet.getRow(row - 1).createCell(cell - 1).setCellValue(value);
        } else {
            sheet.getRow(row - 1).getCell(cell - 1).setCellValue(value);
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.getMessage();
        }


    }
}

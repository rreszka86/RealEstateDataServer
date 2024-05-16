package pl.pollub.integracja_projekt.Utils.ExcelReader;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    private List<List<String>> arr;

    public ExcelReader(String filepath) {
        arr = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File(filepath));
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(1);
            for (Row row : sheet) {
                arr.add(new ArrayList<>());
                for (Cell cell : row) {
                    arr.get(row.getRowNum()).add(cell.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<List<String>> getArr() {
        return arr;
    }

    public void show(){
        arr.forEach(strings -> {
            strings.forEach(s -> {
                System.out.print(s + " ");
            });
            System.out.println();
        });
    }
}

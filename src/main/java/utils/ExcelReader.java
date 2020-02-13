package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import model.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    public static List<User> Reader(String path) {
        List<User> listOfUsers = new ArrayList<User>();
        try {
            FileInputStream excelFile = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            DataFormatter fmt = new DataFormatter();

            Iterator<Row> iterator = datatypeSheet.iterator();
            Row firstRow = iterator.next();
            //Cell firstCell = firstRow.getCell(0);
            //System.out.println(firstCell.getStringCellValue());
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                User user = new User();

                String prefixUsernameRandom = String.valueOf(ExcelReader.getRandomIntegerBetweenRange(1, 1000));

                user.setFirstName(currentRow.getCell(1).getStringCellValue());
                user.setLastName(currentRow.getCell(2).getStringCellValue());
                user.setUserName(prefixUsernameRandom + currentRow.getCell(3).getStringCellValue());
                user.setPassword(currentRow.getCell(4).getStringCellValue());
                user.setEmail(prefixUsernameRandom + currentRow.getCell(5).getStringCellValue());
                listOfUsers.add(user);
            }
            workbook.close();

            return listOfUsers;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfUsers;
    }


    public static int getRandomIntegerBetweenRange(int min, int max){
        int x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReadWriteExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import static org.apache.poi.ss.usermodel.CellType.BLANK;
import static org.apache.poi.ss.usermodel.CellType.STRING;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author cherepanov
 */
public class RWExcel {

    int startReadData = 0;
    private String patch_file;

    public void setPatchF(String patch_file) {
        this.patch_file = patch_file;
    }

    public void readAllfile() throws IOException {

        // Read XSL file
        FileInputStream inputStream = new FileInputStream(new File(patch_file));
        HSSFWorkbook wb = new HSSFWorkbook(inputStream);

        ArrayList<String> list_sheet = new ArrayList<String>();

        // РїСЂРѕРіР°РЅР°СЏРµРј СЃРїРёСЃРѕРє Р›РёСЃС‚РѕРІ РІ С„Р°Р№Р»Рµ 
        Iterator<Sheet> it_sheet = wb.iterator();
        int tmp = 0;
        while (it_sheet.hasNext()) {
            Sheet sheet = it_sheet.next();
            System.out.println(sheet.getSheetName());
            tmp++;
            list_sheet.add(sheet.getSheetName());
        }

        Iterator<String> iter_list_sheet = list_sheet.iterator();
        while (iter_list_sheet.hasNext()) {
            String name_sheet = iter_list_sheet.next();
            System.out.println(name_sheet);
            Sheet sheet = wb.getSheet(name_sheet);

            //Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> it = sheet.iterator(); // РёС‚РµСЂР°С‚РѕСЂ РЎС‚СЂРѕРє
            int len_row = 0;
            int max_len_row = 0;
            while (it.hasNext()) {
                Row row = it.next();
                len_row = row.getLastCellNum();
                if (len_row > max_len_row) {
                    max_len_row = len_row;
                }

                Iterator<Cell> cells = row.iterator(); // РёС‚РµСЂР°С‚РѕСЂ РЇС‡РµРµРє
                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    CellType cellType = cell.getCellType();

                    switch (cellType) {
                        case STRING:
                            System.out.print(cell.getStringCellValue());
                            break;
                        case BLANK:
                            System.out.print(cell.getColumnIndex());
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue());
                            break;
                        case FORMULA:
                            System.out.print(cell.getCellFormula());
                            break;
                        default:
                            System.out.print("|");
                    }

                    //System.out.print("[" + cell.getAddress()+ " " + cell.getCellType()+ "]");
                }

                System.out.println();

            }
            String unicId = getUIID();
            System.out.println("Max_len_in_string_row " + Integer.toString(max_len_row) + " Unic ID " + getUIID());
        }

    }

    String getUIID() {
        java.util.UUID uniqueKey = java.util.UUID.randomUUID();
        Date dateNow = new Date();
      //  SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyyMMddhhmmsss"); //С„РѕСЂРјРёСЂСѓРµРј РґР°С‚Сѓ РєР°Рє РЅР°Рј РІР·РґСѓРјР°РµС‚СЃСЏ

        //String uiid_str = uniqueKey.toString().replace("-", "") + formatForDateNow.format(dateNow).toString();
        String uiid_str = uniqueKey.toString().replace("-", "");
        return uiid_str;
    }

   

    public int getMaxcColumn(String name_sheet) throws FileNotFoundException, IOException {

        // Read XSL file
        FileInputStream inputStream = new FileInputStream(new File(patch_file));
        HSSFWorkbook wb = new HSSFWorkbook(inputStream);

        System.out.println(name_sheet);
        Sheet sheet = wb.getSheet(name_sheet);
        Iterator<Row> it = sheet.iterator(); // РёС‚РµСЂР°С‚РѕСЂ РЎС‚СЂРѕРє
        int max_len_row = 0;
        while (it.hasNext()) {
            Row row = it.next();
            int len_row = row.getLastCellNum();
            if (len_row > max_len_row) {
                max_len_row = len_row;
            }
        }

        return max_len_row;
    }
    
     public ArrayList<String> get_list_sheet() throws FileNotFoundException, IOException {
        // Read XSL file
        FileInputStream inputStream = new FileInputStream(new File(patch_file));
        HSSFWorkbook wb = new HSSFWorkbook(inputStream);

        ArrayList<String> list_sheet = new ArrayList<String>();

        // РїСЂРѕРіР°РЅР°СЏРµРј СЃРїРёСЃРѕРє Р›РёСЃС‚РѕРІ РІ С„Р°Р№Р»Рµ 
        Iterator<Sheet> it_sheet = wb.iterator();
        while (it_sheet.hasNext()) {
            Sheet sheet = it_sheet.next();
            list_sheet.add(sheet.getSheetName());
        }
        return list_sheet;
    }

    // --- Geting data from file Exel ----
    public ArrayList<String[]> getDataCell(String name_sheet, int lenmass) throws FileNotFoundException, IOException {
           String[] array_cell_len;
        ArrayList<String[]> array_cell = new ArrayList<>();

        FileInputStream inputStream = new FileInputStream(new File(patch_file));
        HSSFWorkbook wb = new HSSFWorkbook(inputStream);
        Sheet sheet = wb.getSheet(name_sheet);

        // РЅР°С‡Р°Р»СЊРЅС‹Рµ Р·РЅР°С‡РµРЅРёСЏ РЅР°РІРµСЂРЅРѕРµ РїСЂРѕСЃС‚Рѕ РґР»СЏ РёРЅРёС†РёР°Р»РёР·Р°С†РёРё
        int first_len = 0; // РїРѕР»СѓС‡РёР»Рё РїРµСЂРµРјРµРЅСѓСЋ СЃС‚СЂРѕРєРё СЃ РёРјРµРЅР°РјРё СЃС‚РѕР»Р±С†РѕРІ;
        int tmpFirstLenght = 0;
        int col_UUID = 0;
        int startm = 1;
        //array_cell_len = new String[lenmass + col_UUID]; // РџРѕС‡РµРјСѓ +1

        switch (name_sheet) { // Р’С‹СЃС‡РёС‚С‹РІР°РµРј СЃ РєР°РєРѕР№ СЃС‚СЂРѕРєРё Р·Р°РїРѕР»РЅСЏС‚СЊ С‚Р°Р±Р»РёС†Сѓ Рё 4 UUID - 3 РґРѕРї
            case "AI1":
            case "AO1":
            case "DI1": //case "DO1": // Р§С‚Рѕ С‚Рѕ Сѓ РЅР°СЃ СЃ СЌС‚РѕР№ РљРЅРёРіРѕР№ РІ С„Р°Р№Р»Рµ
            case "DO1": {
                //first_len = 3; // СЃ 4 СЃС‚СЂРѕРєРё С„Р°Р№Р»Р° Р­С‚Рѕ РЅР°РґРѕ Р°РЅР°Р»РёР·РёСЂРѕРІР°С‚СЊ Р° РЅРµ Р·Р°РґР°РІР°С‚СЊ 
                first_len = startReadData; // РїРѕР»СѓС‡РёР»Рё РїРµСЂРµРјРµРЅСѓСЋ СЃС‚СЂРѕРєРё СЃ РёРјРµРЅР°РјРё СЃС‚РѕР»Р±С†РѕРІ
                col_UUID = 4; // 4 UUID
                startm = 4; // СЌС‚Рѕ РјРѕСЏ РёРґРёРѕС‚РёСЏ С‚СѓС‚ РёР· Р·Р° С‚РѕРіРѕ С‡С‚Рѕ СЏ СЂРµС€РёР» С‚СѓС‚ РІРЅРѕСЃРёС‚СЊ UUID РІ Р±Р°Р·Сѓ
                array_cell_len = new String[lenmass + col_UUID]; // РџРѕС‡РµРјСѓ +1
            }
            break;
            default: { // РµСЃР»Рё РЅРµС‡РµРіРѕ РЅРµ РЅР°Р№РґРµРЅРѕ РёР· РѕРґС…РѕРґСЏС‰РµРіРѕ
                first_len = startReadData;
                startm = 1; // РґР°РЅРЅС‹Рµ СЃ РїРµСЂРІРѕРіРѕ С‚Р°Рє РєР°Рє РѕРґРёРЅ UUID 
                col_UUID = 1;
                array_cell_len = new String[lenmass + col_UUID]; // РџРѕС‡РµРјСѓ +1 РўР°Рє РєР°Рє РѕРґРЅРё РґР°РЅРЅС‹Рµ РїРѕРґ UUID
            }
        }

        Iterator<Row> it = sheet.iterator(); // РёС‚РµСЂР°С‚РѕСЂ РЎС‚СЂРѕРє
        int sum_sheet = 0;
        int len_row = 0;
        int max_len_row = 0;

        while (it.hasNext()) {
            while (tmpFirstLenght < first_len) { // Р° РІРѕС‚ РµСЃС‚СЊ Р»Рё С‚Р°Рј РґР°РЅРЅС‹Рµ
                it.next();
                ++tmpFirstLenght;
            }
            //++sum_sheet;
            if (it.hasNext()) { // РїСЂРѕРІРµСЂРєР° РµСЃС‚СЊ Р»Рё РІРѕРѕР±С‰Рµ РґР°РЅРЅС‹Рµ РїРѕСЃР»Рµ РїСЂРѕРїСѓСЃРєР° СЃС‚СЂРѕРє
                Row row = it.next();
                // System.out.println(row.getFirstCellNum() + " " + row.getLastCellNum()); //РІ СЃС‚СЂРѕРєСѓ С‡С‚Рѕ Р±С‹ РїРѕСЃРјРѕС‚СЂРµС‚СЊ С‡С‚Рѕ Р·Р° РЅР°С…
                int tmp = 0;
                //Р·Р°РЅРѕСЃРёРј РљРѕР» UUID
                int tmp_UUID = 1;
                do {
                    array_cell_len[tmp] = getUIID();
                    tmp_UUID++;
                    tmp++;
                } while (tmp_UUID <= col_UUID);

            //array_cell_len[tmp]= getUIID(); // С‚Р°Рє Р±С‹Р»Рѕ РґРѕ For
                // System.out.println(array_cell_len[tmp]);
                // tmp++;
                //System.out.println(row.getLastCellNum());
                Iterator<Cell> cells = row.cellIterator(); // РёС‚РµСЂР°С‚РѕСЂ РЇС‡РµРµРє РІРѕС‚ РЅРµ СЂР°Р±РѕС‚Р°РµС‚ РґРѕР»Р¶РЅС‹Рј РѕР±СЂР°Р·РѕРј РїСЂРѕРїСѓСЃРєР°РµС‚ СЏС‡РµР№РєРё
                int i_tmp = 0;

            //  while (cells.hasNext()) {
                //      Cell cell = cells.next();
                while (i_tmp < array_cell_len.length - startm) {
                    Cell cell = row.getCell(i_tmp);

                //System.out.println(cell.getAddress());
               /* CellAddress cellReference = new CellAddress("Q110");
                     if (cell.getAddress().equals(cellReference)){
                     System.out.println(cell.getAddress());
                     System.out.println(cell.getCellType());
                     }*/
                //System.out.println(cell.getAddress()); // Р”Р»СЏ РїСЂРѕРІРµСЂРєРё СЃРґРІРёРіР°
                /* System.out.println(cell.getAddress());
                     System.out.println(i_tmp);
                     System.out.println(row.getLastCellNum());*/
                    if (cell != null) {  // РѕР±С…РѕРґРёРј С‚Р°РєРёРј РґРµР±РёР»СЊРЅС‹Рј СЃРїРѕСЃРѕР±РѕРј
                        CellType cellType = cell.getCellType();
                        switch (cellType) {
                            case STRING: {
                                if (cell.getStringCellValue().contains("'")) {
                                    //System.out.print("Find ' ->  " + cell.getStringCellValue());
                                    array_cell_len[tmp] = cell.getStringCellValue().replaceAll("'", "");
                                }
                                array_cell_len[tmp] = cell.getStringCellValue();  // // СѓР±РёСЂР°СЋ С‡С‚Рѕ Р±С‹ РЅРµ Р±С‹Р»Рѕ С‚СЂСѓРґРЅРѕСЃС‚РµР№ СЃ Р·Р°РіСЂСѓР·РєРѕР№ РІ РїРѕСЃС‚РіСЂРµСЃ РїСЂРё СЌС‚РѕРј СѓС€Р»Рё РїСѓСЃС‚С‹Рµ СЃС‚СЂРѕРєРё
                            }
                            break;
                            case BLANK:
                                array_cell_len[tmp] = "NULL";
                                break;
                            case NUMERIC:
                                array_cell_len[tmp] = Double.toString(cell.getNumericCellValue()); // Double
                                break;
                            //case FORMULA : array_cell_len[tmp]=cell.getCellFormula(); // String
                            case FORMULA:
                                // System.out.println("Formula");
                                switch (cell.getCachedFormulaResultType()) {
                                    case NUMERIC:
                                        array_cell_len[tmp] = (Double.toString(cell.getNumericCellValue()));
                                        break;
                                    case STRING:
                                        array_cell_len[tmp] = cell.getRichStringCellValue().toString();
                                        array_cell_len[tmp] = array_cell_len[tmp].replaceAll("'", ""); // СѓР±РёСЂР°СЋ С‡С‚Рѕ Р±С‹ РЅРµ Р±С‹Р»Рѕ С‚СЂСѓРґРЅРѕСЃС‚РµР№ СЃ Р·Р°РіСЂСѓР·РєРѕР№ РІ РїРѕСЃС‚РіСЂРµСЃ РїСЂРё СЌС‚РѕРј СѓС€Р»Рё РїСѓСЃС‚С‹Рµ СЃС‚СЂРѕРєРё
                                        //System.out.println("Last evaluated as \"" + cell.getRichStringCellValue() + "\"");
                                        break;
                                }
                                break;
                            default:
                                array_cell_len[tmp] = "|";
                                break;
                        }
                    } else {
                        array_cell_len[tmp] = "NULL"; // Р’РѕС‚ С‚СѓС‚ С‡РµРіРѕ С‚Р°РєРѕРµ С‚Рѕ?
                    }

                    tmp++;
                    i_tmp++;
                }

                for (int i = 0; i < array_cell_len.length; i++) {
                    // System.out.print(array_cell_len[i] + " " );
                }
           //System.out.println();

            // РџР РѕРІРµСЂСЏРµРјСЃ СЃС‡РёС‚Р°Р»РёСЃСЊ РєР°РєРёРµ С‚Рѕ РґР°РЅРЅС‹Рµ РёР· СЏС‡РµРµРє СЃС‚СЂРѕРєРё (1 С‚Р°Рє РєР°Рє РїРµСЂРІС‹Р№ СЌР»РµРјРµРЅС‚ Р·Р°РЅСЏС‚ ID) 
                //Р–РµР»Р°С‚РµР»СЊРЅРѕ РїРµСЂРµРїРёСЃР°С‚СЊ
                int not_null_dat = 0;
                for (int i = 1; i < array_cell_len.length; i++) {
                    if (array_cell_len[i].isEmpty()) {
                        continue;
                    } else {
                        not_null_dat = 1;
                        break;
                    }
                }
                if (not_null_dat != 0) {
                    String[] tmp_array_cell_len = Arrays.copyOf(array_cell_len, array_cell_len.length);

                    // РџСЂРѕРІРµСЂСЏРµРј РїСѓСЃС‚РѕР№ Р»Рё РјР°СЃСЃРёРІ РєРѕС‚РѕСЂС‹Р№ РјС‹ Р·Р°РЅРѕСЃРёРј, С‚Р°Рє РєР°Рє Exel РґСѓРјР°РµС‚ С‡С‚Рѕ РµСЃС‚СЊ РґР°РЅРЅС‹Рµ
                    boolean empty = true;
                    //  if(tmp_array_cell_len.length != 0){    //РјР°СЃСЃРёРІ РјРѕР¶РµС‚ Р±С‹С‚СЊ РїСѓСЃС‚РѕР№
                    for (int i = startm; i < tmp_array_cell_len.length; i++) {
                        //if (!tmp_array_cell_len[i].equals("NULL") |  tmp_array_cell_len[i] != null) {
                        if (tmp_array_cell_len[i] == null || tmp_array_cell_len[i].equals("NULL") || tmp_array_cell_len[i].equals("")) {
                            empty = true;
                            //System.out.println("This find => " + tmp_array_cell_len[i]);
                        } else {
                            // System.out.println("This Else => " + tmp_array_cell_len[i]);
                            empty = false;
                            break;
                        }
                    }
                    //   }
                    if (!empty) {
                        array_cell.add(tmp_array_cell_len);
                    } // РЅРµ РїСѓСЃС‚Рѕ С‚РѕРіРґР° Р·Р°РЅРѕСЃРёРј.
                    //array_cell.add(tmp_array_cell_len);
                    not_null_dat = 0;
                }
            //РѕР±РЅСѓР»СЏРµРј РјР°СЃСЃРёРІ РґР»СЏ РїСЂРѕРІРµСЂРєРё РІС‹С€Рµ РµСЃР»Рё СЃС‚СЂРѕРєРё РїСЂРѕРіСЂР°РјРјР° РІРёРґРёС‚ РЅРѕ РѕРЅРё РїСѓСЃС‚С‹Рµ.
                // array_cell_len = null;
                for (int i = 0; i < array_cell_len.length; i++) {
                    array_cell_len[i] = "";
                }
            }
        }

        //System.out.println( sum_sheet);
        System.out.println(array_cell.size() + " -number string in mass");
        return array_cell;
    }

    public ArrayList<String> getDataNameTable(String name_sheet) throws FileNotFoundException, IOException {
        ArrayList<String> array_cell = new ArrayList<>();
        // РќРѕРјРµСЂР° СЏС‡РµРµРє Р±РµСЂСѓ РІ СЂСѓС‡РЅСѓСЋ РѕС‚ РєСѓРґР° Р±СЂР°С‚СЊ РЅР°Р·РІР°РЅРёСЏ РґР»СЏ С‚Р°Р±Р»РёС†
        FileInputStream inputStream = new FileInputStream(new File(patch_file));
        HSSFWorkbook wb = new HSSFWorkbook(inputStream);
        Sheet sheet = wb.getSheet(name_sheet); // РїРѕР»СѓС‡Р°РµРј РїРѕ РёРјРµРЅРё СЃС‚СЂР°РЅРёС†Сѓ РёР· Exel

        //  --- Р·Р°РїСЂРѕСЃС‹ РґР»СЏ С„РѕСЂРјРёСЂРѕРІР°РЅРёСЏ РЅР°Р·РІР°РЅРёСЏ С‚Р°Р±Р»РёС† Рё СЃС‚СЂРѕРєРё РЅР°С‡Р°Р»Р° РґР°РЅРЅС‹С…---
        Iterator<Row> iterRow = sheet.iterator(); // Р�С‚РµСЂР°С‚РѕСЂ СЃС‚СЂРѕРє
        List<String[]> massColumnName = new ArrayList(); // РґР»СЏ РґР°РЅРЅС‹С…  С„РѕСЂРјРёСЂРѕРІР°РЅРёСЏ РёРјРµРЅ СЃС‚РѕР»Р±С†РѕРІ
        int MaxLenNameMass = 0; // РґР»СЏ РґР»РёРЅРЅС‹ РјР°СЃСЃРёРІР° СЃС„РѕСЂРјРёСЂРѕРІР°РЅРЅС‹С… СЃС‚РѕР»Р±С†РѕРІ
        int maxLengtString = 0;   // РїСЂРѕСЃС‚Рѕ РјР°РєСЃРёРјР°Р»СЊРЅР°СЏ РґР»РёРЅРЅР° СЃС‚СЂРѕРєРё РЅР° СЃС‚СЂР°РЅРёС†Рµ
        int maxLnotNullString = 0;   // РїСЂРѕСЃС‚Рѕ РјР°РєСЃРёРјР°Р»СЊРЅР°СЏ РґР»РёРЅРЅР° СЃС‚СЂРѕРєРё РЅРµ РїСѓСЃС‚Р°СЏ РІ РєРѕРЅС†Рµ
        while (iterRow.hasNext()) {
            Row r = iterRow.next(); // СЃС‚СЂРѕРєР°
            System.out.print("endCell " + r.getLastCellNum() + " ");// + " DataCell ->" + r.getCell(r.getLastCellNum()));
            if (r.getLastCellNum() > maxLengtString) {
                maxLengtString = r.getLastCellNum(); // РїСЂРѕСЃС‚РѕРІС‹С‡РёСЃР»СЏРµРј РјР°РєСЃРёРјР°Р»СЊРЅСѓСЋ РґР»РёРЅРЅСѓ СЃС‚СЂРѕРєРё
            }
        }// РјРѕР¶РЅРѕ РєР°Рє С‚Рѕ СЃ РґРµР»Р°С‚СЊ РІ РѕРґРЅРѕРј РїСЂРѕС…РѕРґРµ РЅРѕ РЅРµ РґРѕ СЌС‚РѕРіРѕ

        iterRow = sheet.iterator(); // Р�С‚РµСЂР°С‚РѕСЂ СЃС‚СЂРѕРє(РµС‰Рµ СЂР°Р· РёРЅРёС†РёР°Р»РёР·Р°С†РёСЏ Р° РёРЅР°С‡СЏРµ РЅРµ СЂР°Р±РѕС‚Р°РµС‚)
        boolean findColumnSig = false; // СЃРёРіРЅР°Р» РґР»СЏ РѕСЃС‚Р°РЅРѕРІРєРё РїРµСЂРµР±РѕСЂР°
        int maxRowNum = 0; // РџРµСЂРµРјРµРЅРЅР°СЏ РґР»СЏ РѕРїСЂРµРґРµР»РµРЅРёСЏ РѕС‚ РєСѓРґР° РґР°РЅРЅС‹Рµ РЅР°С‡РёРЅР°СЋС‚СЃСЏ

        while (iterRow.hasNext()) {
            Row r = iterRow.next(); // СЃС‚СЂРѕРєР°
            Iterator<Cell> icell = r.cellIterator();
            int tmpI = 0;
            String[] nameRow = {"РќР°РёРјРµРЅРѕРІР°РЅРёРµ СЃРёРіРЅР°Р»Р°", "Tag name", "РќР°РёРјРµРЅРѕРІР°РЅРёРµ"}; // РЅСѓР¶РЅРѕ СЃРґРµР»Р°С‚СЊ СЃ РїРѕРјРѕС‰СЊСЋ С„Р°Р№Р»РѕРІ РєРѕРЅС„РёРіРѕРІ

            while (icell.hasNext()) {

                Cell c = icell.next();
                if (!findColumnSig) { // РїРѕРєР° РЅРµ РЅР°С€Р»Рё РїРµСЂРµР±РёСЂР°РµРј СЏС‡РµР№РєРё РґР»СЏ СѓСЃРєРѕСЂРµРЅРёСЏ
                    CellType cellType = c.getCellType();

                    switch (cellType) { // Р’С‹С‡РёСЃР»СЏРµРј С‚РёРї СЏС‡РµР№РєРё
                        case STRING: { // С‚РѕР»СЊРєРѕ РµСЃР»Рё СЃС‚СЂРѕРєР°
                            String dataC = c.getStringCellValue(); // РїРѕР»СѓС‡РёРј СЃС‚СЂРѕРєСѓ РёР· СЏС‡РµР№РєРё
                            for (int i = 0; i < nameRow.length; ++i) { // РїСЂРѕРіРѕРЅСЏРµРј РїРѕ СЃРїРёСЃРєСѓ  РёСЃРєРѕРјС‹С…  
                                if (dataC.equals(nameRow[i]))// РџР РѕРІРµСЂСЏРµРј РЅР° СЃРѕРІРїР°РґРµРЅРёСЏ РѕР±РѕР·РЅР°С‡РµРЅРёР№ СЃС‚РѕР»Р±РѕС† РµСЃР»Рё РµСЃС‚СЊ СЃРѕРІРїР°РґРµРЅРёСЏ С‚Рѕ СЃРѕ СЃР»РµРґСѓСЋС‰РµР№ СЃС‚СЂРѕРєРё РґР°РЅРЅС‹Рµ
                                {
                                    String[] tmpMassN = new String[maxLengtString]; // СЂР°Р·РјРµСЂ РјР°СЃСЃРёРІР° = СЂР°Р·РјРµСЂ РґР»РёРЅС‹ СЃС‚СЂРѕРєРё Exel
                                    findColumnSig = true; // РІС‹СЃС‚Р°РІРёР»Рё С„Р»Р°Рі РІ С‚Рѕ С‡С‚Рѕ РЅР°С€Р»Рё
                                    //System.out.println("Addres f cell " + c.getAddress()); // Р°РґСЂРµСЃ СЏС‡РµР№РєРё
                                    if (maxRowNum <= r.getRowNum()) {
                                        maxRowNum = r.getRowNum(); // Р°РґСЂРµСЃ СЃС‚СЂРѕРєРё СЃ РЅР°Р№РґРµРЅРЅС‹Рј СЃС‚РѕР»Р±С†РѕРј РѕРЅРё РјСѓРіСѓС‚ Р±С‹С‚СЊ СЂР°Р·РЅС‹Рµ РїРѕ СЌС‚РѕРјСѓ РјР°РєСЃРёРјР°Р»СЊРЅРѕРµ Р±РµСЂРµРј
                                        startReadData = maxRowNum + 1; // +1 С‚Р°Рє РєР°Рє РґР°РЅРЅС‹Рµ РЅР°С‡РёРЅР°СЋС‚СЃСЏ СЃРѕ СЃР»РµРґСѓСЋС‰РµР№ СЃС‚СЂРѕРєРё
                                        //System.out.println("Addres first data " + startReadData);
                                        Iterator<Cell> cellNameColumn = r.cellIterator();
                                        int j = 0;
                                        while (cellNameColumn.hasNext()) { // РїСЂРѕРіРѕРЅСЏРµРјСЃСЏ РїРѕ СЃС‚СЂРѕРєРµ РґР»СЏ С„РѕСЂРјРёСЂРѕРІР°РЅРёСЏ РјР°СЃСЃРёРІР°
                                            Cell cName = cellNameColumn.next();
                                            CellType cellTypecName = cName.getCellType();
                                            j = cName.getColumnIndex(); // Р�РЅРґРµРєСЃ СЏС‡РµР№РєРё , С‚Р°Рє РєР°Рє РЅРµ СЂР°Р±РѕС‚Р°РµС‚ С‡РµСЂРµР· ++, РїРµСЂРµСЃРєР°РєРёРІР°РµС‚
                                            switch (cellTypecName) { // Р’С‹С‡РёСЃР»СЏРµРј С‚РёРї СЏС‡РµР№РєРё
                                                case STRING: {
                                                    tmpMassN[j] = cName.getStringCellValue(); // РїРѕР»СѓС‡РёРј СЃС‚СЂРѕРєСѓ РёР· СЏС‡РµР№РєРё
                                                    //System.out.println("j= " + j + " Index Column " + cName.getColumnIndex() + " DataCell -  " + cName.getStringCellValue());
                                                }
                                                break;
                                                case BLANK:
                                                    tmpMassN[j] = null;
                                                    break;
                                                case NUMERIC:
                                                    tmpMassN[j] = Double.toString(cName.getNumericCellValue()); // Double
                                                    break;
                                                //case FORMULA : array_cell_len[tmp]=cell.getCellFormula(); // String
                                                case FORMULA:
                                                    switch (cName.getCachedFormulaResultType()) {
                                                        case NUMERIC:
                                                            tmpMassN[j] = Double.toString(cName.getNumericCellValue());
                                                            break;
                                                        case STRING:
                                                            tmpMassN[j] = cName.getRichStringCellValue().toString();
                                                            //System.out.println("Last evaluated as \"" + cell.getRichStringCellValue() + "\"");
                                                            break;
                                                    }
                                                    break;
                                                default:
                                                    array_cell.add("|");
                                            }

                                        }
                                        massColumnName.add(tmpMassN);
                                    }
                                  break; // РїСЂРµСЂС‹РІР°РµРј РјР°СЃСЃРёРІ РїРѕРёСЃРєР° С‚Р°Рє РєР°Рє РЅР°С€Р»Рё РёСЃС…РѕРґРЅРѕРµ
                                }
                            }

                        }
                        break;

                    }
                    ++tmpI;
                }
                
            }
            findColumnSig = false;
        }

            // С‚Р°Рє РјС‹ РїРµСЂРµР±РёСЂР°РµРј Рё Р°РЅР°Р»РёР·РёСЂСѓРµРј С‡С‚Рѕ РјС‹ РЅР°СЃРѕР±РёСЂР°Р»Рё Рё РґРµР»Р°РµРј РѕРґРёРЅ РјР°СЃСЃРёРІ 
        // String[] tmpMassN = new String[MaxLenNameMass];
        String[] tmpMassN = new String[maxLengtString];// Р·Р°РјРµРЅРёРј РЅР° СЌС‚Рѕ РїРѕРєР° РїРѕ С‚СѓРїРѕРјСѓ РґР»РёРЅРЅСѓ РјР°СЃСЃРёРІР°
        for (String[] mass : massColumnName) {
            for (int i = 0; i < mass.length; ++i) {
                if (tmpMassN[i] == null) { // РєР°Рє С‚Рѕ РЅР°РґРѕ РЅР° РѕР±РѕСЂРѕС‚ Рє РЅРёР¶РЅРµРјСѓ РїСЂРёРєСЂСѓС‡РёРІР°С‚СЊ РІРµСЂС…РЅРµРµ
                    tmpMassN[i] = mass[i];
                } else {
                    if (mass[i] != null) { // РµСЃР»Рё С‡С‚Рѕ РґРѕР±Р°РІР»СЏРµРј С‚РѕР¶Рµ РЅРµ РїСѓСЃС‚РѕРµ
                        tmpMassN[i] = tmpMassN[i] + "_" + mass[i];
                    }
                }
            }
        }
        // СѓР±РµСЂР°РµРј РїСѓСЃС‚РѕРµ РІ СЃС„РѕСЂРјРёСЂРѕРІР°РЅРЅРѕРј РјР°СЃСЃРёРІРµ 
        for (int i = 0; i < tmpMassN.length; ++i) {
            System.out.println(tmpMassN[i]);
            if (tmpMassN[i] == null) {
                tmpMassN[i] = "Num_" + Integer.toString(i);
            }
            for (int j = i + 1; j < tmpMassN.length; ++j) { //РїСЂРѕР±РµРіР°РµРј РµС‰Рµ СЂР°Р· С‡С‚Рѕ Р±С‹ РїСЂРѕРІРµСЂРёС‚СЊ РµСЃС‚СЊ Р»Рё РѕРґРёРЅР°РєРѕРІС‹Рµ Р·РЅР°С‡РµРЅРёСЏ 
                System.out.println(tmpMassN[i] + " == " + tmpMassN[j]);
                if (tmpMassN[i].equals(tmpMassN[j])) { // РµСЃР»Рё С‚РѕС‡РЅРѕ С‚Р°РєР°СЏ Р¶Рµ СЃС‚СЂРѕРєР° С‚Рѕ РјРµРЅСЏРµРј РµРµ
                    tmpMassN[j] = tmpMassN[j] + "_" + Integer.toString(j);
                }
            }
            array_cell.add(tmpMassN[i]);
        }

        System.out.println("maxLengtString " + maxLengtString + " array_cell.size " + array_cell.size());
        return array_cell;
    }

}

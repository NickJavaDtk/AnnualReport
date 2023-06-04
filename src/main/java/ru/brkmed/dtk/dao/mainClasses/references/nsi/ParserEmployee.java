package ru.brkmed.dtk.dao.mainClasses.references.nsi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.brkmed.dtk.dao.mainClasses.entityes.CurrentPositionFRMR;
import ru.brkmed.dtk.dao.mainClasses.entityes.Employee;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoPositionFRMR;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;


public class ParserEmployee {
    public String fullName;
    public CurrentPositionFRMR currentPosition;
    public Date startCert;
    public Date endCert;
    public String typePosition;

    public Boolean mainPosition = false;
    public String inn;
    public String snils;
    public String fon;


    public List<Employee> getLoadListEmployee(File file) {
        String tmpFullName;
        String tmpCurrentPosition;
        CurrentPositionFRMR tmpCurrentPositionFRMR;
        Date tmpSDate;
        Date tmpEDate;
        String tmpSnils = "";
        String tmpInn = "";
        String tmpFon = "";
        String startEmpty = "01.01.2022";
        String endEmpty = "01.01.2023";
        int countRows = 0;
        String[] typePositionArray = new Employee().getObsTypePosition();
        typePosition = typePositionArray[4];
        List<CurrentPositionFRMR> currentFRMR = new ControlerDaoPositionFRMR().listCurrentPositionFRMR();
        CurrentPositionFRMR optCurrentNull = currentFRMR.get(0);
        List<Employee> list = new ArrayList<>();
        SimpleDateFormat simple = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        SimpleDateFormat sParse = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);
        try {
            DataFormatter dataFormatter = new DataFormatter( );
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator<Row> rowIterator = mySheet.iterator( );
            int physicalRows = mySheet.getPhysicalNumberOfRows();
            while (rowIterator.hasNext( )) {
               // Employee employee = new Employee(  );
                Row row = rowIterator.next( );
                // For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator( );
                //List<Report> list = new ArrayList<>();
                while (cellIterator.hasNext( )) {
                    Cell cell = cellIterator.next( );
                    int index = cell.getColumnIndex( );
                    if (index == 0) {
                        //tmpStId = dataFormatter.formatCellValue(cell);
                        tmpFullName = cell.getStringCellValue( );
                        if (!tmpFullName.equals("")) {
                            fullName = tmpFullName;
                        }
                    } else if (index == 1) {
                        tmpCurrentPosition = cell.getStringCellValue( );
                        if (!tmpCurrentPosition.equals("")) {
                            String finalTmpSurname = tmpCurrentPosition;
                            Optional<CurrentPositionFRMR> optionalCurrent = currentFRMR.stream().filter(currentPositionFRMR ->
                                       finalTmpSurname.equals(currentPositionFRMR.getWorkPosition())
                            ).findAny();
                            currentPosition = optionalCurrent.orElse(optCurrentNull);

                        }
                    } else if (index == 2) {
                        String s = dataFormatter.formatCellValue(cell);
                        //String tmpAds = cell.getStringCellValue();
                        if (!s.equals("")) {
                            //tmpSDate = java.sql.Date.valueOf(s);
                            tmpSDate = sParse.parse(s);
                            startCert = tmpSDate;
                        } else {
                            startCert = simple.parse(startEmpty);
                        }
                    } else if (index == 3) {
                        String s = dataFormatter.formatCellValue(cell);
                        //String tmpAds = cell.getStringCellValue();
                        if (!s.equals("")) {
                            tmpEDate = sParse.parse(s);
                            endCert = tmpEDate;
                        } else {
                            endCert = simple.parse(endEmpty);
                        }

                    } else if (index == 4) {
                        if (cell.getCellTypeEnum( ) == CellType.STRING) {
                            tmpSnils = cell.getStringCellValue( );
                        } else if (cell.getCellTypeEnum( ) == CellType.NUMERIC) {
                            //tmpInn = String.valueOf(cell.getNumericCellValue());
                            tmpSnils = NumberToTextConverter.toText(cell.getNumericCellValue( ));
                        }
                        if (!tmpSnils.equals("")) {
                           snils = tmpInn;
                        } else {
                            snils = "";
                        }
                    } else if (index == 5) {
                        if (cell.getCellTypeEnum( ) == CellType.STRING) {
                            tmpInn = cell.getStringCellValue( );
                        } else if (cell.getCellTypeEnum( ) == CellType.NUMERIC) {
                            //tmpInn = String.valueOf(cell.getNumericCellValue());
                            tmpInn = NumberToTextConverter.toText(cell.getNumericCellValue( ));
                        }
                        if (!tmpSnils.equals("")) {
                            inn = tmpInn;
                        } else {
                            inn = "";
                        }
                    } else {
                        if (cell.getCellTypeEnum() == CellType.STRING) {
                            tmpFon = cell.getStringCellValue( );
                        } else if (cell.getCellTypeEnum( ) == CellType.NUMERIC) {
                            //Ext.Number.
                            tmpFon = String.valueOf(cell.getNumericCellValue( ));

                        }
                        if (!tmpFon.equals("")) {
                            fon = tmpFon;
                        } else {
                            fon = "";
                        }
                    }
//                    employee.setFullName(fullName);
//                    employee.setCurrentPosition(currentPosition);
//                    employee.setBeginningSignature(startCert);
//                    employee.setEndSignature(endCert);
//                    employee.setTypePosition(typePosition);
//                    employee.setInn(inn);
//                    employee.setSnils(snils);
//                    employee.setFon(fon);

                }


                list.add(new Employee(fullName, currentPosition, startCert, endCert, typePosition, mainPosition, inn, snils, fon) );
                countRows ++;
                if (countRows >= physicalRows) {
                    break;
                }

            }

        } catch (Exception e) {
            e.printStackTrace( );
        }
       // List<Employee> employeeList = new ArrayList<>(list);
        //List<Employee> list1 =
      // employeeList.stream().sorted(Comparator.comparing(employee -> employee.getFullName().compareTo(employee.getFullName())));

        return list;
    }


}

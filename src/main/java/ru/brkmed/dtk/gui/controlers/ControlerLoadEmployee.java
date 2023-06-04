package ru.brkmed.dtk.gui.controlers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.brkmed.dtk.dao.mainClasses.entityes.CurrentPositionFRMR;
import ru.brkmed.dtk.dao.mainClasses.entityes.Employee;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoEmployee;
import ru.brkmed.dtk.dao.mainClasses.references.controler.ControlerDaoPositionFRMR;
import ru.brkmed.dtk.dao.mainClasses.references.nsi.ParserCurrentPosition;
import ru.brkmed.dtk.dao.mainClasses.references.nsi.ParserEmployee;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class ControlerLoadEmployee implements Initializable {
    @FXML
    private Button btnLoad;

    @FXML
    private Button btnView;

    @FXML
    private Label lblStatus;

    @FXML
    private TextField txtFieldNameFile;

    private static File file;

    final FileChooser fileChooser = new FileChooser();

    Stage primaryStage = null;


    @FXML
    void openFindView(ActionEvent event) {
        txtFieldNameFile.clear();
        fileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Файл", "*.xlsx"));
        file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            txtFieldNameFile.setText(file.getAbsolutePath());
            btnLoad.setDisable(false);
        }

    }

    @FXML
    void startLoad(ActionEvent event) {
        ParserEmployee parserEmployee = new ParserEmployee();
        ControlerDaoEmployee daoEmployee = new ControlerDaoEmployee();
        Task taskLoad = createWorkerLoad(parserEmployee, daoEmployee);
        List<Employee> employeeList = parserEmployee.getLoadListEmployee(getFile());
//        List<Employee> emp = getLoadListEmployee(getFile());
//        String s;
        taskLoad.messageProperty().addListener(new ChangeListener<String>( ) {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                lblStatus.setText(newValue);
            }

        });
        try {
            new Thread( taskLoad ).start();
        } catch (Exception e) {
            lblStatus.setText("Файл не соответствует схеме\n " + e.getMessage());

        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLoad.setDisable(true);

    }

    public static File getFile() {
        return file;
    }

    public Task createWorkerLoad(ParserEmployee parser, ControlerDaoEmployee daoEmployee) {
        return  new Task( ) {
            @Override
            protected Object call() throws Exception {
                btnLoad.setDisable(true);
                updateMessage("Идет чтение файла");
                List<Employee> employeeList = parser.getLoadListEmployee(getFile());
                if (employeeList.size() == 0) {
                    updateMessage( "Ошибка чтения файла!!! Проверьте структуру файла");
                    return false;
                } else {
                    updateMessage("Файл прочитан");
                    updateMessage("Идет загрузка файла");
                    daoEmployee.loadFile(employeeList);
                    updateMessage("Файл загружен");
                    return true;
                }

            }

        };
    }

//    public List<Employee> getLoadListEmployee(File file) {
//         String fullName = "";
//         CurrentPositionFRMR currentPosition = null;
//         Date startCert = new Date(  );
//         Date endCert = new Date();
//         String typePosition = "";
//         String inn = "";
//         String snils = "";
//         String fon = "";
//
//        String tmpFullName;
//        String tmpCurrentPosition;
//        Date tmpSDate;
//        Date tmpEDate;
//        String tmpSnils = "";
//        String tmpInn = "";
//        String tmpFon = "";
//        String startEmpty = "01.01.2022";
//        String endEmpty = "01.01.2023";
//        int countRows = 0;
//        String[] typePositionArray = new Employee().getObsTypePosition();
//        typePosition = typePositionArray[4];
//        List<CurrentPositionFRMR> currentFRMR = new ControlerDaoPositionFRMR().listCurrentPositionFRMR();
//        CurrentPositionFRMR optCurrentNull = currentFRMR.get(0);
//        List<Employee> list = new ArrayList<>();
//        SimpleDateFormat simple = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
//        SimpleDateFormat sParse = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);
//        try {
//            DataFormatter dataFormatter = new DataFormatter( );
//            FileInputStream fis = new FileInputStream(file);
//            XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
//            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
//            Iterator<Row> rowIterator = mySheet.iterator( );
//            int physicalRows = mySheet.getPhysicalNumberOfRows();
//            while (rowIterator.hasNext( )) {
//                Employee employee = new Employee(  );
//                Row row = rowIterator.next( );
//                // For each row, iterate through each columns
//                Iterator<Cell> cellIterator = row.cellIterator( );
//                //List<Report> list = new ArrayList<>();
//                while (cellIterator.hasNext( )) {
//                    Cell cell = cellIterator.next( );
//                    int index = cell.getColumnIndex( );
//                    if (index == 0) {
//                        //tmpStId = dataFormatter.formatCellValue(cell);
//                        tmpFullName = cell.getStringCellValue( );
//                        if (!tmpFullName.equals("")) {
//                            fullName = tmpFullName;
//                        } else {
//                            countRows = physicalRows;
//                            break;
//                        }
//                    } else if (index == 1) {
//                        tmpCurrentPosition = cell.getStringCellValue( );
//                        if (!tmpCurrentPosition.equals("")) {
//                            String finalTmpSurname = tmpCurrentPosition;
//                            Optional<CurrentPositionFRMR> optionalCurrent = currentFRMR.stream().filter(currentPositionFRMR ->
//                                    finalTmpSurname.equals(currentPositionFRMR.getWorkPosition())
//                            ).findAny();
//                            currentPosition = optionalCurrent.orElse(optCurrentNull);
//                        }
//                    } else if (index == 2) {
//                        String s = dataFormatter.formatCellValue(cell);
//                        //String tmpAds = cell.getStringCellValue();
//                        if (!s.equals("")) {
//                            //tmpSDate = java.sql.Date.valueOf(s);
//                            tmpSDate = sParse.parse(s);
//                            startCert = tmpSDate;
//                        } else {
//                            startCert = simple.parse(startEmpty);
//                        }
//                    } else if (index == 3) {
//                        String s = dataFormatter.formatCellValue(cell);
//                        //String tmpAds = cell.getStringCellValue();
//                        if (!s.equals("")) {
//                            tmpEDate = sParse.parse(s);
//                            endCert = tmpEDate;
//                        } else {
//                            endCert = simple.parse(endEmpty);
//                        }
//
//                    } else if (index == 4) {
//                        if (cell.getCellTypeEnum( ) == CellType.STRING) {
//                            tmpSnils = cell.getStringCellValue( );
//                        } else if (cell.getCellTypeEnum( ) == CellType.NUMERIC) {
//                            //tmpInn = String.valueOf(cell.getNumericCellValue());
//                            tmpSnils = NumberToTextConverter.toText(cell.getNumericCellValue( ));
//                        }
//                        if (!tmpSnils.equals("")) {
//                            snils = tmpInn;
//                        } else {
//                            snils = "";
//                        }
//                    } else if (index == 5) {
//                        if (cell.getCellTypeEnum( ) == CellType.STRING) {
//                            tmpInn = cell.getStringCellValue( );
//                        } else if (cell.getCellTypeEnum( ) == CellType.NUMERIC) {
//                            //tmpInn = String.valueOf(cell.getNumericCellValue());
//                            tmpInn = NumberToTextConverter.toText(cell.getNumericCellValue( ));
//                        }
//                        if (!tmpSnils.equals("")) {
//                            inn = tmpInn;
//                        } else {
//                            inn = "";
//                        }
//                    } else {
//                        if (cell.getCellTypeEnum() == CellType.STRING) {
//                            tmpFon = cell.getStringCellValue( );
//                        } else if (cell.getCellTypeEnum( ) == CellType.NUMERIC) {
//                            //Ext.Number.
//                            tmpFon = String.valueOf(cell.getNumericCellValue( ));
//
//                        }
//                        if (!tmpFon.equals("")) {
//                            fon = tmpFon;
//                        } else {
//                            fon = "";
//                        }
//                    }
//                    employee.setFullName(fullName);
//                    employee.setCurrentPosition(currentPosition);
//                    employee.setBeginningSignature(startCert);
//                    employee.setEndSignature(endCert);
//                    employee.setTypePosition(typePosition);
//                    employee.setInn(inn);
//                    employee.setSnils(snils);
//                    employee.setFon(fon);
//
//                }
//
//
//                list.add(employee);
//                countRows ++;
//                if (countRows >= physicalRows) {
//                    break;
//                }
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace( );
//        }
//        // List<Employee> employeeList = new ArrayList<>(list);
//        //List<Employee> list1 =
//        // employeeList.stream().sorted(Comparator.comparing(employee -> employee.getFullName().compareTo(employee.getFullName())));
//
//        return list;
//    }

}

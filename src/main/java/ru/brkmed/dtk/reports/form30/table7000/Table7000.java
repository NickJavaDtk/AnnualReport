package ru.brkmed.dtk.reports.form30.table7000;

public class Table7000 {
    private String name;
    private String numberString;
    private Long total;
    private Long taskPolic;
    private Long taskHosp;
    private Long helpPolic;
    private Long helpHosp;
    private Long other;

    protected static final String cellOne = "Персональные компьютеры (моноблоки, системные блоки, терминалы, ноутбуки, планшеты)";
    protected static final String cellTwo = "из них:\n со сроком эксплуатации более 5 лет";
    protected static final String cellThree = "использующих операционные системы семейства Windows";
    protected static final String cellFour = "использующих операционные системы отечественной разработки";
    protected static final String cellFive = "использующих иные операционные системы";
    protected static final String cellSix = "Серверное оборудование";
    protected static final String cellSeven = "из них со сроком эксплуатации более 5 лет";
    protected static final String cellEight = "Печатающие устройства и МФУ";
    protected static final String cellNine = "из них со сроком эксплуатации более 5 лет";
    protected static final String cellTen = "Автоматизированные рабочие места, подключенные к медицинской информационной системе медицинской " +
            "организации или государственной информационной системе в сфере здравоохранения субъекта Российской Федерации";
    protected static final String cellEleven = "из них: автоматизированные рабочие места, подключенные к защищенной сети передачи данных субъекта Российской Федерации";
    protected static final String cellTwelve = "в сельской местности";
    protected static final String cellThirteen = "из них в ФАП и ФП";
    protected static final String cellFourteen = "мобильные автоматизированные рабочие места (планшеты)\n(из стр. 004)";
    protected static final String cellFifteen = "Количество программно-аппаратных комплексов VipNet Coordinator для подключения";
    protected static final String cellSixteen = "VipNet Coordinator HW50";
    protected static final String cellSeventeen = "VipNet Coordinator HW100";
    protected static final String cellEighteen = "VipNet Coordinator HW1000";
    protected static final String cellNineteen = "Количество точек подключения к сети Интернет по типам подключения";
    protected static final String cellTwenty = "из них: коммутируемый (модемный)";
    protected static final String cellTwentyOne = "широкополосный доступ по технологии xDSL";
    protected static final String cellTwentyTwo = "оптоволокно";
    protected static final String cellTwentyThree = "радиодоступ";
    protected static final String cellTwentyFour = "спутниковый канал";
    protected static final String cellTwentyFive = "VPN через сеть общего пользования";
    protected static final String cellTwentySex = "на скорости до 10 Мбит/с";
    protected static final String cellTwentySeven = "на скорости от 10 Мбит/с до 100 Мбит/с";
    protected static final String cellTwentyEight = "на скорости свыше 100 Мбит/с";
    protected static final String cellTwentyNine = "Число ФАП и ФП, подключенных к сети Интернет";


    public Table7000() {
    }

    public Table7000(String name, String numberString, Long total, Long taskPolic, Long taskHosp, Long helpPolic, Long helpHosp, Long other) {
        this.name = name;
        this.numberString = numberString;
        this.total = total;
        this.taskPolic = taskPolic;
        this.taskHosp = taskHosp;
        this.helpPolic = helpPolic;
        this.helpHosp = helpHosp;
        this.other = other;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberString() {
        return numberString;
    }

    public void setNumberString(String numberString) {
        this.numberString = numberString;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTaskPolic() {
        return taskPolic;
    }

    public void setTaskPolic(Long taskPolic) {
        this.taskPolic = taskPolic;
    }

    public Long getTaskHosp() {
        return taskHosp;
    }

    public void setTaskHosp(Long taskHosp) {
        this.taskHosp = taskHosp;
    }

    public Long getHelpPolic() {
        return helpPolic;
    }

    public void setHelpPolic(Long helpPolic) {
        this.helpPolic = helpPolic;
    }

    public Long getHelpHosp() {
        return helpHosp;
    }

    public void setHelpHosp(Long helpHosp) {
        this.helpHosp = helpHosp;
    }

    public Long getOther() {
        return other;
    }

    public void setOther(Long other) {
        this.other = other;
    }


}

package be.atelierrafaelaferreira.gendocalendar.model;

import java.util.Arrays;

public enum EmployeeIcsEnum {

    RAFA("rafa", "@@hashics@@.ics"),
    PAMMELLA("pammella", "uhubabuqumemegedemyg.ics"),
    MARIA("maria", "maguhynejuzanujeteha.ics"),
    RHOXANY("rhoxany", "esynepumazegygevysag.ics"),
    KHEYLLA("kheylla", "edepybypunynyjuvupus.ics"),
    ROBERTA("roberta", "upymejazegepetujahep.ics");

    private static final String baseUrl = "http://davi931.adm.gendo.app";

    private String name;

    private String icsGendoFileName;

    EmployeeIcsEnum(String name, String path) {
        this.name = name;
        this.icsGendoFileName = path;
    }

    public String getName() {
        return name;
    }

    public String getNewIcsName() {
        return name + ".ics";
    }

    public String getIcsGendoFileName() {
        return icsGendoFileName;
    }



    public String getUrl() {
        return baseUrl + "/" + icsGendoFileName;
    }

    public static String getUrl(String name) {
        return Arrays.stream(values())
            .filter(employee -> employee.getName().equalsIgnoreCase(name))
            .map(EmployeeIcsEnum::getUrl)
            .findFirst()
            .orElse(null);
    }
}

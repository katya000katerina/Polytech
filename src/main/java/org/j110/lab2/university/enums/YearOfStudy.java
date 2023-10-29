package org.j110.lab2.university.enums;

public enum YearOfStudy {
    I ("1'st"),
    II("2'nd"),
    III("3'rd"),
    IV("4'th");
    private String ordinalNumber;

    YearOfStudy(String ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

    public String getOrdinalNumber() {
        return ordinalNumber;
    }
}

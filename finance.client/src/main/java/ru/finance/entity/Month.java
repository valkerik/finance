package ru.finance.entity;

public enum Month {
    JANUARY("Январь"),
    FEBRUARY("Февраль"),
    MARCH("Март"),
    APRIL("Апрель"),
    MAY("Май"),
    JUNE("Июнь"),
    JULY("Июль"),
    AUGUST("Август"),
    SEPTEMBER("Сентябрь"),
    OCTOBER("Октябрь"),
    NOVEMBER("Ноябрь"),
    DECEMBER("Декабрь");

    private String name;

    Month(String name) {
        this.name = name;
    }

    public static Month[] getWinterMonths() {

        return new Month[]{DECEMBER, JANUARY, FEBRUARY};
    }

    public static Month[] getSummerMonths() {

        return new Month[]{JUNE, JULY, AUGUST};
    }

    public static Month[] getAutumnMonths() {

        return new Month[]{SEPTEMBER, OCTOBER, NOVEMBER};
    }

    public static Month[] getSpringMonths() {

        return new Month[]{MARCH, APRIL, MAY};
    }

    public String getName() {
        return name;
    }
}

package be.atelierrafaelaferreira.gendocalendar.model;

public enum DateType {
    DTSTART,
    DTEND,
    DTSTAMP,
    CREATED,
    LAST_MODIFIED("LAST-MODIFIED");

    private String value;
    DateType() {
        this.value = name();
    }

    DateType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

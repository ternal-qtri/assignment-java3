package com.poly.model.enums;

public enum Status {
    ACTIVE(true, "Hoạt động"),
    UNACTIVE(false, "Tạm dừng");

    private final boolean dbValue;
    private final String description;

    Status(boolean dbValue, String description) {
        this.dbValue = dbValue;
        this.description = description;
    }

    public boolean toDb() {
        return dbValue;
    }

    public String getDescription() {
        return description;
    }

    public static Status fromDb(boolean value) {
        return value ? ACTIVE : UNACTIVE;
    }
}

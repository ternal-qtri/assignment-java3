package com.poly.model.enums;

public enum Role {
    ADMIN(true, "Quản trị viên"),
    REPORTER(false, "Phóng viên");

    private final boolean dbValue;
    private final String description;

    Role(boolean dbValue, String description) {
        this.dbValue = dbValue;
        this.description = description;
    }

    public boolean toDb() {
        return dbValue;
    }

    public String getDescription() {
        return description;
    }

    public static Role fromDb(boolean value) {
        return value ? ADMIN : REPORTER;
    }
}

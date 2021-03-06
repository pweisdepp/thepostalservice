package com.serverdemo.myserver;

public class FieldFormat {

    String name;
    String format;
    Boolean optional;
    Boolean enumerated;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Boolean getOptional() {
        return optional;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    public Boolean getEnumerated() {
        return enumerated;
    }

    public void setEnumerated(Boolean enumerated) {
        this.enumerated = enumerated;
    }
}
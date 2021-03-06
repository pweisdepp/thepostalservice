package com.serverdemo.myserver;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryFormat {

    String name;
    Map<String, FieldFormat> formats = new HashMap<>();
    Map<String, List<String>> enumerations = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map getFormats() {
        return formats;
    }

    public void setFormats(Map formats) {
        this.formats = formats;
    }

    public Map getEnumerations() {
        return enumerations;
    }

    public void setEnumerations(Map enumerations) {
        this.enumerations = enumerations;
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("formats")
    private void unpackNested(Map<String, Object>[] formats) {
        Map<String, FieldFormat> countryFormats = new HashMap<>();

        for (Map<String, Object> field : formats) {
            FieldFormat format = new FieldFormat();
            format.setName((String) field.get("field"));
            format.setFormat((String) field.get("format"));
            format.setEnumerated((Boolean) field.getOrDefault("enumerated", false));
            format.setOptional((Boolean) field.getOrDefault("optional", false));
            countryFormats.put(format.name, format);
        }
        this.setFormats(countryFormats);
    }
}

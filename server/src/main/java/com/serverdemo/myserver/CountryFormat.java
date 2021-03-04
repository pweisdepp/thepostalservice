package com.serverdemo.myserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryFormat {
    String name;
    Map<String, FieldFormat> fields = new HashMap<>();
    Map<String, List<String>> enumerations = new HashMap<>();
}

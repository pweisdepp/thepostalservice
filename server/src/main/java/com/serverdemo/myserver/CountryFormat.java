package com.serverdemo.myserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryFormat {
    String name;
    Map fields = new HashMap<String, FieldFormat>();
    Map enumerations = new HashMap<String, List<String>>();
}

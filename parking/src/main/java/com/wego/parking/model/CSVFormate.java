package com.wego.parking.model;

import java.util.List;

public abstract class CSVFormate {
    public abstract CSVFormate constructFromStrings(List<String> values);
}

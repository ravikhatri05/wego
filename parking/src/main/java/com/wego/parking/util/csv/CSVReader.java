package com.wego.parking.util.csv;

import com.wego.parking.model.CSVFormate;
import org.springframework.core.io.ResourceLoader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class CSVReader<T extends CSVFormate> {

    ResourceLoader resourceLoader;

    public CSVReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public List<T> readCsv(String path, T obj) throws Exception {
        List<T> list = new LinkedList<>();
        InputStream fis = resourceLoader.getResource("classpath:" + path).getInputStream();
        Reader fr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        // for skipping the header
        List<String> values = CSVHelper.parseLine(fr);
        values = CSVHelper.parseLine(fr);
        while (values != null) {
            list.add((T) obj.constructFromStrings(values));
            values = CSVHelper.parseLine(fr);
        }
        return list;
    }
}

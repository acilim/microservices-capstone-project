package com.microservices.catalogapplication.util;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.microservices.catalogapplication.CatalogApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CsvReader {

    private static final CsvMapper mapper = new CsvMapper();

    public static <T> List<T> read(Class<T> clazz, String path) throws IOException {
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        ObjectReader reader = mapper.readerFor(clazz).with(schema);
        InputStream stream = CatalogApplication.class.getClassLoader().getResourceAsStream(path);
        return reader.<T>readValues(stream).readAll();
    }
}
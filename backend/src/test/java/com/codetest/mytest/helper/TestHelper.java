package com.codetest.mytest.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class TestHelper {
    public static InputStream getInputStreamFromFile(String fileName, Object context) {
        return context.getClass().getClassLoader().getResourceAsStream(fileName);
    }

    public static BufferedReader getBufferedReaderFromFile(String fileName, Object context) {
        return new BufferedReader(
                new InputStreamReader(getInputStreamFromFile(fileName, context),
                StandardCharsets.UTF_8)
        );
    }
}

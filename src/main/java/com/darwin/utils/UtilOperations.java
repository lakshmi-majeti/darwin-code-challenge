package com.darwin.utils;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;


public class UtilOperations {

    public static String readFileAsString(String filePath)throws Exception{
        File file = ResourceUtils.getFile("classpath:"+ filePath);
        String jsonString = new String(Files.readAllBytes(file.toPath()));
        return jsonString;
    }
}

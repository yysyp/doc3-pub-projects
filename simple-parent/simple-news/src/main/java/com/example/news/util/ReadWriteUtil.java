package com.example.news.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ReadWriteUtil {

    public static void writeToFileInHomeDir(File file, Object content) {
        writeToFileInHomeDir(file, content, StandardCharsets.UTF_8, true);
    }

    public static void writeToFileInHomeDir(File file, Object content, Charset charsetName, boolean append) {
        try {
            String lineEnding = System.lineSeparator();
            FileUtils.writeStringToFile(file, JsonUtil.object2Json(content)+lineEnding, charsetName, append);
        } catch (IOException e) {
            throw new RuntimeException("Write to File failed!", e);
        }
    }

    public static <T> T readObjectFromFileInHomeDir(File file, Class<T> c) {
        try {
            return JsonUtil.json2Object(FileUtils.readFileToString(file, StandardCharsets.UTF_8), c);
        } catch (IOException e) {
            throw new RuntimeException("Read from File failed!", e);
        }
    }

    public static <T> void writeObjectsToFileInHomeDir(File file, List<T> lists) {
        writeObjectsToFileInHomeDir(file, lists, true);
    }

    public static <T> void writeObjectsToFileInHomeDir(File file, List<T> lists, boolean append) {
        try {
            List<String> stringList = JsonUtil.listObject2ListJson(lists);
            FileUtils.writeLines(file, stringList, append);
        } catch (IOException e) {
            throw new RuntimeException("Write to File failed!", e);
        }
    }

    public static <T> List<T> readObjectsFromFileInHomeDir(File file, Class<T> c) {
        return readObjectsFromFileInHomeDir(file, StandardCharsets.UTF_8, c);
    }

    public static <T> List<T> readObjectsFromFileInHomeDir(File file, Charset charset, Class<T> c) {
        try {
            List<String> lines = FileUtils.readLines(file, charset);
            return lines.stream().map(s -> JsonUtil.json2Object(s, c)).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Read from File failed!", e);
        }
    }

}

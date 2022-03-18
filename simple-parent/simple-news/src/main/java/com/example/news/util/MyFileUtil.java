package com.example.news.util;

import org.apache.catalina.core.ApplicationPart;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.util.Date;

public class MyFileUtil {

    public static String getUserHomeDir() {
        return Paths.get(System.getProperty("user.home")).toAbsolutePath()
                .toString();
    }

    public static String getCurrentDir() {
        return Paths.get(System.getProperty("user.dir")).toAbsolutePath()
                .toString();
    }

    public static File getFileInHomeDir(String fileName) {
        return new File(getUserHomeDir() + "/" + fileName);
    }

    public static File getLogInHomeDir(String key) {
        return new File(getUserHomeDir() + "/" + key + "-" + MyTimeUtil.getNowStr() + ".log");
    }

    public static void setFullPermission(File file) {
        if (file != null) {
            file.setReadable(true, false);
            file.setExecutable(true, false);
            file.setWritable(true, false);
        }
    }

    public static void createDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                deleteFile(f);
            }
        } else {
            file.delete();
        }
    }

    public static File resolverTempFile(MultipartFile multipartFile) throws IOException {
        Class<? extends MultipartFile> multipartFileClass = multipartFile.getClass();
        try {
            Field partField = multipartFileClass.getDeclaredField("part");
            partField.setAccessible(true);
            ApplicationPart part = (ApplicationPart) partField.get(multipartFile);
            Field fileItemField = ApplicationPart.class.getDeclaredField("fileItem");
            fileItemField.setAccessible(true);
            FileItem fileItem = (FileItem) fileItemField.get(part);
            Class<? extends FileItem> fileItemClass = fileItem.getClass();
            Field tempFileField = fileItemClass.getDeclaredField("tempFile");
            tempFileField.setAccessible(true);
            return (File) tempFileField.get(fileItem);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}

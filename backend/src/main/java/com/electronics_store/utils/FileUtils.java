package com.electronics_store.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
    private static final String IMAGE_PATH = "./src/main/resources/static/files_upload/images/";
    private static final String FILE_PATH = "./src/main/resources/static/files_upload/files/";
    private static final String ROOT_PATH = "./src/main/resources/static";

    public static String saveImage(@NonNull MultipartFile file) {
        LocalDateTime time = LocalDateTime.now();
        String dateTimeString = time.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + "-";
        String fileName = dateTimeString + file.getOriginalFilename();
        Path path = Paths.get(IMAGE_PATH);
        Path pathRoot = Paths.get(ROOT_PATH);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            path = path.resolve(fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving image");
        }
        String result = "\\" + pathRoot.relativize(path).toString();
        return result.replaceAll("\\\\", "/");
    }

    public static String[] saveMultipleFile(MultipartFile[] files) {
        String[] listPath = new String[files.length];
        try {
            for (int i = 0; i < files.length; i++) {
                listPath[i] = saveImage(files[i]);
                if (i == 1) {
                    throw new Exception();
                }
            }
            return listPath;
        } catch (Exception e) {
            e.printStackTrace();
            FileUtils.deleteMultiPath(listPath, 0);
            throw new RuntimeException("Error saving image");
        }
    }

    public static boolean deleteImage(String path) {
        String executePath = ROOT_PATH + path;
        Path pathFile = Paths.get(executePath);
        try {
            Files.delete(pathFile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting image");
        }
    }

    public static boolean checkPath(String path) {
        String executePath = ROOT_PATH + path;
        Path pathFile = Paths.get(executePath);
        return Files.exists(pathFile);
    }

    public static boolean isImageExisted(String fileName) {
        String executePath = IMAGE_PATH + fileName;
        Path pathFile = Paths.get(executePath);
        return Files.exists(pathFile);
    }

    public static void deleteMultiPath(String[] paths, int index) {
        int i = index;
        if (i == paths.length) {
            return;
        }
        try {
            for (i = index; i < paths.length; i++) {
                if (paths[i] == null) {
                    continue;
                }
                if (FileUtils.checkPath(paths[i])) {
                    FileUtils.deleteImage(paths[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            deleteMultiPath(paths, i + 1);
            throw new RuntimeException("Error deleting multipart image");
        }
    }
}

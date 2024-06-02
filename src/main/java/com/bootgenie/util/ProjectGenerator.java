package com.bootgenie.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ProjectGenerator {

    private static final String PATTERN_DIR = "patterns/";
    private static final Logger log = LoggerFactory.getLogger(ProjectGenerator.class);

    public static ByteArrayInputStream generateProjectZip(String projectName, String packageName, String pattern) throws IOException {
        Path tempDir = Files.createTempDirectory(projectName);

        // Create the base directory structure
        String baseDir = "src/main/java/" + packageName.replace('.', '/');
        Path baseDirPath = tempDir.resolve(baseDir);
        Files.createDirectories(baseDirPath);

        // Copy and modify files
        copyAndModifyFiles(PATTERN_DIR + pattern, baseDirPath, packageName);

        // Create ZIP file
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ZipOutputStream zos = new ZipOutputStream(baos)) {
            Path sourceDir = tempDir;
            Files.walk(sourceDir).filter(path -> !Files.isDirectory(path)).forEach(path -> {
                String zipEntryName = sourceDir.relativize(path).toString();
                try {
                    zos.putNextEntry(new ZipEntry(zipEntryName));
                    Files.copy(path, zos);
                    zos.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        // Clean up temporary files
        deleteDirectory(tempDir.toFile());
        log.info("generateProjectZip :: {} ,프로젝트 생성에 성공했습니다.", projectName);

        return new ByteArrayInputStream(baos.toByteArray());
    }

    private static void copyAndModifyFiles(String sourceDir, Path targetDir, String packageName) throws IOException {
        Files.walk(Paths.get(sourceDir)).forEach(sourcePath -> {
            try {
                Path targetPath = targetDir.resolve(Paths.get(sourceDir).relativize(sourcePath));
                if (Files.isDirectory(sourcePath)) {
                    Files.createDirectories(targetPath);
                } else {
                    Files.copy(sourcePath, targetPath);
                    if (sourcePath.toString().endsWith(".java")) {
                        modifyJavaFile(targetPath, packageName);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        log.info("copyAndModifyFiles :: {} ,복사 및 수정이 성공했습니다.", packageName);
    }

    private static void modifyJavaFile(Path javaFilePath, String packageName) throws IOException {
        String content = new String(Files.readAllBytes(javaFilePath));
        content = content.replace("{BootGenie}", packageName);
        Files.write(javaFilePath, content.getBytes());
        log.info("modifyJavaFile :: {} ,파일의 수정이 완료되었습니다.", javaFilePath);

    }

    private static void deleteDirectory(File file) throws IOException {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                deleteDirectory(child);
            }
            log.info("deleteDirectory :: {} 디렉토리가 정상적으로 제거되었습니다.",file.getName());
        }
        file.delete();
    }
}

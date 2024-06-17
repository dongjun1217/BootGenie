package com.bootgenie.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ProjectGenerator {

    private static final String PATTERN_DIR = "bg-patterns/";
    private static final Logger log = LoggerFactory.getLogger(ProjectGenerator.class);

    public static ByteArrayInputStream generateProjectZip(
            String projectName,
            String packageName,
            String pattern,
            String javaVersion,
            String springBootVersion,
            String packagingType,
            List<String> dependencies) throws IOException {

        Path tempDir = Files.createTempDirectory(projectName);

        // Create the base directory structure
        String baseDir = "src/main/java/" + packageName.replace('.', '/');
        Path baseDirPath = tempDir.resolve(baseDir);
        Files.createDirectories(baseDirPath);

        // Copy and modify files (Java)
        copyAndModifyFiles(PATTERN_DIR + pattern, baseDirPath, packageName);

        // Copy and modify files (Tests)
        copyAndModifyFiles("bg-test", tempDir.resolve("src/test/"+packageName.replace('.','/')), packageName);

        // Copy and modify files (Gradle)
        copyAndModifyFiles("bg-gradle/",tempDir, projectName);

        // Create build.gradle file using BuildGradleWriter
        BuildGradleWriter buildGradleWriter = new BuildGradleWriter();
        buildGradleWriter.writeTo(tempDir, javaVersion, springBootVersion, packagingType, dependencies);

        // Create application.properties file
        createApplicationProperties(tempDir, projectName);

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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        // Clean up temporary files
        deleteDirectory(tempDir.toFile());
        log.info("generateProjectZip :: {} ,프로젝트 생성에 성공했습니다.", projectName);

        return new ByteArrayInputStream(baos.toByteArray());
    }

    private static void copyAndModifyFiles(String sourceDir, Path targetDir, String name) throws IOException {
        Files.walk(Paths.get(sourceDir)).forEach(sourcePath -> {
            try {
                Path targetPath = targetDir.resolve(Paths.get(sourceDir).relativize(sourcePath));
                if (Files.isDirectory(sourcePath)) {
                    Files.createDirectories(targetPath);
                } else {
                    Files.copy(sourcePath, targetPath);
                    if (sourcePath.toString().endsWith(".java")) {
                        modifyJavaFile(targetPath, name);
                    }
                    if (sourcePath.toString().endsWith("settings.gradle")) {
                        modifyJavaFile(targetPath, name);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        log.info("copyAndModifyFiles :: {} ,복사 및 수정이 성공했습니다.", name);
    }

    private static void modifyJavaFile(Path javaFilePath, String Name) throws IOException {
        String content = new String(Files.readAllBytes(javaFilePath));
        content = content.replace("{BootGenie}", Name);
        Files.write(javaFilePath, content.getBytes());
        log.info("modifyJavaFile :: {} ,파일의 수정이 완료되었습니다.", javaFilePath);
    }

    private static void createApplicationProperties(Path tempDir, String projectName) throws IOException {
        Path propertiesFilePath = tempDir.resolve("src/main/resources/application.properties");
        Files.createDirectories(propertiesFilePath.getParent());
        String content = String.format("spring.application.name=%s", projectName);
        Files.write(propertiesFilePath, content.getBytes());
        log.info("createApplicationProperties :: application.properties 파일 생성이 완료되었습니다.");
    }

    private static void deleteDirectory(File file) throws IOException {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                deleteDirectory(child);
            }
            log.info("deleteDirectory :: {} 디렉토리가 정상적으로 제거되었습니다.", file.getName());
        }
        file.delete();
    }
}

package com.bootgenie.util;

import com.bootgenie.model.ProjectRequest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/*

    제네레이트 클래스별 메서드 구현하기 (컨트롤러, 서비스, 모델 등)

 */
public class ProjectGenerator {

    public static ByteArrayOutputStream generateProject(ProjectRequest request) {
        try {
            String projectName = request.getProjectName();
            String basePath = System.getProperty("java.io.tmpdir") + "/" + projectName;
            Files.createDirectories(Paths.get(basePath));

            // Generate application class
            String applicationClassContent = generateApplicationClass(request);
            String packagePath = basePath + "/src/main/java/" + request.getPackageName().replace('.', '/');
            Files.createDirectories(Paths.get(packagePath));
            Files.write(Paths.get(packagePath, "Application.java"), applicationClassContent.getBytes());


            // Create zip file
            File zipFile = new File(basePath + ".zip");
            ZipUtils.pack(new File(basePath), zipFile);

            // Convert zip file to ByteArrayOutputStream
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try (FileOutputStream fos = new FileOutputStream(zipFile)) {
                byteArrayOutputStream.write(Files.readAllBytes(zipFile.toPath()));
            }

            return byteArrayOutputStream;
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate project", e);
        }
    }

    private static String generateApplicationClass(ProjectRequest request) {


        return "package " + request.getPackageName() + ";\n\n" +
                "import org.springframework.boot.SpringApplication;\n" +
                "import org.springframework.boot.autoconfigure.SpringBootApplication;\n\n" +
                "@SpringBootApplication\n" +
                "public class Application {\n" +
                "    public static void main(String[] args) {\n" +
                "        SpringApplication.run(Application.class, args);\n" +
                "    }\n" +
                "}\n";
    }


    private static String generateHeader(String packageName){

        return "";
    }
}

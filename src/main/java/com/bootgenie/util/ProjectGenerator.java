package com.bootgenie.util;

import com.bootgenie.model.ProjectRequest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProjectGenerator {

    public static ByteArrayOutputStream generateProject(ProjectRequest request) {
        try {
            String projectName = request.getProjectName();
            String basePath = System.getProperty("java.io.tmpdir") + "/" + projectName;
            Files.createDirectories(Paths.get(basePath));

            // Generate build file (build.gradle or pom.xml)
            String buildFileContent = generateBuildFile(request);
            Files.write(Paths.get(basePath, getBuildFileName(request.getBuildTool())), buildFileContent.getBytes());

            // Generate application class
            String applicationClassContent = generateApplicationClass(request);
            String packagePath = basePath + "/src/main/java/" + request.getBasePackage().replace('.', '/');
            Files.createDirectories(Paths.get(packagePath));
            Files.write(Paths.get(packagePath, "Application.java"), applicationClassContent.getBytes());

            // Generate README
            String readmeContent = generateReadme(request);
            Files.write(Paths.get(basePath, "README.md"), readmeContent.getBytes());

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

    private static String generateBuildFile(ProjectRequest request) {
        StringBuilder buildFile = new StringBuilder();
        buildFile.append("plugins {").append("\n");
        buildFile.append("    id 'org.springframework.boot' version '").append(request.getSpringBootVersion()).append("'").append("\n");
        buildFile.append("    id 'io.spring.dependency-management' version '1.0.11.RELEASE'").append("\n");
        buildFile.append("    id 'java'").append("\n");
        buildFile.append("}").append("\n\n");

        buildFile.append("group = '").append(request.getBasePackage()).append("'").append("\n");
        buildFile.append("version = '0.0.1-SNAPSHOT'").append("\n");
        buildFile.append("sourceCompatibility = '11'").append("\n\n");

        buildFile.append("repositories {").append("\n");
        buildFile.append("    mavenCentral()").append("\n");
        buildFile.append("}").append("\n\n");

        buildFile.append("dependencies {").append("\n");
        buildFile.append("    implementation 'org.springframework.boot:spring-boot-starter'").append("\n");
        for (String dependency : request.getDependencies()) {
            buildFile.append("    implementation 'org.springframework.boot:spring-boot-starter-").append(dependency).append("'").append("\n");
        }
        buildFile.append("    testImplementation 'org.springframework.boot:spring-boot-starter-test'").append("\n");
        buildFile.append("}").append("\n\n");

        buildFile.append("test {").append("\n");
        buildFile.append("    useJUnitPlatform()").append("\n");
        buildFile.append("}").append("\n");

        return buildFile.toString();
    }

    private static String getBuildFileName(String buildTool) {
        return buildTool.equals("maven") ? "pom.xml" : "build.gradle";
    }

    private static String generateApplicationClass(ProjectRequest request) {
        return "package " + request.getBasePackage() + ";\n\n" +
                "import org.springframework.boot.SpringApplication;\n" +
                "import org.springframework.boot.autoconfigure.SpringBootApplication;\n\n" +
                "@SpringBootApplication\n" +
                "public class Application {\n" +
                "    public static void main(String[] args) {\n" +
                "        SpringApplication.run(Application.class, args);\n" +
                "    }\n" +
                "}\n";
    }

    private static String generateReadme(ProjectRequest request) {
        StringBuilder readme = new StringBuilder();
        readme.append("# ").append(request.getProjectName()).append("\n\n");
        readme.append("## Design Pattern: ").append(request.getDesignPattern()).append("\n\n");

        if ("singleton".equalsIgnoreCase(request.getDesignPattern())) {
            readme.append("### Singleton Pattern Example\n\n");
            readme.append("```java\n");
            readme.append("public class Singleton {\n");
            readme.append("    private static Singleton instance;\n\n");
            readme.append("    private Singleton() {}\n\n");
            readme.append("    public static Singleton getInstance() {\n");
            readme.append("        if (instance == null) {\n");
            readme.append("            instance = new Singleton();\n");
            readme.append("        }\n");
            readme.append("        return instance;\n");
            readme.append("    }\n");
            readme.append("}\n");
            readme.append("```\n");
        } else if ("mvc".equalsIgnoreCase(request.getDesignPattern())) {
            readme.append("### MVC Pattern Example\n\n");
            readme.append("```java\n");
            readme.append("@Controller\n");
            readme.append("public class HomeController {\n");
            readme.append("    @GetMapping(\"/\")\n");
            readme.append("    public String home() {\n");
            readme.append("        return \"home\";\n");
            readme.append("    }\n");
            readme.append("}\n");
            readme.append("```\n");
        }

        readme.append("### Dependencies\n\n");
        for (String dependency : request.getDependencies()) {
            readme.append("- ").append(dependency).append("\n");
        }

        return readme.toString();
    }
}

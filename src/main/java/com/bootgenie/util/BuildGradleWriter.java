package com.bootgenie.util;

import com.bootgenie.util.handler.DependencyHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BuildGradleWriter {

    public void writeTo(Path path, String javaVersion, String springBootVersion, String packagingType, List<String> dependencies) throws IOException {
        DependencyHandler dependencyHandler = new DependencyHandler(dependencies);
        StringBuilder buildGradleContent = new StringBuilder();

        buildGradleContent.append(String.format(
            "plugins {\n"+
            "  id 'java'\n"+
            "%s"+
            "  id 'org.springframework.boot' version '%s'\n"+
            "  id 'io.spring.dependency-management' version '1.1.5'\n"+
            "  %s"+
            "}\n"+
            "\n"+
            "group = 'com.example'\n"+
            "version = '0.0.1-SNAPSHOT'\n"+
            "\n"+
            "java {\n"+
            "  sourceCompatibility = '%s'\n"+
            "}\n"+
            "\n"+
            "repositories {\n"+
            "  mavenCentral()\n"+
            "}\n"+
            "\n"+
            "dependencies {\n"+
            "  implementation 'org.springframework.boot:spring-boot-starter'\n"+
            "  %s"+
            "  testImplementation 'org.springframework.boot:spring-boot-starter-test'\n"+
            "  testRuntimeOnly 'org.junit.platform:junit-platform-launcher'\n"+
            "}\n"+
            "\n"+
            "tasks.named('test') {\n"+
            "  useJUnitPlatform()\n"+
            "\n}"
            ,
                "war".equalsIgnoreCase(packagingType) ? "  id 'war'\n" : "",
                springBootVersion,
                dependencyHandler.getPlugins(),
                javaVersion,
                dependencyHandler.getDependencies()
        ));

        buildGradleContent.append(dependencyHandler.getConfigurations());
        buildGradleContent.append(dependencyHandler.getGenerateJava());


        Files.writeString(path.resolve("build.gradle"), buildGradleContent.toString());
    }
}

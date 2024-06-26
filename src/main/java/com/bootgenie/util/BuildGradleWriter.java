package com.bootgenie.util;

import com.bootgenie.util.handler.DependencyHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BuildGradleWriter {

    public void writeTo(Path path,String projectGroup, String javaVersion, String springBootVersion, String packagingType, List<String> dependencies) throws IOException {
        DependencyHandler dependencyHandler = new DependencyHandler(dependencies);
        StringBuilder buildGradleContent = new StringBuilder();

        buildGradleContent.append(String.format(
                "plugins {\n" +
                        "  id 'java'\n" +
                        "  id 'org.springframework.boot' version '%s'\n" +
                        "  id 'io.spring.dependency-management' version '1.1.4'\n" +
                        "%s\n" +
                        "}\n" +
                        "\n" +
                        "group = '%s'\n" +
                        "version = '0.0.1-SNAPSHOT'\n" +
                        "\n" +
                        "java {\n" +
                        "  sourceCompatibility = '%s'\n" +
                        "}\n" +
                        "\n" +
                        "repositories {\n" +
                        "  mavenCentral()\n",
                springBootVersion, dependencyHandler.getPlugins(), projectGroup, javaVersion
        ));

        if (dependencyHandler.hasAdditionalRepositories()) {
            buildGradleContent.append(
                    "  maven { url 'https://repo.spring.io/milestone' }\n"
            );
        }

        buildGradleContent.append("}\n");

        if (dependencyHandler.hasExt()) {
            buildGradleContent.append(String.format(
                    "\next {\n" +
                            "%s\n" +
                            "}\n",
                    dependencyHandler.getExt()
            ));
        }

        buildGradleContent.append(String.format(
                "\ndependencies {\n" +
                        "  implementation 'org.springframework.boot:spring-boot-starter'\n" +
                        "  implementation 'org.springframework.boot:spring-boot-starter-web'\n" +
                        "  testImplementation 'org.springframework.boot:spring-boot-starter-test'\n" +
                        "  testRuntimeOnly 'org.junit.platform:junit-platform-launcher'\n" +
                        "%s\n" +
                        "}\n" +
                        "\n" +
                        "tasks.named('test') {\n" +
                        "  useJUnitPlatform()\n" +
                        "}\n",
                dependencyHandler.getDependencies()
        ));

        buildGradleContent.append(dependencyHandler.getConfigurations());
        buildGradleContent.append(dependencyHandler.getGenerateJava());

        Files.writeString(path.resolve("build.gradle"), buildGradleContent.toString());
    }
}
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

        buildGradleContent.append(String.format("""
            plugins {
              id 'java'
              %s
              id 'org.springframework.boot' version '%s'
              id 'io.spring.dependency-management' version '1.1.5'
              %s
            }

            group = 'com.example'
            version = '0.0.1-SNAPSHOT'

            java {
              toolchain {
                languageVersion = JavaLanguageVersion.of(%s)
              }
            }

            repositories {
              mavenCentral()
            }

            dependencies {
              implementation 'org.springframework.boot:spring-boot-starter'
              %s
              testImplementation 'org.springframework.boot:spring-boot-starter-test'
              testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
            }

            tasks.named('test') {
              useJUnitPlatform()
            }
            """,
                "war".equalsIgnoreCase(packagingType) ? "id 'war'" : "",
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

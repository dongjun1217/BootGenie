package com.bootgenie.util.handler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DependencyHandler {

    private final List<String> dependencies;
    private static final Map<String, String> PLUGIN_MAP = Map.of(
            "GraphQL DGS Code Generation", "id 'com.netflix.dgs.codegen' version '6.2.1'",
            "GraalVM Native Support", "id 'org.graalvm.buildtools.native' version '0.10.2'"
    );

    private static final Map<String, String> EXT_MAP = Map.of(
            "Spring Modulith", "set('springModulithVersion', \"1.2.0\")"
    );

    private static final Map<String, String> DEPENDENCY_MAP = Map.of(
            "Lombok", "compileOnly 'org.projectlombok:lombok'\nannotationProcessor 'org.projectlombok:lombok'",
            "Spring Configuration Processor", "annotationProcessor 'org.springframework.boot:spring-boot-configuration-processor'",
            "GraphQL DGS Code Generation", "implementation 'com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter'",
            "GraalVM Native Support", "implementation 'org.graalvm.nativeimage:svm'",
            "Spring Boot DevTools", "developmentOnly 'org.springframework.boot:spring-boot-devtools'",
            "Docker Compose Support", "developmentOnly 'org.springframework.boot:spring-boot-docker-compose'"
    );

    private static final Map<String, String> CONFIGURATION_MAP = Map.of(
            "Lombok", "configurations {\n  compileOnly {\n    extendsFrom annotationProcessor\n  }\n}"
    );

    private static final Map<String, String> GENERATE_JAVA_MAP = Map.of(
            "GraphQL DGS Code Generation", "generateJava {\n  schemaPaths = [\"${projectDir}/src/main/resources/graphql-client\"]\n  packageName = 'com.example.demo.codegen'\n  generateClient = true\n}"
    );

    public DependencyHandler(List<String> dependencies) {
        this.dependencies = dependencies;
    }

    public String getPlugins() {
        return dependencies.stream()
                .map(PLUGIN_MAP::get)
                .filter(plugin -> plugin != null)
                .collect(Collectors.joining("\n"));
    }

    public String getExt() {
        return dependencies.stream()
                .map(EXT_MAP::get)
                .filter(ext -> ext != null)
                .collect(Collectors.joining("\n"));
    }

    public boolean hasExt() {
        return dependencies.stream().anyMatch(EXT_MAP::containsKey);
    }

    public String getDependencies() {
        return dependencies.stream()
                .map(DEPENDENCY_MAP::get)
                .filter(dependency -> dependency != null)
                .collect(Collectors.joining("\n"));
    }

    public String getConfigurations() {
        return dependencies.stream()
                .map(CONFIGURATION_MAP::get)
                .filter(configuration -> configuration != null)
                .collect(Collectors.joining("\n"));
    }

    public String getGenerateJava() {
        return dependencies.stream()
                .map(GENERATE_JAVA_MAP::get)
                .filter(generateJava -> generateJava != null)
                .collect(Collectors.joining("\n"));
    }
}

package com.bootgenie.util.handler;

import java.util.List;

public class DependencyHandler {

    private final List<String> dependencies;

    public DependencyHandler(List<String> dependencies) {
        this.dependencies = dependencies;
    }

    public String getPlugins() {
        StringBuilder plugins = new StringBuilder();
        if (dependencies.contains("graphql-dgs-codegen")) {
            plugins.append("id 'com.netflix.dgs.codegen' version '6.2.1'\n");
        }
        if (dependencies.contains("graalvm-native-support")) {
            plugins.append("id 'org.graalvm.buildtools.native' version '0.10.2'\n");
        }
        return plugins.toString();
    }

    public String getDependencies() {
        StringBuilder dependenciesBlock = new StringBuilder();
        if (dependencies.contains("lombok")) {
            dependenciesBlock.append("compileOnly 'org.projectlombok:lombok'\n");
            dependenciesBlock.append("annotationProcessor 'org.projectlombok:lombok'\n");
        }
        if (dependencies.contains("graphql-dgs-codegen")) {
            dependenciesBlock.append("implementation 'com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter'\n");
        }
        if (dependencies.contains("graalvm-native-support")) {
            dependenciesBlock.append("implementation 'org.graalvm.nativeimage:svm'\n");
        }
        return dependenciesBlock.toString();
    }

    public String getConfigurations() {
        if (dependencies.contains("lombok")) {
            return """
                configurations {
                  compileOnly {
                    extendsFrom annotationProcessor
                  }
                }
                """;
        }
        return "";
    }

    public String getGenerateJava() {
        if (dependencies.contains("graphql-dgs-codegen")) {
            return """
                generateJava {
                  schemaPaths = ["${projectDir}/src/main/resources/graphql-client"]
                  packageName = 'com.example.demo.codegen'
                  generateClient = true
                }
                """;
        }
        return "";
    }
}

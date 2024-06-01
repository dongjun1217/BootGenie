package com.bootgenie.model;

import java.util.List;

public class ProjectRequest {
    private String projectName;
    private String packageName;
    private String pattern;

    public ProjectRequest() {}

    public ProjectRequest(String projectName, String packageName, String pattern) {
        this.projectName = projectName;
        this.packageName = packageName;
        this.pattern = pattern;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}

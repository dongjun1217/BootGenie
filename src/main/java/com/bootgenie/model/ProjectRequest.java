package com.bootgenie.model;

public class ProjectRequest {
    private String projectName;
    private String projectDescription;
    private String projectJavaVersion;
    private String projectArtifact;
    private String projectGroup;
    private String projectSpringVersion;
    private String projectBuildTool;
    private String packageName;
    private String pattern;

    public ProjectRequest() {
    }

    public ProjectRequest(String projectName, String projectDescription, String projectJavaVersion, String projectArtifact, String projectGroup, String projectSpringVersion, String projectBuildTool, String packageName, String pattern) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectJavaVersion = projectJavaVersion;
        this.projectArtifact = projectArtifact;
        this.projectGroup = projectGroup;
        this.projectSpringVersion = projectSpringVersion;
        this.projectBuildTool = projectBuildTool;
        this.packageName = packageName;
        this.pattern = pattern;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectJavaVersion() {
        return projectJavaVersion;
    }

    public void setProjectJavaVersion(String projectJavaVersion) {
        this.projectJavaVersion = projectJavaVersion;
    }

    public String getProjectArtifact() {
        return projectArtifact;
    }

    public void setProjectArtifact(String projectArtifact) {
        this.projectArtifact = projectArtifact;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProjectSpringVersion() {
        return projectSpringVersion;
    }

    public void setProjectSpringVersion(String projectSpringVersion) {
        this.projectSpringVersion = projectSpringVersion;
    }

    public String getProjectBuildTool() {
        return projectBuildTool;
    }

    public void setProjectBuildTool(String projectBuildTool) {
        this.projectBuildTool = projectBuildTool;
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

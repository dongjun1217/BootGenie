package com.bootgenie.model;

import java.util.List;

public class ProjectRequest {

    private String projectType;
    private String language;
    private String packaging;
    private String javaVersion;
    private String springBootVersion;
    private String pattern;
    private String projectGroup;
    private String projectArtifact;
    private String projectName;
    private String projectPackageName;
    private List<Dependency> selectedDependencies;

    public static class Dependency {
        private int id;
        private String name;

        // Getters and setters

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public String getSpringBootVersion() {
        return springBootVersion;
    }

    public void setSpringBootVersion(String springBootVersion) {
        this.springBootVersion = springBootVersion;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProjectArtifact() {
        return projectArtifact;
    }

    public void setProjectArtifact(String projectArtifact) {
        this.projectArtifact = projectArtifact;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectPackageName() {
        return projectPackageName;
    }

    public void setProjectPackageName(String projectPackageName) {
        this.projectPackageName = projectPackageName;
    }

    public List<Dependency> getSelectedDependencies() {
        return selectedDependencies;
    }

    public void setSelectedDependencies(List<Dependency> selectedDependencies) {
        this.selectedDependencies = selectedDependencies;
    }
}

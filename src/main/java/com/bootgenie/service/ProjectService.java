package com.bootgenie.service;

import com.bootgenie.util.ProjectGenerator;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ProjectService {

    public ByteArrayInputStream generateProject(String projectName, String packageName, String pattern, String javaVersion, String springBootVersion, String packagingType, List<String> dependencies) throws IOException {
        return ProjectGenerator.generateProjectZip(projectName, packageName, pattern, javaVersion, springBootVersion, packagingType, dependencies);
    }
}

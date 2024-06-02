// src/main/java/com/bootgenie/service/ProjectService.java
package com.bootgenie.service;

import com.bootgenie.util.ProjectGenerator;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ProjectService {

    public ByteArrayInputStream generateProject(String projectName,String packageName,String pattern) throws IOException {
        return ProjectGenerator.generateProjectZip(projectName,packageName,pattern);
    }
}

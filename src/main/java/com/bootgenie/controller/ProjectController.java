package com.bootgenie.controller;

import com.bootgenie.model.ProjectRequest;
import com.bootgenie.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins="*", allowedHeaders = "*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/generate")
    public ResponseEntity<InputStreamResource> generateProject(@RequestBody ProjectRequest projectRequest) {
        try {
            List<String> dependencies = projectRequest.getSelectedDependencies()
                    .stream()
                    .map(ProjectRequest.Dependency::getName)
                    .collect(Collectors.toList());


            ByteArrayInputStream projectStream = projectService.generateProject(
                    projectRequest.getProjectName(),
                    projectRequest.getProjectPackageName(),
                    projectRequest.getPattern(),
                    projectRequest.getJavaVersion(),
                    projectRequest.getSpringBootVersion(),
                    projectRequest.getPackaging(),
                    dependencies
            );

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=" + projectRequest.getProjectName() + ".zip");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(projectStream));
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
}

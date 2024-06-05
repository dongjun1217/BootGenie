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

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/generate")
    public ResponseEntity<InputStreamResource> generateProject(@RequestBody ProjectRequest projectRequest) {
        try {
            ByteArrayInputStream projectStream = projectService.generateProject(
                    projectRequest.getProjectName(),
                    projectRequest.getPackageName(),
                    projectRequest.getPattern(),
                    projectRequest.getJavaVersion(),
                    projectRequest.getSpringBootVersion(),
                    projectRequest.getPackagingType(),
                    projectRequest.getDependencies()
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

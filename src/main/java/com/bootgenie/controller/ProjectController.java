package com.bootgenie.controller;

import com.bootgenie.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/generateProject")
    public ResponseEntity<InputStreamResource> generateProject(@RequestParam String projectName, @RequestParam String packageName,@RequestParam String pattern) throws IOException {
        ByteArrayInputStream zipFile = projectService.generateProject(projectName, packageName,pattern);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + projectName + ".zip");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(zipFile));
    }
}
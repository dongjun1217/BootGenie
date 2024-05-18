package com.bootgenie.controller;

import com.bootgenie.model.ProjectRequest;
import com.bootgenie.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/create-project")
    public ResponseEntity<byte[]> createProject(@RequestBody ProjectRequest request) {
        ByteArrayOutputStream byteArrayOutputStream = projectService.generateProject(request);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=project.zip");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/zip");

        return new ResponseEntity<>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.OK);
    }
}

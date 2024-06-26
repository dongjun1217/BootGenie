package com.bootgenie.controller;

import com.bootgenie.model.ProjectRequest;
import com.bootgenie.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(ProjectController.class);
    @Autowired
    private ProjectService projectService;

    @PostMapping("/generate")
    public ResponseEntity<InputStreamResource> generateProject(@RequestBody ProjectRequest projectRequest) {
        try {
            log.info(projectRequest.toString());
            List<String> dependencies = projectRequest.getSelectedDependencies()
                    .stream()
                    .map(ProjectRequest.Dependency::getName)
                    .collect(Collectors.toList());

            /*todo : projectGroup을 사용한 build.gradle에 작성해야할 내용있음.
                hasAdditionalRepositories() 어떤 디펜던시가 정확하게 저걸 호출하는 지 확인할 것.
            * */
            ByteArrayInputStream projectStream = projectService.generateProject(
                    projectRequest.getProjectName(),
                    projectRequest.getProjectPackageName(),
                    projectRequest.getProjectGroup(),
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

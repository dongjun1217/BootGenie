package com.bootgenie.service;

import com.bootgenie.model.ProjectRequest;
import com.bootgenie.util.ProjectGenerator;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class ProjectService {

    public ByteArrayOutputStream generateProject(ProjectRequest request) {
        return ProjectGenerator.generateProject(request);
    }
}

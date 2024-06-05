package com.bootgenie.app;

import com.bootgenie.model.ProjectRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BootGenieApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testGenerateProject() throws Exception {
		ProjectRequest projectRequest = new ProjectRequest();
		projectRequest.setProjectName("testProject");
		projectRequest.setPackageName("com.example");
		projectRequest.setPattern("default");
		projectRequest.setJavaVersion("17");
		projectRequest.setSpringBootVersion("2.5.4");
		projectRequest.setPackagingType("jar");
		projectRequest.setDependencies(Arrays.asList("lombok", "graphql-dgs-codegen", "graalvm-native-support"));

		mockMvc.perform(MockMvcRequestBuilders.post("/projects/generate")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(projectRequest)))
				.andExpect(status().isOk());
	}
}

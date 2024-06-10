package com.bootgenie.app;

import com.bootgenie.util.BuildGradleWriter;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BootGenieApplicationTests {

	@Test
	public void testWriteToDefaultBuildGradle() throws Exception {
		Path tempDir = Files.createTempDirectory("gradleTest");
		Path buildGradlePath = tempDir.resolve("build.gradle");

		BuildGradleWriter writer = new BuildGradleWriter();
		writer.writeTo(tempDir, "17", "2.7.5", "jar", Collections.emptyList());

		String content = Files.readString(buildGradlePath);

		assertTrue(content.contains("id 'java'"));
		assertTrue(content.contains("id 'org.springframework.boot' version '2.7.5'"));
		assertTrue(content.contains("id 'io.spring.dependency-management' version '1.1.4'"));
		assertTrue(content.contains("group = 'com.example'"));
		assertTrue(content.contains("version = '0.0.1-SNAPSHOT'"));
		assertTrue(content.contains("sourceCompatibility = '17'"));
		assertTrue(content.contains("mavenCentral()"));
		assertTrue(content.contains("implementation 'org.springframework.boot:spring-boot-starter'"));
		assertTrue(content.contains("testImplementation 'org.springframework.boot:spring-boot-starter-test'"));
		assertTrue(content.contains("testRuntimeOnly 'org.junit.platform:junit-platform-launcher'"));
		assertTrue(content.contains("useJUnitPlatform()"));
		assertTrue(!content.contains("ext {"));
	}

	@Test
	public void testWriteToBuildGradleWithSpringModulith() throws Exception {
		Path tempDir = Files.createTempDirectory("gradleTest");
		Path buildGradlePath = tempDir.resolve("build.gradle");

		BuildGradleWriter writer = new BuildGradleWriter();
		writer.writeTo(tempDir, "17", "2.7.5", "jar", Arrays.asList("Spring Modulith"));

		String content = Files.readString(buildGradlePath);

		assertTrue(content.contains("id 'java'"));
		assertTrue(content.contains("id 'org.springframework.boot' version '2.7.5'"));
		assertTrue(content.contains("id 'io.spring.dependency-management' version '1.1.4'"));
		assertTrue(content.contains("group = 'com.example'"));
		assertTrue(content.contains("version = '0.0.1-SNAPSHOT'"));
		assertTrue(content.contains("sourceCompatibility = '17'"));
		assertTrue(content.contains("mavenCentral()"));
		assertTrue(content.contains("implementation 'org.springframework.boot:spring-boot-starter'"));
		assertTrue(content.contains("testImplementation 'org.springframework.boot:spring-boot-starter-test'"));
		assertTrue(content.contains("testRuntimeOnly 'org.junit.platform:junit-platform-launcher'"));
		assertTrue(content.contains("useJUnitPlatform()"));
		assertTrue(content.contains("ext {"));
		assertTrue(content.contains("set('springModulithVersion', \"1.2.0\")"));
	}

	@Test
	public void testWriteToBuildGradleWithLombok() throws Exception {
		Path tempDir = Files.createTempDirectory("gradleTest");
		Path buildGradlePath = tempDir.resolve("build.gradle");

		BuildGradleWriter writer = new BuildGradleWriter();
		writer.writeTo(tempDir, "17", "2.7.5", "jar", Arrays.asList("Lombok"));

		String content = Files.readString(buildGradlePath);

		assertTrue(content.contains("id 'java'"));
		assertTrue(content.contains("id 'org.springframework.boot' version '2.7.5'"));
		assertTrue(content.contains("id 'io.spring.dependency-management' version '1.1.4'"));
		assertTrue(content.contains("group = 'com.example'"));
		assertTrue(content.contains("version = '0.0.1-SNAPSHOT'"));
		assertTrue(content.contains("sourceCompatibility = '17'"));
		assertTrue(content.contains("mavenCentral()"));
		assertTrue(content.contains("implementation 'org.springframework.boot:spring-boot-starter'"));
		assertTrue(content.contains("testImplementation 'org.springframework.boot:spring-boot-starter-test'"));
		assertTrue(content.contains("testRuntimeOnly 'org.junit.platform:junit-platform-launcher'"));
		assertTrue(content.contains("useJUnitPlatform()"));
		assertTrue(content.contains("compileOnly 'org.projectlombok:lombok'"));
		assertTrue(content.contains("annotationProcessor 'org.projectlombok:lombok'"));
		assertTrue(content.contains("configurations {"));
		assertTrue(content.contains("compileOnly {"));
		assertTrue(content.contains("extendsFrom annotationProcessor"));
	}

	@Test
	public void testWriteToBuildGradleWithMultipleDependencies() throws Exception {
		Path tempDir = Files.createTempDirectory("gradleTest");
		Path buildGradlePath = tempDir.resolve("build.gradle");

		BuildGradleWriter writer = new BuildGradleWriter();
		writer.writeTo(tempDir, "17", "2.7.5", "jar", Arrays.asList("Lombok", "Spring Modulith", "GraphQL DGS Code Generation"));

		String content = Files.readString(buildGradlePath);

		assertTrue(content.contains("id 'java'"));
		assertTrue(content.contains("id 'org.springframework.boot' version '2.7.5'"));
		assertTrue(content.contains("id 'io.spring.dependency-management' version '1.1.4'"));
		assertTrue(content.contains("id 'com.netflix.dgs.codegen' version '6.2.1'"));
		assertTrue(content.contains("group = 'com.example'"));
		assertTrue(content.contains("version = '0.0.1-SNAPSHOT'"));
		assertTrue(content.contains("sourceCompatibility = '17'"));
		assertTrue(content.contains("mavenCentral()"));
		assertTrue(content.contains("implementation 'org.springframework.boot:spring-boot-starter'"));
		assertTrue(content.contains("testImplementation 'org.springframework.boot:spring-boot-starter-test'"));
		assertTrue(content.contains("testRuntimeOnly 'org.junit.platform:junit-platform-launcher'"));
		assertTrue(content.contains("useJUnitPlatform()"));
		assertTrue(content.contains("compileOnly 'org.projectlombok:lombok'"));
		assertTrue(content.contains("annotationProcessor 'org.projectlombok:lombok'"));
		assertTrue(content.contains("configurations {"));
		assertTrue(content.contains("compileOnly {"));
		assertTrue(content.contains("extendsFrom annotationProcessor"));
		assertTrue(content.contains("ext {"));
		assertTrue(content.contains("set('springModulithVersion', \"1.2.0\")"));
		assertTrue(content.contains("generateJava {"));
		assertTrue(content.contains("schemaPaths = [\"${projectDir}/src/main/resources/graphql-client\"]"));
		assertTrue(content.contains("packageName = 'com.example.demo.codegen'"));
		assertTrue(content.contains("generateClient = true"));
	}

	@Test
	public void testWriteToBuildGradleWithGraalVMNativeSupport() throws Exception {
		Path tempDir = Files.createTempDirectory("gradleTest");
		Path buildGradlePath = tempDir.resolve("build.gradle");

		BuildGradleWriter writer = new BuildGradleWriter();
		writer.writeTo(tempDir, "17", "2.7.5", "jar", Arrays.asList("GraalVM Native Support"));

		String content = Files.readString(buildGradlePath);

		assertTrue(content.contains("id 'java'"));
		assertTrue(content.contains("id 'org.springframework.boot' version '2.7.5'"));
		assertTrue(content.contains("id 'io.spring.dependency-management' version '1.1.4'"));
		assertTrue(content.contains("id 'org.graalvm.buildtools.native' version '0.10.2'"));
		assertTrue(content.contains("group = 'com.example'"));
		assertTrue(content.contains("version = '0.0.1-SNAPSHOT'"));
		assertTrue(content.contains("sourceCompatibility = '17'"));
		assertTrue(content.contains("mavenCentral()"));
		assertTrue(content.contains("implementation 'org.springframework.boot:spring-boot-starter'"));
		assertTrue(content.contains("testImplementation 'org.springframework.boot:spring-boot-starter-test'"));
		assertTrue(content.contains("testRuntimeOnly 'org.junit.platform:junit-platform-launcher'"));
		assertTrue(content.contains("useJUnitPlatform()"));
		assertTrue(content.contains("implementation 'org.graalvm.nativeimage:svm'"));
	}
}

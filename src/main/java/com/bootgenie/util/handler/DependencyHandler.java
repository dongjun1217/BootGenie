package com.bootgenie.util.handler;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DependencyHandler {

    private final List<String> dependencies;
    private static final Map<String, String> PLUGIN_MAP = Map.of(
            "GraphQL DGS Code Generation", "id 'com.netflix.dgs.codegen' version '6.2.1'",
            "GraalVM Native Support", "id 'org.graalvm.buildtools.native' version '0.10.2'",
            "Hibernate ORM", "id 'org.hibernate.orm' version '6.5.2.Final'",
            "Cyclonedx BOM", "id 'org.cyclonedx.bom' version '1.8.2'",
            "Spring Cloud Contract", "id 'org.springframework.cloud.contract' version '4.1.3'",
            "Asciidoctor", "id 'org.asciidoctor.jvm.convert' version '3.3.2'",
            "Vaadin", "id 'com.vaadin' version '24.3.13'"
    );

    private static final Map<String, String> EXT_MAP = Map.of(
            "Spring Modulith", "set('springModulithVersion', \"1.2.0\")",
            "GraphQL DGS Code Generation", "set('netflixDgsVersion', \"9.0.0\")",
            "Spring AI", "set('springAiVersion', \"1.0.0-M1\")",
            "Spring Cloud Azure", "set('springCloudAzureVersion', \"5.13.0\")",
            "Spring Cloud", "set('springCloudVersion', \"2023.0.2\")",
            "Spring Shell", "set('springShellVersion', \"3.3.0\")",
            "Timefold Solver", "set('timefoldSolverVersion', \"1.10.0\")",
            "Vaadin", "set('vaadinVersion', \"24.3.13\")"
    );

    private static final Map<String, String> DEPENDENCY_MAP = Map.ofEntries(
            Map.entry("Lombok", "compileOnly 'org.projectlombok:lombok'\nannotationProcessor 'org.projectlombok:lombok'"),
            Map.entry("Spring Configuration Processor", "annotationProcessor 'org.springframework.boot:spring-boot-configuration-processor'"),
            Map.entry("GraphQL DGS Code Generation", "implementation 'com.netflix.graphql.dgs:graphql-dgs-spring-graphql-starter'"),
            Map.entry("GraalVM Native Support", "implementation 'org.graalvm.nativeimage:svm'"),
            Map.entry("Spring Boot DevTools", "developmentOnly 'org.springframework.boot:spring-boot-devtools'"),
            Map.entry("Docker Compose Support", "developmentOnly 'org.springframework.boot:spring-boot-docker-compose'"),
            Map.entry("Vaadin", "implementation 'com.vaadin:vaadin-spring-boot-starter'"),
            Map.entry("Spring Boot Starter Activemq", "implementation 'org.springframework.boot:spring-boot-starter-activemq'"),
            Map.entry("Spring Boot Starter Actuator", "implementation 'org.springframework.boot:spring-boot-starter-actuator'"),
            Map.entry("Spring Boot Starter Amqp", "implementation 'org.springframework.boot:spring-boot-starter-amqp'"),
            Map.entry("Spring Boot Starter Artemis", "implementation 'org.springframework.boot:spring-boot-starter-artemis'"),
            Map.entry("Spring Boot Starter Batch", "implementation 'org.springframework.boot:spring-boot-starter-batch'"),
            Map.entry("Spring Boot Starter Cache", "implementation 'org.springframework.boot:spring-boot-starter-cache'"),
            Map.entry("Spring Boot Starter Data Cassandra", "implementation 'org.springframework.boot:spring-boot-starter-data-cassandra'"),
            Map.entry("Spring Boot Starter Data Cassandra Reactive", "implementation 'org.springframework.boot:spring-boot-starter-data-cassandra-reactive'"),
            Map.entry("Spring Boot Starter Data Couchbase", "implementation 'org.springframework.boot:spring-boot-starter-data-couchbase'"),
            Map.entry("Spring Boot Starter Data Couchbase Reactive", "implementation 'org.springframework.boot:spring-boot-starter-data-couchbase-reactive'"),
            Map.entry("Spring Boot Starter Data Elasticsearch", "implementation 'org.springframework.boot:spring-boot-starter-data-elasticsearch'"),
            Map.entry("Spring Boot Starter Data Jdbc", "implementation 'org.springframework.boot:spring-boot-starter-data-jdbc'"),
            Map.entry("Spring Boot Starter Data Jpa", "implementation 'org.springframework.boot:spring-boot-starter-data-jpa'"),
            Map.entry("Spring Boot Starter Data Ldap", "implementation 'org.springframework.boot:spring-boot-starter-data-ldap'"),
            Map.entry("Spring Boot Starter Data Mongodb", "implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'"),
            Map.entry("Spring Boot Starter Data Mongodb Reactive", "implementation 'org.springframework.boot:spring-boot-starter-data-mongodb-reactive'"),
            Map.entry("Spring Boot Starter Data Neo4j", "implementation 'org.springframework.boot:spring-boot-starter-data-neo4j'"),
            Map.entry("Spring Boot Starter Data R2dbc", "implementation 'org.springframework.boot:spring-boot-starter-data-r2dbc'"),
            Map.entry("Spring Boot Starter Data Redis", "implementation 'org.springframework.boot:spring-boot-starter-data-redis'"),
            Map.entry("Spring Boot Starter Data Redis Reactive", "implementation 'org.springframework.boot:spring-boot-starter-data-redis-reactive'"),
            Map.entry("Spring Boot Starter Data Rest", "implementation 'org.springframework.boot:spring-boot-starter-data-rest'"),
            Map.entry("Spring Boot Starter Freemarker", "implementation 'org.springframework.boot:spring-boot-starter-freemarker'"),
            Map.entry("Spring Boot Starter Graphql", "implementation 'org.springframework.boot:spring-boot-starter-graphql'"),
            Map.entry("Spring Boot Starter Groovy Templates", "implementation 'org.springframework.boot:spring-boot-starter-groovy-templates'"),
            Map.entry("Spring Boot Starter Hateoas", "implementation 'org.springframework.boot:spring-boot-starter-hateoas'"),
            Map.entry("Spring Boot Starter Integration", "implementation 'org.springframework.boot:spring-boot-starter-integration'"),
            Map.entry("Spring Boot Starter Jdbc", "implementation 'org.springframework.boot:spring-boot-starter-jdbc'"),
            Map.entry("Spring Boot Starter Jersey", "implementation 'org.springframework.boot:spring-boot-starter-jersey'"),
            Map.entry("Spring Boot Starter Jooq", "implementation 'org.springframework.boot:spring-boot-starter-jooq'"),
            Map.entry("Spring Boot Starter Mail", "implementation 'org.springframework.boot:spring-boot-starter-mail'"),
            Map.entry("Spring Boot Starter Mustache", "implementation 'org.springframework.boot:spring-boot-starter-mustache'"),
            Map.entry("Spring Boot Starter Oauth2 Authorization Server", "implementation 'org.springframework.boot:spring-boot-starter-oauth2-authorization-server'"),
            Map.entry("Spring Boot Starter Oauth2 Client", "implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'"),
            Map.entry("Spring Boot Starter Oauth2 Resource Server", "implementation 'org.springframework.boot:spring-boot-starter-oauth2-resource-server'"),
            Map.entry("Spring Boot Starter Pulsar", "implementation 'org.springframework.boot:spring-boot-starter-pulsar'"),
            Map.entry("Spring Boot Starter Pulsar Reactive", "implementation 'org.springframework.boot:spring-boot-starter-pulsar-reactive'"),
            Map.entry("Spring Boot Starter Quartz", "implementation 'org.springframework.boot:spring-boot-starter-quartz'"),
            Map.entry("Spring Boot Starter Rsocket", "implementation 'org.springframework.boot:spring-boot-starter-rsocket'"),
            Map.entry("Spring Boot Starter Security", "implementation 'org.springframework.boot:spring-boot-starter-security'"),
            Map.entry("Spring Boot Starter Thymeleaf", "implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'"),
            Map.entry("Spring Boot Starter Validation", "implementation 'org.springframework.boot:spring-boot-starter-validation'"),
            Map.entry("Spring Boot Starter Web", "implementation 'org.springframework.boot:spring-boot-starter-web'"),
            Map.entry("Spring Boot Starter Web Services", "implementation 'org.springframework.boot:spring-boot-starter-web-services'"),
            Map.entry("Spring Boot Starter Webflux", "implementation 'org.springframework.boot:spring-boot-starter-webflux'"),
            Map.entry("Spring Boot Starter Websocket", "implementation 'org.springframework.boot:spring-boot-starter-websocket'"),
            Map.entry("Timefold Solver", "implementation 'ai.timefold.solver:timefold-solver-spring-boot-starter'"),
            Map.entry("Spring Cloud Azure Starter", "implementation 'com.azure.spring:spring-cloud-azure-starter'"),
            Map.entry("Spring Cloud Azure Starter Active Directory", "implementation 'com.azure.spring:spring-cloud-azure-starter-active-directory'"),
            Map.entry("Spring Cloud Azure Starter Actuator", "implementation 'com.azure.spring:spring-cloud-azure-starter-actuator'"),
            Map.entry("Spring Cloud Azure Starter Data Cosmos", "implementation 'com.azure.spring:spring-cloud-azure-starter-data-cosmos'"),
            Map.entry("Spring Cloud Azure Starter Integration Storage Queue", "implementation 'com.azure.spring:spring-cloud-azure-starter-integration-storage-queue'"),
            Map.entry("Spring Cloud Azure Starter Jdbc Mysql", "implementation 'com.azure.spring:spring-cloud-azure-starter-jdbc-mysql'"),
            Map.entry("Spring Cloud Azure Starter Jdbc Postgresql", "implementation 'com.azure.spring:spring-cloud-azure-starter-jdbc-postgresql'"),
            Map.entry("Spring Cloud Azure Starter Keyvault", "implementation 'com.azure.spring:spring-cloud-azure-starter-keyvault'"),
            Map.entry("Spring Cloud Azure Starter Storage", "implementation 'com.azure.spring:spring-cloud-azure-starter-storage'"),
            Map.entry("Micrometer Wavefront", "implementation 'io.micrometer:micrometer-registry-wavefront'\nruntimeOnly 'io.micrometer:micrometer-tracing-reporter-wavefront'"),
            Map.entry("Micrometer Brave", "implementation 'io.micrometer:micrometer-tracing-bridge-brave'"),
            Map.entry("Zipkin Reporter Brave", "implementation 'io.zipkin.reporter2:zipkin-reporter-brave'"),
            Map.entry("Kafka Streams", "implementation 'org.apache.kafka:kafka-streams'"),
            Map.entry("Flyway Core", "implementation 'org.flywaydb:flyway-core'"),
            Map.entry("Flyway Db2", "implementation 'org.flywaydb:flyway-database-db2'"),
            Map.entry("Flyway Derby", "implementation 'org.flywaydb:flyway-database-derby'"),
            Map.entry("Flyway Hsqldb", "implementation 'org.flywaydb:flyway-database-hsqldb'"),
            Map.entry("Flyway Oracle", "implementation 'org.flywaydb:flyway-database-oracle'"),
            Map.entry("Flyway Postgresql", "implementation 'org.flywaydb:flyway-database-postgresql'"),
            Map.entry("Flyway Mysql", "implementation 'org.flywaydb:flyway-mysql'"),
            Map.entry("Flyway Sqlserver", "implementation 'org.flywaydb:flyway-sqlserver'"),
            Map.entry("Liquibase Core", "implementation 'org.liquibase:liquibase-core'"),
            Map.entry("Spring AI Azure Openai", "implementation 'org.springframework.ai:spring-ai-azure-openai-spring-boot-starter'"),
            Map.entry("Spring AI Azure Store", "implementation 'org.springframework.ai:spring-ai-azure-store-spring-boot-starter'"),
            Map.entry("Spring AI Bedrock AI", "implementation 'org.springframework.ai:spring-ai-bedrock-ai-spring-boot-starter'"),
            Map.entry("Spring AI Chroma Store", "implementation 'org.springframework.ai:spring-ai-chroma-store-spring-boot-starter'"),
            Map.entry("Spring AI Milvus Store", "implementation 'org.springframework.ai:spring-ai-milvus-store-spring-boot-starter'"),
            Map.entry("Spring AI Mistral AI", "implementation 'org.springframework.ai:spring-ai-mistral-ai-spring-boot-starter'"),
            Map.entry("Spring AI Neo4j Store", "implementation 'org.springframework.ai:spring-ai-neo4j-store-spring-boot-starter'"),
            Map.entry("Spring AI Ollama", "implementation 'org.springframework.ai:spring-ai-ollama-spring-boot-starter'"),
            Map.entry("Spring AI Openai", "implementation 'org.springframework.ai:spring-ai-openai-spring-boot-starter'"),
            Map.entry("Spring AI Pgvector Store", "implementation 'org.springframework.ai:spring-ai-pgvector-store-spring-boot-starter'"),
            Map.entry("Spring AI Pinecone Store", "implementation 'org.springframework.ai:spring-ai-pinecone-store-spring-boot-starter'"),
            Map.entry("Spring AI PostgresML", "implementation 'org.springframework.ai:spring-ai-postgresml-spring-boot-starter'"),
            Map.entry("Spring AI Qdrant Store", "implementation 'org.springframework.ai:spring-ai-qdrant-store-spring-boot-starter'"),
            Map.entry("Spring AI Redis Store", "implementation 'org.springframework.ai:spring-ai-redis-store-spring-boot-starter'"),
            Map.entry("Spring AI Stability AI", "implementation 'org.springframework.ai:spring-ai-stability-ai-spring-boot-starter'"),
            Map.entry("Spring AI Transformers", "implementation 'org.springframework.ai:spring-ai-transformers-spring-boot-starter'"),
            Map.entry("Spring AI Vertex AI Gemini", "implementation 'org.springframework.ai:spring-ai-vertex-ai-gemini-spring-boot-starter'"),
            Map.entry("Spring AI Vertex AI Palm2", "implementation 'org.springframework.ai:spring-ai-vertex-ai-palm2-spring-boot-starter'"),
            Map.entry("Spring AI Weaviate Store", "implementation 'org.springframework.ai:spring-ai-weaviate-store-spring-boot-starter'"),
            Map.entry("Spring Rabbit Stream", "implementation 'org.springframework.amqp:spring-rabbit-stream'"),
            Map.entry("Spring Cloud Bus", "implementation 'org.springframework.cloud:spring-cloud-bus'"),
            Map.entry("Spring Cloud Config Server", "implementation 'org.springframework.cloud:spring-cloud-config-server'"),
            Map.entry("Spring Cloud Function Web", "implementation 'org.springframework.cloud:spring-cloud-function-web'"),
            Map.entry("Spring Cloud Starter", "implementation 'org.springframework.cloud:spring-cloud-starter'"),
            Map.entry("Spring Cloud Circuitbreaker Resilience4j", "implementation 'org.springframework.cloud:spring-cloud-starter-circuitbreaker-reactor-resilience4j'"),
            Map.entry("Spring Cloud Config", "implementation 'org.springframework.cloud:spring-cloud-starter-config'"),
            Map.entry("Spring Cloud Consul Config", "implementation 'org.springframework.cloud:spring-cloud-starter-consul-config'"),
            Map.entry("Spring Cloud Consul Discovery", "implementation 'org.springframework.cloud:spring-cloud-starter-consul-discovery'"),
            Map.entry("Spring Cloud Gateway", "implementation 'org.springframework.cloud:spring-cloud-starter-gateway'"),
            Map.entry("Spring Cloud Gateway Mvc", "implementation 'org.springframework.cloud:spring-cloud-starter-gateway-mvc'"),
            Map.entry("Spring Cloud Loadbalancer", "implementation 'org.springframework.cloud:spring-cloud-starter-loadbalancer'"),
            Map.entry("Spring Cloud Netflix Eureka Client", "implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'"),
            Map.entry("Spring Cloud Netflix Eureka Server", "implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-server'"),
            Map.entry("Spring Cloud Openfeign", "implementation 'org.springframework.cloud:spring-cloud-starter-openfeign'"),
            Map.entry("Spring Cloud Task", "implementation 'org.springframework.cloud:spring-cloud-starter-task'"),
            Map.entry("Spring Cloud Vault Config", "implementation 'org.springframework.cloud:spring-cloud-starter-vault-config'"),
            Map.entry("Spring Cloud Zookeeper Config", "implementation 'org.springframework.cloud:spring-cloud-starter-zookeeper-config'"),
            Map.entry("Spring Cloud Zookeeper Discovery", "implementation 'org.springframework.cloud:spring-cloud-starter-zookeeper-discovery'"),
            Map.entry("Spring Cloud Stream", "implementation 'org.springframework.cloud:spring-cloud-stream'"),
            Map.entry("Spring Cloud Stream Binder Kafka", "implementation 'org.springframework.cloud:spring-cloud-stream-binder-kafka'"),
            Map.entry("Spring Cloud Stream Binder Kafka Streams", "implementation 'org.springframework.cloud:spring-cloud-stream-binder-kafka-streams'"),
            Map.entry("Spring Cloud Stream Binder Pulsar", "implementation 'org.springframework.cloud:spring-cloud-stream-binder-pulsar'"),
            Map.entry("Spring Cloud Stream Binder Rabbit", "implementation 'org.springframework.cloud:spring-cloud-stream-binder-rabbit'"),
            Map.entry("Spring Data Rest HAL Explorer", "implementation 'org.springframework.data:spring-data-rest-hal-explorer'"),
            Map.entry("Spring Integration Amqp", "implementation 'org.springframework.integration:spring-integration-amqp'"),
            Map.entry("Spring Integration Http", "implementation 'org.springframework.integration:spring-integration-http'"),
            Map.entry("Spring Integration Jdbc", "implementation 'org.springframework.integration:spring-integration-jdbc'"),
            Map.entry("Spring Integration Jms", "implementation 'org.springframework.integration:spring-integration-jms'"),
            Map.entry("Spring Integration Jpa", "implementation 'org.springframework.integration:spring-integration-jpa'"),
            Map.entry("Spring Integration Kafka", "implementation 'org.springframework.integration:spring-integration-kafka'"),
            Map.entry("Spring Integration Mail", "implementation 'org.springframework.integration:spring-integration-mail'"),
            Map.entry("Spring Integration Mongodb", "implementation 'org.springframework.integration:spring-integration-mongodb'"),
            Map.entry("Spring Integration R2dbc", "implementation 'org.springframework.integration:spring-integration-r2dbc'"),
            Map.entry("Spring Integration Redis", "implementation 'org.springframework.integration:spring-integration-redis'"),
            Map.entry("Spring Integration Rsocket", "implementation 'org.springframework.integration:spring-integration-rsocket'"),
            Map.entry("Spring Integration Security", "implementation 'org.springframework.integration:spring-integration-security'"),
            Map.entry("Spring Integration Stomp", "implementation 'org.springframework.integration:spring-integration-stomp'"),
            Map.entry("Spring Integration Webflux", "implementation 'org.springframework.integration:spring-integration-webflux'"),
            Map.entry("Spring Integration Websocket", "implementation 'org.springframework.integration:spring-integration-websocket'"),
            Map.entry("Spring Integration Ws", "implementation 'org.springframework.integration:spring-integration-ws'"),
            Map.entry("Spring Kafka", "implementation 'org.springframework.kafka:spring-kafka'"),
            Map.entry("Spring Modulith Core", "implementation 'org.springframework.modulith:spring-modulith-starter-core'"),
            Map.entry("Spring Modulith Jdbc", "implementation 'org.springframework.modulith:spring-modulith-starter-jdbc'"),
            Map.entry("Spring Modulith Jpa", "implementation 'org.springframework.modulith:spring-modulith-starter-jpa'"),
            Map.entry("Spring Modulith Mongodb", "implementation 'org.springframework.modulith:spring-modulith-starter-mongodb'"),
            Map.entry("Spring Security Messaging", "implementation 'org.springframework.security:spring-security-messaging'"),
            Map.entry("Spring Security Rsocket", "implementation 'org.springframework.security:spring-security-rsocket'"),
            Map.entry("Spring Session Data Redis", "implementation 'org.springframework.session:spring-session-data-redis'"),
            Map.entry("Spring Session Jdbc", "implementation 'org.springframework.session:spring-session-jdbc'"),
            Map.entry("Spring Shell Starter", "implementation 'org.springframework.shell:spring-shell-starter'"),
            Map.entry("Thymeleaf Extras Spring Security6", "implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity6'"),
            Map.entry("Spring Boot Devtools", "developmentOnly 'org.springframework.boot:spring-boot-devtools'"),
            Map.entry("Spring Boot Docker Compose", "developmentOnly 'org.springframework.boot:spring-boot-docker-compose'"),
            Map.entry("H2 Database", "runtimeOnly 'com.h2database:h2'"),
            Map.entry("IBM DB2", "runtimeOnly 'com.ibm.db2:jcc'"),
            Map.entry("Microsoft SQL Server", "runtimeOnly 'com.microsoft.sqlserver:mssql-jdbc'"),
            Map.entry("MySQL Connector J", "runtimeOnly 'com.mysql:mysql-connector-j'"),
            Map.entry("Oracle Database JDBC", "runtimeOnly 'com.oracle.database.jdbc:ojdbc11'"),
            Map.entry("Oracle Database R2DBC", "runtimeOnly 'com.oracle.database.r2dbc:oracle-r2dbc'"),
            Map.entry("R2DBC MySQL", "runtimeOnly 'io.asyncer:r2dbc-mysql'"),
            Map.entry("Micrometer Datadog", "runtimeOnly 'io.micrometer:micrometer-registry-datadog'"),
            Map.entry("Micrometer Dynatrace", "runtimeOnly 'io.micrometer:micrometer-registry-dynatrace'"),
            Map.entry("Micrometer Graphite", "runtimeOnly 'io.micrometer:micrometer-registry-graphite'"),
            Map.entry("Micrometer Influx", "runtimeOnly 'io.micrometer:micrometer-registry-influx'"),
            Map.entry("Micrometer New Relic", "runtimeOnly 'io.micrometer:micrometer-registry-new-relic'"),
            Map.entry("Micrometer Prometheus", "runtimeOnly 'io.micrometer:micrometer-registry-prometheus'"),
            Map.entry("R2DBC H2", "runtimeOnly 'io.r2dbc:r2dbc-h2'"),
            Map.entry("R2DBC MSSQL", "runtimeOnly 'io.r2dbc:r2dbc-mssql:1.0.0.RELEASE'"),
            Map.entry("Apache Derby", "runtimeOnly 'org.apache.derby:derby'"),
            Map.entry("Apache Derby Tools", "runtimeOnly 'org.apache.derby:derbytools'"),
            Map.entry("HSQLDB", "runtimeOnly 'org.hsqldb:hsqldb'"),
            Map.entry("R2DBC MariaDB", "runtimeOnly 'org.mariadb:r2dbc-mariadb:1.1.3'"),
            Map.entry("MariaDB Java Client", "runtimeOnly 'org.mariadb.jdbc:mariadb-java-client'"),
            Map.entry("PostgreSQL", "runtimeOnly 'org.postgresql:postgresql'"),
            Map.entry("R2DBC PostgreSQL", "runtimeOnly 'org.postgresql:r2dbc-postgresql'"),
            Map.entry("Spring Modulith Actuator", "runtimeOnly 'org.springframework.modulith:spring-modulith-actuator'"),
            Map.entry("Spring Modulith Observability", "runtimeOnly 'org.springframework.modulith:spring-modulith-observability'"),
            Map.entry("JUnit Platform Launcher", "testRuntimeOnly 'org.junit.platform:junit-platform-launcher'"),
            Map.entry("UnboundID LDAP SDK", "testImplementation 'com.unboundid:unboundid-ldapsdk'"),
            Map.entry("Reactor Test", "testImplementation 'io.projectreactor:reactor-test'"),
            Map.entry("Rest Assured Spring Web Test Client", "testImplementation 'io.rest-assured:spring-web-test-client'"),
            Map.entry("Spring Rabbit Test", "testImplementation 'org.springframework.amqp:spring-rabbit-test'"),
            Map.entry("Spring Batch Test", "testImplementation 'org.springframework.batch:spring-batch-test'"),
            Map.entry("Spring Cloud Contract Stub Runner", "testImplementation 'org.springframework.cloud:spring-cloud-starter-contract-stub-runner'"),
            Map.entry("Spring Cloud Contract Verifier", "testImplementation 'org.springframework.cloud:spring-cloud-starter-contract-verifier'"),
            Map.entry("Spring Cloud Stream Test Binder", "testImplementation 'org.springframework.cloud:spring-cloud-stream-test-binder'"),
            Map.entry("Spring Graphql Test", "testImplementation 'org.springframework.graphql:spring-graphql-test'"),
            Map.entry("Spring Integration Test", "testImplementation 'org.springframework.integration:spring-integration-test'"),
            Map.entry("Spring Kafka Test", "testImplementation 'org.springframework.kafka:spring-kafka-test'"),
            Map.entry("Spring Modulith Test", "testImplementation 'org.springframework.modulith:spring-modulith-starter-test'"),
            Map.entry("Spring Restdocs Mockmvc", "testImplementation 'org.springframework.restdocs:spring-restdocs-mockmvc'"),
            Map.entry("Spring Security Test", "testImplementation 'org.springframework.security:spring-security-test'"),
            Map.entry("Spring Shell Test", "testImplementation 'org.springframework.shell:spring-shell-starter-test'"),
            Map.entry("Testcontainers Activemq", "testImplementation 'org.testcontainers:activemq'"),
            Map.entry("Testcontainers Cassandra", "testImplementation 'org.testcontainers:cassandra'"),
            Map.entry("Testcontainers Consul", "testImplementation 'org.testcontainers:consul'"),
            Map.entry("Testcontainers Couchbase", "testImplementation 'org.testcontainers:couchbase'"),
            Map.entry("Testcontainers Db2", "testImplementation 'org.testcontainers:db2'"),
            Map.entry("Testcontainers Elasticsearch", "testImplementation 'org.testcontainers:elasticsearch'"),
            Map.entry("Testcontainers Junit Jupiter", "testImplementation 'org.testcontainers:junit-jupiter'"),
            Map.entry("Testcontainers Kafka", "testImplementation 'org.testcontainers:kafka'"),
            Map.entry("Testcontainers Mariadb", "testImplementation 'org.testcontainers:mariadb'"),
            Map.entry("Testcontainers Mongodb", "testImplementation 'org.testcontainers:mongodb'"),
            Map.entry("Testcontainers Mssqlserver", "testImplementation 'org.testcontainers:mssqlserver'"),
            Map.entry("Testcontainers Mysql", "testImplementation 'org.testcontainers:mysql'"),
            Map.entry("Testcontainers Neo4j", "testImplementation 'org.testcontainers:neo4j'"),
            Map.entry("Testcontainers Oracle Free", "testImplementation 'org.testcontainers:oracle-free'"),
            Map.entry("Testcontainers Postgresql", "testImplementation 'org.testcontainers:postgresql'"),
            Map.entry("Testcontainers Pulsar", "testImplementation 'org.testcontainers:pulsar'"),
            Map.entry("Testcontainers R2dbc", "testImplementation 'org.testcontainers:r2dbc'"),
            Map.entry("Testcontainers Rabbitmq", "testImplementation 'org.testcontainers:rabbitmq'"),
            Map.entry("Testcontainers Vault", "testImplementation 'org.testcontainers:vault'")
    );

    private static final Map<String, String> CONFIGURATION_MAP = Map.of(
            "Lombok", "configurations {\n  compileOnly {\n    extendsFrom annotationProcessor\n  }\n}"
    );

    private static final Map<String, String> GENERATE_JAVA_MAP = Map.of(
            "GraphQL DGS Code Generation", "generateJava {\n  schemaPaths = [\"${projectDir}/src/main/resources/graphql-client\"]\n  packageName = 'com.example.demo.codegen'\n  generateClient = true\n}"
    );

    public DependencyHandler(List<String> dependencies) {
        this.dependencies = dependencies;
    }

    public String getPlugins() {
        return dependencies.stream()
                .map(PLUGIN_MAP::get)
                .filter(Objects::nonNull)
                .collect(Collectors.joining("\n"));
    }

    public String getExt() {
        return dependencies.stream()
                .map(EXT_MAP::get)
                .filter(Objects::nonNull)
                .collect(Collectors.joining("\n"));
    }

    public boolean hasExt() {
        return dependencies.stream().anyMatch(EXT_MAP::containsKey);
    }

    public boolean hasAdditionalRepositories() {
        return dependencies.stream().anyMatch(dependency ->
                dependency.equals("GraphQL DGS Code Generation") || dependency.equals("Vaadin"));
    }


    public String getDependencies() {
        return dependencies.stream()
                .map(DEPENDENCY_MAP::get)
                .filter(Objects::nonNull)
                .collect(Collectors.joining("\n"));
    }

    public String getConfigurations() {
        return dependencies.stream()
                .map(CONFIGURATION_MAP::get)
                .filter(Objects::nonNull)
                .collect(Collectors.joining("\n"));
    }

    public String getGenerateJava() {
        return dependencies.stream()
                .map(GENERATE_JAVA_MAP::get)
                .filter(Objects::nonNull)
                .collect(Collectors.joining("\n"));
    }
}


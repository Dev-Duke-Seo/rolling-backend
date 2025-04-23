package com.rolling.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

        @Value("${rolling.openapi.dev-url}")
        private String devUrl;

        @Value("${rolling.openapi.prod-url}")
        private String prodUrl;

        @Bean
        public GroupedOpenApi recipientApi() {
                return GroupedOpenApi.builder().group("recipient-api")
                                .pathsToMatch("/recipients/**")
                                .packagesToScan("com.rolling.controller").build();
        }

        @Bean
        public GroupedOpenApi publicApi() {
                return GroupedOpenApi.builder().group("public-api").pathsToMatch("/public/**")
                                .packagesToScan("com.rolling.controller").build();
        }

        @Bean
        public GroupedOpenApi allApis() {
                return GroupedOpenApi.builder().group("all-apis").pathsToMatch("/**")
                                .packagesToScan("com.rolling.controller").build();
        }

        @Bean
        public OpenAPI openAPI() {
                Server devServer = new Server();
                devServer.setUrl(devUrl);
                devServer.setDescription("개발 서버");

                Server prodServer = new Server();
                prodServer.setUrl(prodUrl);
                prodServer.setDescription("운영 서버");

                // 태그 정의 추가
                List<Tag> tags = Arrays.asList(
                                createTag("Recipient", "메시지를 수신할 주체(recipient)를 위한 API"),
                                createTag("Public", "공개 API"));

                return new OpenAPI().info(new Info().title("Rolling API 문서").version("v1.0.0")
                                .description("Rolling 프로젝트의 API 문서입니다.")
                                .contact(new Contact().name("Rolling Project")
                                                .email("dev.duke.seo@gmail.com")
                                                .url("https://rolling-production.vercel.app/"))
                                .license(new License().name("MIT License")
                                                .url("https://opensource.org/licenses/MIT")))
                                .externalDocs(new ExternalDocumentation()
                                                .description("Rolling GitHub")
                                                .url("https://github.com/users/Dev-Duke-Seo/projects/3"))
                                .servers(Arrays.asList(devServer, prodServer)).tags(tags);
        }

        private Tag createTag(String name, String description) {
                Tag tag = new Tag();
                tag.setName(name);
                tag.setDescription(description);
                return tag;
        }
}

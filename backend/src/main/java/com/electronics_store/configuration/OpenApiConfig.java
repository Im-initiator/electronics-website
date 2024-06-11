package com.electronics_store.configuration;

import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    /**
     * sử dụng @Tag(name) để  đặt tên cho API
     * sử dụng @Operation(summary="",description="") để đặt tên cho mỗi API và mô tả cho mỗi API
     */
    @Bean
    public OpenAPI openAPI(
            @Value("${application.doc.version}") String docVersion,
            @Value("${application.doc.title}") String title,
            @Value("${application.doc.description}") String docDescription,
            @Value("${application.doc.license.name}") String docLicense,
            @Value("${application.doc.license.url}") String docLicenseUrl,
            @Value("${application.doc.server.url}") String docServerUrl,
            @Value("${application.doc.server.description}") String docServerDescription) {

        return new OpenAPI()
                .info(new Info()
                        .title(title) // tile của api
                        .version(docVersion) // version của api
                        .description(docDescription) // mô tả api
                        .license(new License().name(docLicense).url(docLicenseUrl))) // thông tin giấy phep sử dụng
                // thông tin  về các server được triển khai
                .servers(List.of(new Server().url(docServerUrl).description(docServerDescription)))
                // thiết lập nơi auto gửi token vào header
                .components(
                        new Components() // thiết  lập phần được sử dụng trong api
                                // định nghĩa  một loại xác thực kiểu bearer với tên  là bearerAuth
                                .addSecuritySchemes(
                                        "bearerAuth", // name  hiển thị
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP) // bảo mật
                                                .scheme("bearer") // kiểu bảo mật, chuỗi  được quy định trớc
                                                .bearerFormat("JWT") // định dạng, chuoi này cũng được quy định trước
                                                .in(SecurityScheme.In.HEADER) // vị trí
                                                .name("Authorization")))
                .security(List.of(new SecurityRequirement()
                        .addList("bearerAuth"))); // chuỗi này chính là chuỗi đặt tên hiển thị phía trên
    }

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("api-service") // group các api
                .packagesToScan("com.electronics_store.controller") // nơi scan bean controller cho group
                .build();
    }
}

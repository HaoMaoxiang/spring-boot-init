package tech.mervyn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author HaoMaoxiang@126.com
 * @since 2020/2/19
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring-Boot-Init Platform API")
                .description("Spring-Boot-Init 脚手架API示例")
                .termsOfServiceUrl("https://github.com/HaoMaoxiang/spring-boot-init")
                .contact(new Contact("HaoMaoxiang", "http://mervyn.me", "haomaoxiang@126.com"))
                .version("1.0.0")
                .licenseUrl("")
                .build();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("tech.mervyn.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}

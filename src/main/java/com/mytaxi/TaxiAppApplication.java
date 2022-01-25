package com.mytaxi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class TaxiAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaxiAppApplication.class, args);
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
        .apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build().apiInfo(getDetails());
  }
  
  private ApiInfo getDetails()
  {
    return new ApiInfoBuilder().title("Taxi App API").version("1.0.0")
        .description("Taxi App API Reference")
        .build();
  }

}

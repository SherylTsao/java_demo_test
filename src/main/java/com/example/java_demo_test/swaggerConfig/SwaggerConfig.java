//package com.example.java_demo_test.swaggerConfig;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration //跟springboot說 為配置類
//@EnableOpenApi //通過此註釋 啟用swagger
//@EnableWebMvc  //引用DelegatingWebMvcConfiguration 配置類，並啟用spring MVC
//public class SwaggerConfig {
//
//	//接口訊息，包含 標題、說明、版本、聯絡人
//	private ApiInfo DEFAULT_API_INFO = new ApiInfoBuilder()
//			.title("java_demo_test Restful API")
//			.description("java_demo_test API") //說明
////			.termsOfServiceUrl("urn:tos")
////			.contact(new Contact("DEFAULT","",""))//聯絡人
////			.license("Apache 2.0")
////			.version("v3")
////			.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0.text")
//			.build();
//	
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.apiInfo(DEFAULT_API_INFO)
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.example.java_demo_test.controller"))
//				.paths(PathSelectors.any())
//				.build();
//		
//		//返回版本概要訊息
//	}
//	
//	
//	
//	
//	
//	
//}

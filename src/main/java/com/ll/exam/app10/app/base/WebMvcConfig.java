package com.ll.exam.app10.app.base;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.
        addResourceHandler("/gen/**")
        .addResourceLocations("file:/Users/choahyoung/work/tmp/app10/"); //리눅스 root에서 시작하는 폴더 경로
  }
}

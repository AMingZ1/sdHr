package com.sd.sdhr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

//如果你想diy一些定制化功能，只需要写好这个组件，然后交给SpringBoot，它会帮我们自动装配.

//如果要拓展spring MVC，官方建议我们这样去做.
@Configuration
//@EnableWebMvc 拓展的一定不能加这个，这个注解默认导入了一个类：delegatingWebMvcConfiguration:从容器中获取所有的WebMvcConfig.
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html","/charts/*","/css/*","/js/*"
                        ,"/images/*","/admin-dashboard.html","/admin-dashboard"
                        ,"/index", "/processes/*");
    }
    //ViewResolver 实现了视图解析器接口的类，可以看作视图解析器
    @Bean
    public ViewResolver myViewResolver(){
        return new MyViewResolver();

    }

    //自定义了一个自己的视图解析器MyViewResolver
    public  static class MyViewResolver implements  ViewResolver{
        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception{
            return  null;
        }
    }

/*

    //视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/gq").setViewName("test");
    }*/
}

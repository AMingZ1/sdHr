package com.sd.sdhr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//本身就是Spring的一个组件
//程序主入口

@SpringBootApplication
/**SpringBootApplication。
 * 标注这个类是一个SpringBoot的应用：启动类下的所有资源被导入
 * 核心是@SpringBootConfiguration和@EnableAutoConfiguration
 * @SpringBootConfiguration：springBoot的配置。包含：
 *                          @Configuration：spring配置类。包含：
 *                              @Component：说明这也是一个Spring的组件
 * @EnableAutoConfiguration：自动配置※。包含：
 *                          @AutoConfigurationPackage：自动配置包。包含：
 *                              @Import({AutoConfigurationPackages.Registrar.class})：自动配置‘包注册’
 *                          @Import({AutoConfigurationImportSelector.class})：自动配置导入选择。如下:
 *                              List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes)：获取所有配置
 *                                  org.springframework.boot:spring-boot-autoconfigure:2.7.2:META-INF/spring.factories：自动配置的核心文件
 *                                  Springboot所有的自动配置都在启动类中扫描并加载：但是不一定生效，只要导入了对应的start就有对应的启动器，自动装备才会生效。
 */
public class SdHrApplication {

    public static void main(String[] args) {
        //将应用启动
        //SpringApplication
        //run
        /***
         * SpringApplication：1、推断应用类型是普通项目还是Web项目
         *                    2、查找并加载所有的可用视化器，设置到initializers(初始化器)属性中
         *                    3、找出所有的应用程序监听器，设置到listeners属性中
         *                    4、推断并设置main方法的定义类，找到运行的主类
         */
        SpringApplication.run(SdHrApplication.class, args);
    }

}

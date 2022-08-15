package com.sd.sdhr;

import com.sd.sdhr.pojo.Dog;
import com.sd.sdhr.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

//单元测试
@SpringBootTest
class SdHrApplicationTests {
    @Autowired
    //Dog dog;
   private Person person;
    @Test
    void contextLoads() {
        //System.out.println(dog);
        System.out.println(person);
    }

}

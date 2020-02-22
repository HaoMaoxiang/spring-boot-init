package tech.mervyn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author haomaoxiang@126.com
 */
@SpringBootApplication
@MapperScan("tech.mervyn.dao.mapper")
public class SpringBootInitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootInitApplication.class, args);
    }

}

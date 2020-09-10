package nccs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-10 14:57
 * @description:
 **/
@RestController
@EnableDiscoveryClient
@SpringBootApplication
@RefreshScope
public class ComsumerServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ComsumerServiceApp.class, args);
    }

    @GetMapping("/helloNacos")
    public String helloNacos() {
        return "你好，nacos！";
    }


    @Value("${config.info}")
    private String info;

    @GetMapping("/test")
    String test(){
        return info;
    }
}

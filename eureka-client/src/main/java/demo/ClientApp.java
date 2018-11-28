package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

@EnableAutoConfiguration
@ComponentScan
@RestController
@RefreshScope
//@EnableEurekaClient
@EnableDiscoveryClient
public class ClientApp implements GreetingController{
    @Value("${bar:World!}")
    String bar;

    
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;
 
    @Value("${spring.application.name}")
    private String appName;
    
    @RequestMapping("/greeting")
    public String greeting() {
        return String.format("Hello from '%s'!", eurekaClient.getApplication(appName).getName());
    }
//    @RequestMapping("/")
//    String hello() {
//        return "Hello " + bar + "!";
//    }

    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class, args);
    }
    
}
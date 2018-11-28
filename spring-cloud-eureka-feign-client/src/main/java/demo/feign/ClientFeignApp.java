package demo.feign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

@EnableAutoConfiguration
@ComponentScan
@RestController
@RefreshScope
@EnableEurekaClient
public class ClientFeignApp {
//	@Autowired
//    private GreetingClient greetingClient;
 
    public static void main(String[] args) {
        SpringApplication.run(ClientFeignApp.class, args);
    }
 
    @Autowired
    private DiscoveryClient discoveryClient;

    public String serviceUrl() {
        List<ServiceInstance> list = discoveryClient.getInstances("spring-cloud-eureka-client");
        if (list != null && list.size() > 0 ) {
            return list.get(0).getUri().toString();
        }
        return null;
    }
    @RequestMapping("/get-greeting")
    public String greeting() {
//        return "hello: "+greetingClient.greeting();
        
        return "hello: "+serviceUrl();
    }
    
}
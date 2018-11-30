package demo.feign;

import org.springframework.web.bind.annotation.RequestMapping;

public interface GreetingClient {
    @RequestMapping("/greeting")
    String greeting();

	
}
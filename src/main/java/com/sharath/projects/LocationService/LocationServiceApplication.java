package com.sharath.projects.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharath.projects.LocationService.ConfigClientAppConfiguration;


@EnableDiscoveryClient
@SpringBootApplication
@RestController
@RefreshScope
public class LocationServiceApplication {

	@Autowired
	private ConfigClientAppConfiguration properties;
	
	@Value("${some.other.property}")
	private String someOtherProperty;
	
	@Value("${service.instance.name}")	
	private String serviceName;
	
	public static void main(String[] args) {
		SpringApplication.run(LocationServiceApplication.class, args);
	}

	@RequestMapping("/")
	public String myMethod(@RequestHeader("x-location") String headerValue) {
		return "My Location service "+headerValue;
	}
	@RequestMapping("/properties")
	public String myProperties() {
		StringBuilder sb = new StringBuilder();
		return sb.append(properties.getProperty()).append(" || ").append(someOtherProperty).toString();
		
	}
}

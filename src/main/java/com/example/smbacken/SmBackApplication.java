package com.example.smbacken;


import com.example.smbacken.controller.WebSocketC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;



@SpringBootApplication
public class SmBackApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        //解决springboot和websocket之间使用@autowired注入为空问题
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SmBackApplication.class, args);
        //这里将Spring Application注入到websocket类中定义的Application中。
        WebSocketC.setApplicationContext(applicationContext);
    }

//    @Bean
//    public ServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//        tomcat.addAdditionalTomcatConnectors(createHTTPConnector());
//        return tomcat;
//    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SmBackApplication.class);
    }
    @Bean
    public ServerEndpointExporter serverEndpointExporter() throws Exception {
        return new ServerEndpointExporter();
    }
}

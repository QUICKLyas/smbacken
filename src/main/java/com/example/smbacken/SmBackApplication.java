package com.example.smbacken;


//import org.junit.Test;
import com.example.smbacken.controller.WebSocketC;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.ApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.HashSet;



@SpringBootApplication
//@SpringBootConfiguration
public class SmBackApplication extends SpringBootServletInitializer {
//    @Test
    public static void main(String[] args) {
        //解决springboot和websocket之间使用@autowired注入为空问题
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SmBackApplication.class, args);
//这里将Spring Application注入到websocket类中定义的Application中。
        WebSocketC.setApplicationContext(applicationContext);
    }

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createHTTPConnector());
        return tomcat;
    }

    private Connector createHTTPConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        //同时启用http（8080）、https（8443）两个端口
        connector.setScheme("http");
        connector.setSecure(false);
        connector.setPort(8080);
        connector.setRedirectPort(8443);
        return connector;
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SmBackApplication.class);
    }
    @Bean
    public ServerEndpointExporter serverEndpointExporter() throws Exception {
        return new ServerEndpointExporter();
    }
}

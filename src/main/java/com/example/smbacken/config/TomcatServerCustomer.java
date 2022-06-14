package com.example.smbacken.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * 自定义tomcat http 80连接器
 *
 * @author cloudgyb
 * @since 2022/3/12 15:28
 */
@Component
public class TomcatServerCustomer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        final Connector httpConn = new Connector("HTTP/1.1");
        httpConn.setPort(9307);
        factory.addAdditionalTomcatConnectors(httpConn);
    }
}
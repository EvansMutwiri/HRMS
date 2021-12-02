package com.eazybytes.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WSDLConfigService {

    //RequestHandler - Servlet -> ServletRegistration Bean - MessageServlet
    //url /soap/*

    @Bean
    public ServletRegistrationBean requestDispatcher(ApplicationContext context){
        MessageDispatcherServlet mds = new MessageDispatcherServlet();
        mds.setApplicationContext(context);
        mds.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(mds, "/soap/*");
    }
    @Bean
    public XsdSchema employeeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("employee.xsd"));
    }

    @Bean(name = "empservice")
    public DefaultWsdl11Definition createWsdl(XsdSchema schema) {
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("EmployeePort");
        wsdl.setLocationUri("/soap");
        wsdl.setTargetNamespace("http://eazybytes.com/hrms");
        wsdl.setSchema(schema);
        return wsdl;

    }
}

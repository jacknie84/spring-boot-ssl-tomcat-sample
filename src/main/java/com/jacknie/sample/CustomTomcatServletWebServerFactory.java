package com.jacknie.sample;

import org.apache.catalina.Host;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import javax.servlet.Servlet;
import javax.servlet.ServletContainerInitializer;
import java.util.Collections;

public class CustomTomcatServletWebServerFactory extends TomcatServletWebServerFactory {

    @Override
    protected void prepareContext(Host host, ServletContextInitializer[] initializers) {
        super.prepareContext(host, initializers);
        StandardContext child = new StandardContext();
        child.setName("root");
        child.addLifecycleListener(new Tomcat.FixContextListener());
        child.setPath("");
        ServletContainerInitializer initializer = getServletContextInitializer(getContextPath());
        child.addServletContainerInitializer(initializer, Collections.emptySet());
        child.setCrossContext(true);
        host.addChild(child);
    }

    private ServletContainerInitializer getServletContextInitializer(String contextPath) {
        return (c, context) -> {
            Servlet servlet = new RedirectContextPathServlet(contextPath);
            context.addServlet("root", servlet).addMapping("/*");
        };
    }

}

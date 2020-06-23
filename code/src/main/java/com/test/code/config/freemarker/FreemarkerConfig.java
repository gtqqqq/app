package com.test.code.config.freemarker;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import freemarker.template.TemplateException;

@Configuration
public class FreemarkerConfig implements ApplicationContextAware{
	
    @Autowired  
    protected freemarker.template.Configuration configuration;  
    @Autowired  
    protected FreeMarkerViewResolver resolver;  
    @Autowired  
    protected InternalResourceViewResolver springResolver; 

    private ApplicationContext applicationContext;

    @PostConstruct
    public void afterPropertiesSet() throws IOException, TemplateException {
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(FreemarkerFunction.class);
        for (String key : map.keySet()) {
            configuration.setSharedVariable(key, map.get(key));
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

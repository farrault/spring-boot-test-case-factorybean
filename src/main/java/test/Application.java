package test;

import org.apache.cxf.jaxws.spring.JaxWsProxyFactoryBeanDefinitionParser.JAXWSSpringClientProxyFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import test.service.CxfClientService;
import test.service.StringClientService;


@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext app = SpringApplication.run(Application.class, args);
		
		try {
		
			CxfClientService cxfClientService = app.getBean(CxfClientService.class);
			cxfClientService.logWSAddress();

			StringClientService bean = app.getBean(StringClientService.class);
			bean.logInjectedStrings();
			
			JAXWSSpringClientProxyFactoryBean factoryBean = app.getBean("&MyWebService",JAXWSSpringClientProxyFactoryBean.class);
			System.out.println(factoryBean.getAddress());
			
			
		} finally {
			app.close();
		}
	}
}

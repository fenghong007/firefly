package daniel.sheppard.firefly;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import daniel.sheppard.firefly.web.filter.HelloFilter;
import daniel.sheppard.firefly.web.servlet.HelloServlet;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EntityScan
public class AppConfig extends SpringBootServletInitializer {

	@Value("${characterEncoding}")
	private String characterEncoding;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}

	@Bean
	public CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding(characterEncoding);
		filter.setForceEncoding(true);
		return filter;
	}

	@Bean
	public ServletRegistrationBean helloServlet() {
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new HelloServlet());
		reg.setLoadOnStartup(1);
		List<String> mapping = new ArrayList<String>();
		mapping.add("/hello");
		mapping.add("/hello.html");
		reg.setUrlMappings(mapping);
		return reg;
	}

	@Bean
	public FilterRegistrationBean helloFilter() {
		FilterRegistrationBean reg = new FilterRegistrationBean();
		reg.setFilter(new HelloFilter());
		reg.addUrlPatterns("/*");
		return reg;
	}

	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(
				Charset.forName(characterEncoding));
		return stringHttpMessageConverter;
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(AppConfig.class);
	}
}

@Configuration
@Profile("dev")
class ClientResourcesConfig extends WebMvcConfigurerAdapter {

	@Value("${app.static.path}")
	private String staticPath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("file://" + staticPath).setCachePeriod(0);
	}
}

package cloth.config.web;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan("cloth.web")
public class WebConfig extends WebMvcConfigurerAdapter {
	/*
	 * DispatcherServlet doesn’t implement any logic for parsing the data in a
	 * multipart request. Instead, it delegates to an implementation of Spring’s
	 * MultipartResolver strategy interface to resolve the content in a
	 * multipart request. StandardServletMultipartResolver—Relies on Servlet 3.0
	 * support for multipart requests (since Spring 3.1)
	 */
	@Bean(name = "multipartResolver")
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	/*
	 * <thymeleafConfig>---------------------------------------------------------
	 */

	@Bean(name = "templateResolver")
	public TemplateResolver templateResolver() {
		TemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");

		/*
		 * Its templateMode property is also set to HTML5, indicating that the
		 * templates resolved are expected to render HTML5 output
		 */
		templateResolver.setTemplateMode("HTML5");
		return templateResolver;
	}

	@Bean(name = "templateEngine")
	@DependsOn({ "templateResolver" })
	public TemplateEngine templateEngine(@Qualifier("templateResolver") TemplateResolver templateResolver) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		return templateEngine;
	}

	/*
	 * In order to use Thymeleaf with Spring, you’ll need to configure three
	 * beans that enable Thymeleaf-Spring integration: > A ThymeleafViewResolver
	 * that resolves Thymeleaf template views from logical view names > A
	 * SpringTemplateEngine to process the templates and render the results > A
	 * TemplateResolver that loads Thymeleaf templates
	 */
	@Bean
	@DependsOn({ "templateEngine" })
	public ViewResolver viewResolver(@Qualifier("templateEngine") SpringTemplateEngine templateEngine) {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		return viewResolver;
	}

	/* </thymeleafConfig>-------------------------------------------------- */

	/*
	 * It loads messages from a properties file whose name is derived from a
	 * base name.
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

		/*
		 * The key thing in this bean declaration is the setting of the basename
		 * property. You can set it to any value you’d like, but here I’ve
		 * chosen to set it to messages. By setting it to messages, you can
		 * expect ResourceBundleMessageResolver to resolve messages from
		 * properties files at the root of the classpath whose names are derived
		 * from that base name.
		 */
		messageSource.setBasename("messages");
		return messageSource;
	}

	/*
	 * Configures a request handler for serving static resources by forwarding
	 * the request to the Servlet container's "default" Servlet. This is
	 * intended to be used when the Spring MVC DispatcherServlet is mapped to
	 * "/" thus overriding the Servlet container's default handling of static
	 * resources. Since this handler is configured at the lowest precedence,
	 * effectively it allows all other handler mappings to handle the request,
	 * and if none of them do, this handler can forward it to the "default"
	 * Servlet.
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}
}

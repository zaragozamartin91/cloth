package cloth.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import cloth.config.web.WebConfig;

/*El nombre de la app a crear es cloth*/
public class ClothWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setMultipartConfig(getMultipartConfigElement());
	}

	// TODO : SE ESTABLECIO EL PERFIL test PARA PRUEBAS, SE DEBE MODIFICAR
	// EVENTUALMENTE.
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);

		servletContext.setInitParameter("spring.profiles.active", "test");

		System.setProperty("spring.thymeleaf.mode", "LEGACYHTML5");
	}

	/*
	 * <multipartConfig> you can override the customizeRegistration() method
	 * (which is given a Dynamic as a parameter) to configure multipart details.
	 * suppose you want to limit files to no more than 2 MB, to limit the entire
	 * request to no more than 4 MB, and to write all files to disk...
	 */
	private MultipartConfigElement getMultipartConfigElement() {
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(LOCATION, MAX_FILE_SIZE,
				MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
		return multipartConfigElement;
	}

	private static final String LOCATION = "C:/temp/"; // Temporary location
														// where files will be
														// stored

	private static final long MAX_FILE_SIZE = 5242880; // 5MB : Max file size.
														// Beyond that size
														// spring will throw
														// exception.
	private static final long MAX_REQUEST_SIZE = 20971520; // 20MB : Total
															// request size
															// containing Multi
															// part.

	private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after
														// which files will be
														// written to disk
	/* </multipartConfig> */
}

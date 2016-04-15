package ca.krystasalera;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@SpringBootApplication
public class L1DevKrystaSaleraSpringBootApplication {

	static final Logger logger = LogManager.getLogger(L1DevKrystaSaleraSpringBootApplication.class.getName());
	 
	
	public static void main(String[] args) {
		logger.info("entered application");
		disableCertificateValidation();
		SpringApplication.run(L1DevKrystaSaleraSpringBootApplication.class, args);
	}
	
	
	
	 public static void disableCertificateValidation() {
	      // Create a trust manager that does not validate certificate chains
	      TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
	          public X509Certificate[] getAcceptedIssuers() {
	              return new X509Certificate[0];
	          }

	          @Override
	          public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

	          }

	          @Override
	          public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

	          }
	      }};

	      // Ignore differences between given hostname and certificate hostname
	      HostnameVerifier hv = new HostnameVerifier() {
	          @Override
	          public boolean verify(String hostname, SSLSession session) {
	              return true;
	          }
	      };

	      // Install the all-trusting trust manager
	      try {
	          SSLContext sc = SSLContext.getInstance("SSL");
	          sc.init(null, trustAllCerts, new SecureRandom());
	          HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	          HttpsURLConnection.setDefaultHostnameVerifier(hv);
	      } catch (Exception e) {
	          // Do nothing
	      }
	  }

	
//	 @Bean
//	  public EmbeddedServletContainerFactory servletContainer() {
//	    TomcatEmbeddedServletContainerFactory tomcat =
//	      new TomcatEmbeddedServletContainerFactory() {
//	      
//	        @Override
//	        protected void postProcessContext(Context context) {
//	          SecurityConstraint securityConstraint = new SecurityConstraint();
//	          securityConstraint.setUserConstraint("CONFIDENTIAL");
//	          SecurityCollection collection = new SecurityCollection();
//	          collection.addPattern("/mvchome/*");
//	          collection.addPattern("/home/*");
//	          securityConstraint.addCollection(collection);
//	          context.addConstraint(securityConstraint);
//	        }
//	      };
//	    tomcat.addAdditionalTomcatConnectors(createHttpConnector());
//	    return tomcat;
//	  }
//	  
//	  private Connector createHttpConnector() {
//	    Connector connector =
//	      new Connector("org.apache.coyote.http11.Http11NioProtocol");
//	    connector.setScheme("http");
//	    connector.setSecure(false);
//	    connector.setPort(8080);
//	    connector.setRedirectPort(8443);
//	    return connector;
//	  }
//	  
	  
	 //fixes : Circular view path [home]: would dispatch back to the current handler URL error
	  @Bean
	  public ViewResolver viewResolver() {
	    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	    templateResolver.setTemplateMode("XHTML");
	    templateResolver.setPrefix("web/");
	    templateResolver.setSuffix(".html");

	    SpringTemplateEngine engine = new SpringTemplateEngine();
	    engine.setTemplateResolver(templateResolver);

	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(engine);
	    return viewResolver;
	  }

	
}

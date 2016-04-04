//package ca.krystasalera.configuration;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.thymeleaf.spring4.SpringTemplateEngine;
//import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
//
//@Configuration
//public class ThymeleafExtension {
//
//    @Autowired
//    private SpringTemplateEngine templateEngine;
//
//    @PostConstruct
//    public void extension() {
//    	ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
//        resolver.setPrefix("/templates/web/");
//        resolver.setSuffix(".html");
//        resolver.setTemplateMode("HTML5");
//        resolver.setOrder(templateEngine.getTemplateResolvers().size());
//        resolver.setCacheable(false);
//        templateEngine.addTemplateResolver(resolver);
//    }
//}

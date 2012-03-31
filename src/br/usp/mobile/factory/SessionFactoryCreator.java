package br.usp.mobile.factory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
@ApplicationScoped
public class SessionFactoryCreator implements ComponentFactory<SessionFactory> {
	private SessionFactory sessionFactory;

	@PostConstruct
	public void create() {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.configure();
		
		sessionFactory = configuration.buildSessionFactory();
	}
	
	public SessionFactory getInstance() {
		return sessionFactory;
	}
	
	@PreDestroy
	public void close() {
		sessionFactory.close();
	}
}

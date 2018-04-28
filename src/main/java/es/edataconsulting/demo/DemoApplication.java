package es.edataconsulting.demo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Application;

import es.edataconsulting.demo.model.User;
import es.edataconsulting.demo.resources.UserResources;
import es.edataconsulting.exception.GenericExceptionMapper;

public class DemoApplication extends Application {

	/*
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(GenericExceptionMapper.class);
        s.add(UserResources.class);
        return s;
	}
	*/
	
	@Override
	public Set<Object> getSingletons() {
	    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DemoPU");
	   
	    PopulateDatabase(entityManagerFactory.createEntityManager());
	    
	    Set<Object> s = new HashSet<Object>();
	    
	    GenericExceptionMapper genericExceptionMapper = new GenericExceptionMapper();
	    UserResources userResources = new UserResources(entityManagerFactory);
	    
	    s.add(genericExceptionMapper);
	    s.add(userResources);
	    
	    return s;
	}
	
	private void PopulateDatabase(EntityManager entityManager) {
		entityManager.getTransaction().begin();
		entityManager.persist( new User("User 1") );
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	
	
}

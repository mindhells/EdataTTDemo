package es.edataconsulting.demo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Application;

import es.edataconsulting.demo.model.Role;
import es.edataconsulting.demo.model.User;
import es.edataconsulting.demo.providers.AuthenticationProvider;
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
	    AuthenticationProvider authProvider = new AuthenticationProvider(entityManagerFactory);
	    
	    s.add(genericExceptionMapper);
	    s.add(authProvider);
	    s.add(userResources);
	    
	    return s;
	}
	
	private void PopulateDatabase(EntityManager entityManager) {
		entityManager.getTransaction().begin();
		Role standardRole = new Role("standard");
		entityManager.persist(standardRole);
		Role adminRole = new Role("admin");
		entityManager.persist(adminRole);
		Set<Role> roles = new HashSet<>();
		roles.add(standardRole);
		roles.add(adminRole);
		User adminUser = new User("User 1", "admin", "3d4t4");
		User felipe = new User("Felipe", "felipe", "s3cret");
		User roberto = new User("Roberto", "roberto", "s3cret");
		User maria = new User("Maria", "maria", "s3cret");
		User rebeca = new User("Rebeca", "rebeca", "s3cret");
		Set<Role> rolesStandard = new HashSet<>();
		rolesStandard.add(standardRole);		
		Set<Role> rolesAdmin = new HashSet<>();
		rolesAdmin.add(adminRole);		
		felipe.setRoles(rolesStandard);
		roberto.setRoles(rolesStandard);
		maria.setRoles(rolesStandard);
		rebeca.setRoles(rolesAdmin);
		adminUser.setRoles(roles);
		entityManager.persist(adminUser);
		entityManager.persist(felipe);
		entityManager.persist(roberto);
		entityManager.persist(maria);
		entityManager.persist(rebeca);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	
	
}

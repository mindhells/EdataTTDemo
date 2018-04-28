package es.edataconsulting.demo.resources;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

import es.edataconsulting.demo.model.Role;
import es.edataconsulting.demo.model.User;

public class RoleResources {

	private EntityManager entityManager;
	
	public RoleResources(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@GET
	public Set<Role> getRoles(@PathParam("userId") long userId) {
		User user = this.entityManager.find(User.class, userId);
		
		return user.getRoles();
	}
}

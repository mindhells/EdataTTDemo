package es.edataconsulting.demo.resources;
 

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import es.edataconsulting.demo.model.User;
import es.edataconsulting.demo.service.UserService;



@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResources {
	
	UserService userService = new UserService();
	
	private EntityManager entityManager; 
	
	public UserResources(EntityManagerFactory entityManagerFactory) {
		super();
		this.entityManager = entityManagerFactory.createEntityManager();
	}

	@GET
	public List<User> getUsers(
			@DefaultValue("10") @QueryParam("limit") long limit, 
			@QueryParam("offset") long offset, 
			@QueryParam("orderBy") List<String> orderBy) {
		
		Query q = entityManager.createQuery("SELECT u from User u");
		@SuppressWarnings("unchecked")
		List<User> users = q.getResultList();
	    return users;
		//return this.userService.getAllUsers();
	}
	
	@GET
	@Path("/{userId}")
	public User getUser(@PathParam("userId") long userId) {
		if (userId > 10)
			throw new RuntimeException("erro de prueba");
		return new User(userId, "random user");
	}
	
	@POST
	public Response addUser(User user) {
		return Response.status(Status.CREATED)
				.entity(user)
				.build();
	}
	
	@PUT
	public User updateUser(User user) {
		return user;
	}
	
	@DELETE
	@Path("/{userId}")
	public void deleteUser(@PathParam("userId") long userId) {

	}
	
	@Path("/{userId}/roles")
	public RoleResources getRoleReources() {
		return new RoleResources(this.entityManager);
	}
}

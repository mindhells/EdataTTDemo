package es.edataconsulting.demo.resources;
 

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

import es.edataconsulting.demo.model.Role;
import es.edataconsulting.demo.model.User;


@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResources {
	
	private EntityManager entityManager; 
	
	public UserResources(EntityManagerFactory entityManagerFactory) {
		super();
		this.entityManager = entityManagerFactory.createEntityManager();
	}

	
	//toDo: handle ORM exceptions
	
	
	@GET
	public List<User> getUsers(
			@DefaultValue("10") @QueryParam("limit") int limit, 
			@QueryParam("offset") int offset, 
			@DefaultValue("id") @QueryParam("orderBy") String orderBy,
			@DefaultValue("asc") @QueryParam("orderDirection") String orderDirection) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root);
		if (orderDirection.equals("desc")) {
			criteria.orderBy(cb.desc(root.get(orderBy)));
		}else{
			criteria.orderBy(cb.asc(root.get(orderBy)));
		}
		TypedQuery<User> q = entityManager.createQuery(criteria);
		q.setFirstResult(offset);
		q.setMaxResults(limit);
		
		return q.getResultList();
	}
	
	@GET
	@Path("/{userId}")
	public User getUser(@PathParam("userId") long userId) {
		User user = entityManager.find(User.class, userId);
		return user;
	}
	
	@POST
	public Response addUser(User user) {
		entityManager.getTransaction().begin();		
		if (user.getRoles().isEmpty()) {
			Set<Role> roleSet = new HashSet<>();
			roleSet.add(entityManager.find(Role.class, "standard"));
			user.setRoles(roleSet);
		}
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		return Response.status(Status.CREATED)
				.entity(user)
				.build();
	}
	
	@PUT
	public User updateUser(User user) {
		entityManager.getTransaction().begin();		
		entityManager.merge(user);
		entityManager.getTransaction().commit();
		return user;
	}
	
	@DELETE
	@Path("/{userId}")
	public void deleteUser(@PathParam("userId") long userId) {
		User user = entityManager.find(User.class, userId);
		entityManager.getTransaction().begin();		
		entityManager.remove(user);
		entityManager.getTransaction().commit();
	}
	
	@Path("/{userId}/roles")
	public RoleResources getRoleReources() {
		return new RoleResources(this.entityManager);
	}
}

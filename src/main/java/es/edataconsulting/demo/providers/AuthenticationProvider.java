package es.edataconsulting.demo.providers;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.Base64;

import es.edataconsulting.demo.model.ErrorMessage;
import es.edataconsulting.demo.model.Role;
import es.edataconsulting.demo.model.User;

public class AuthenticationProvider implements ContainerRequestFilter {
	
	EntityManager entityManager;
	
	@Context
    private ResourceInfo resourceInfo;

	public AuthenticationProvider(EntityManagerFactory entityManagerFactory) {
		super();
		this.entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		Method method = resourceInfo.getResourceMethod();	
		
		//allowed to everyone
		if (method.isAnnotationPresent(PermitAll.class)) return;
		
		String authorization = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		authorization = authorization.replace("Basic ", "");
		
		//no authorization header data
		if(authorization == null || authorization.isEmpty())
        {
            requestContext.abortWith(getAccessDeniedResponse());
            return;
        }
		
		//Find the user trying to authenticate
        String usernameAndPassword = new String(Base64.decode(authorization.getBytes()));
        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();      
        
        User user = getUserByLogin(username);
        
        //user login failed        
        if( user == null || !user.getPassword().equals(password)) {        	
        	requestContext.abortWith(getAccessDeniedResponse());
            return;
        }
        
        //check role restriction access
        if (!method.isAnnotationPresent(RolesAllowed.class)) return;
        RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
        Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
        boolean roleFound = false;
        for (Role role: user.getRoles()) {
        	if (rolesSet.contains(role.getName())) {
        		roleFound = true;
        		break;
        	}
        }
        //user have none of the roles allowed
        if (!roleFound) {
        	requestContext.abortWith(getAccessDeniedResponse());
            return;
        }
		
	}
	
	private Response getAccessDeniedResponse() {
		return  Response.status(Response.Status.UNAUTHORIZED)
	            .entity(new ErrorMessage("acceso denegado", 401)).build();
	}
	
	private User getUserByLogin(String login) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root);
		criteria.where(cb.equal(root.get("login"), login));
		TypedQuery<User> q = entityManager.createQuery(criteria);
		try {
			return q.getSingleResult();
		}catch (NoResultException exception) {
			return null;
		}
	}

}

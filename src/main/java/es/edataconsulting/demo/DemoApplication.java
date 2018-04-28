package es.edataconsulting.demo;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import es.edataconsulting.demo.resources.UserResources;
import es.edataconsulting.exception.GenericExceptionMapper;

public class DemoApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(GenericExceptionMapper.class);
        s.add(UserResources.class);
        return s;
	}
	
}

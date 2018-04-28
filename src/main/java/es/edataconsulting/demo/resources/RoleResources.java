package es.edataconsulting.demo.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

public class RoleResources {

	@GET
	public List<String> getRoles(@PathParam("userId") long userId) {
		List<String> list = new ArrayList<>();
		list.add("admin");
		list.add("default");
		return list;
	}
}

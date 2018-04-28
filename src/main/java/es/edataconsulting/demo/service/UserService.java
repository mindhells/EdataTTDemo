package es.edataconsulting.demo.service;

import java.util.ArrayList;
import java.util.List;

import es.edataconsulting.demo.model.User;

public class UserService {

	
	public List<User> getAllUsers(){
		List<User> list = new ArrayList<>();
		list.add(new User(1, "pedro"));
		list.add(new User(2, "luis"));
		return list;
	}
}

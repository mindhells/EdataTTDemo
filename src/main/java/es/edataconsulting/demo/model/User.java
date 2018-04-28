package es.edataconsulting.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="TM_USERS")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String name;
	
	@ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
        name = "TM_USERS_ROLES", 
        joinColumns = { @JoinColumn(name = "user_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    Set<Role> roles = new HashSet<>();
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public User() {
		
	}
	
	public User(String name) {
		super();
		this.name = name;
	}
	
	public User(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

}

package yes.share.library.persistence.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Usuarios da aplicación
 * 
 * @author noeca
 */
@Entity
@Table(name = "users")
public class User { 

	@Id @JsonIgnore
  	@GeneratedValue(strategy = GenerationType.AUTO)
  	private long id;
  
  	@Column(name = "username", nullable = true, length = 255)
  	private String username;
  
  	@Column(name = "password", nullable = true, length = 255)
  	private char[] password;
  	
  	//@NotNull @Column(unique=true)
  	private String email;
  	
  	private String name;
  
  	private String familyName1;
  
  	private String familyName2;
  
  	private String facebookId;
  	
  	private String googleId;
  	
  	private String githubId;
  	
  	@JsonIgnore
  	@OneToMany(fetch = FetchType.EAGER, mappedBy="user" )
  	private Set<UserRole> userRoles;
  	
  	public User() {}

  	public User(long id) { 
  		this.id = id;
  	}
  
  	public User(String username, String email) {
  		this.username = username;
  		this.email = email;
  	}

  	public long getId() {
  		return id;
  	}
  
  	public void setId(long value) {
  		this.id = value;
	}

  	public String getUsername() {
  		return username;
  	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public char[] getPassword() {
		return password;
	}
	
	public void setPassword(char[] password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFamilyName1() {
		return familyName1;
	}
	
	public void setFamilyName1(String familyName1) {
		this.familyName1 = familyName1;
	}
	
	public String getFamilyName2() {
		return familyName2;
	}
	
	public void setFamilyName2(String familyName2) {
		this.familyName2 = familyName2;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public String getGithubId() {
		return githubId;
	}

	public void setGithubId(String githubId) {
		this.githubId = githubId;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	@Transient
	public List<String> getAuthorities() {
		List<String> authorities = new ArrayList<String>();
		for (UserRole userRoles : getUserRoles()) {
			authorities.add(userRoles.getRole().getPrefixName());
		}
		return authorities;
	}
}
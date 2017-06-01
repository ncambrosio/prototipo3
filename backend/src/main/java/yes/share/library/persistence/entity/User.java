package yes.share.library.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

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
  
  	@NotNull
  	@Column(name = "username", nullable = false, length = 255)
  	private String username;
  
  	@NotNull
  	@Column(name = "password", nullable = false, length = 255)
  	private char[] password;
  
  	@NotNull
  	private String name;
  
  	@Null
  	private String familyName1;
  
  	@Null
  	private String familyName2;
  
  	@NotNull
  	private String email;
  
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
}
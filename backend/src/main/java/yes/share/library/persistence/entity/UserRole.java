package yes.share.library.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Roles de los usuarios de la aplicación
 * 
 * @author noeca
 */
@Entity
@Table(name = "user_role")
public class UserRole { 

	public enum Role {
		
		ADMIN ("ADMIN"),
		USER  ("USER");
		
		private final static String prefix = "ROLE_";
		private final String name;
		    
		Role(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public String getPrefixName() {
			return prefix + name;
		}
	};

	@Id @JsonIgnore
  	@GeneratedValue(strategy = GenerationType.AUTO)
  	private long id;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
  	private User user;
	
	@JsonIgnore
	@Enumerated(EnumType.STRING)
  	@Column(name = "role", nullable = false, length = 255)
	private Role role;
	
	public UserRole() {
	}
	
	public UserRole(User user) {
		this(user, Role.USER);
	}
	
	public UserRole(User user, Role role) {
		this.user = user;
		this.role = role;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
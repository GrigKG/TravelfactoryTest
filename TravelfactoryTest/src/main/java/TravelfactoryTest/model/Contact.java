package TravelfactoryTest.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"email"})
@Entity
public class Contact implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7512207905112046467L;
	@Id
	String email;
	String name;
	String firstName;
	String phone;
	@ManyToOne
	Campaign campaign;
	
	public Contact(String name, String firstName, String email, String phone) {
		this.name = name;
		this.firstName = firstName;
		this.email = email;
		this.phone = phone;
	}
}

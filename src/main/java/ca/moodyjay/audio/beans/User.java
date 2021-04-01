package ca.moodyjay.audio.beans;

import java.util.*;

import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="user", schema="public")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String username;
	private String password;
	
	@ManyToOne(cascade=CascadeType.ALL)//, fetch=FetchType.EAGER)
	private Role role;	
}

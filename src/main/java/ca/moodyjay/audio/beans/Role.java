package ca.moodyjay.audio.beans;
import java.util.*;

import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String rolename;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="role")
	private List<User> users;
}

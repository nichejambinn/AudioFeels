package ca.moodyjay.audio.beans;

import java.util.List;
import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Label {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String mood;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="label")
	private List<Track> tracks;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="label")
	private List<Rating> ratings;
}

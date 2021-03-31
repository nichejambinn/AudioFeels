package ca.moodyjay.audio.beans;

import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Track {

	@Id
	private String spotifyId;
	private double danceability;
	private double energy;
	private int trackKey;
	private double loudness;
	private double speechiness;
	private double acousticness;
	private double instrumentalness;
	private double liveness;
	private double valence;
	private double tempo;
	private String trackName;
	private String artist;
	private String album;
	private String imgUrl;
	
	private int labelId;
}

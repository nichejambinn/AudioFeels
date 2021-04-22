package ca.moodyjay.audio.beans.spotify;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Artist {
	private String[] genres;
	private String href;
	private String id;
	private String name;
	private String uri;
}

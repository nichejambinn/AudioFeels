package ca.moodyjay.audio.beans.spotify;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SimplifiedAlbum {
	private String albumGroup;
	private String albumType;
	private String[] availableMarkets;
	private String externalUrls;
	private String href;
	private String id;
	private Image[] images;
	private String name;
	private String releaseDate;
	private String uri;
}

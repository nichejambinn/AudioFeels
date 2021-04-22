package ca.moodyjay.audio.beans.spotify;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Track {
	private SimplifiedAlbum album;
	private Artist[] artists;
	private String[] availableMarkets;
	private String href;
	private String id;
	private String name;
	private boolean isPlayable;
	private String uri;
}

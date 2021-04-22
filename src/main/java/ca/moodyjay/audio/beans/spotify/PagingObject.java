package ca.moodyjay.audio.beans.spotify;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PagingObject {
	private String href;
	private Track[] items;
	private int limit;
	private String next;
	private int offset;
	private String previous;
	private int total;
}

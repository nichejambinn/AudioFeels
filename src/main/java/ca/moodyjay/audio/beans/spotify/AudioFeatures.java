package ca.moodyjay.audio.beans.spotify;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AudioFeatures {
	private float acousticness;
	private String analysisUrl;
	private float danceability;
	private int durationMs;
	private float energy;
	private String id;
	private float instrumentalness;
	private int key;
	private float liveness;
	private float loudness;
	private int mode;
	private float speechiness;
	private float tempo;
	private int timeSignature;
	private String trackHref;
	private String type;
	private String uri;
	private float valence;
}

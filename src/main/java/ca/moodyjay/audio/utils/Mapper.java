package ca.moodyjay.audio.utils;

import ca.moodyjay.audio.beans.Label;
import ca.moodyjay.audio.beans.Track;
import ca.moodyjay.audio.beans.spotify.AudioFeatures;

public class Mapper {
	
	public static Track mapSpotifyTrackAndAudioFeaturesToTrackBean(ca.moodyjay.audio.beans.spotify.Track spotifyTrack, AudioFeatures audioFeatures) {
		Track track = Track.builder()
				.acousticness(audioFeatures.getAcousticness())
				.album(spotifyTrack.getAlbum().getName())
				.artist(spotifyTrack.getArtists()[0].getName())
				.danceability(audioFeatures.getDanceability())
				.energy(audioFeatures.getEnergy())
				.imgUrl(spotifyTrack.getAlbum().getImages()[0].getUrl())
				.instrumentalness(audioFeatures.getInstrumentalness())
				.label(new Label())
				.liveness(audioFeatures.getLiveness())
				.loudness(audioFeatures.getLoudness())
				.speechiness(audioFeatures.getSpeechiness())
				.spotifyId(spotifyTrack.getId())
				.tempo(audioFeatures.getTempo())
				.trackKey(audioFeatures.getKey())
				.trackName(spotifyTrack.getName())
				.valence(audioFeatures.getValence())
				.build();
		
		return track;
	}
}

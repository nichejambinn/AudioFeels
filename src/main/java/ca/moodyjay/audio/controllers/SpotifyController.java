package ca.moodyjay.audio.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

import ca.moodyjay.audio.beans.spotify.*;
import ca.moodyjay.audio.repositories.LabelRepository;
import ca.moodyjay.audio.repositories.TrackRepository;
import ca.moodyjay.audio.utils.Mapper;
import reactor.core.publisher.Mono;

@Controller
public class SpotifyController {

	@Autowired
	@Qualifier("spotify")
	private WebClient webClient;
	
	@Autowired
	private TrackRepository trackRepo;
	
	@Autowired
	private LabelRepository labelRepo;
	
	@GetMapping("/search")
	public String goSearch(Model model) {
		model.addAttribute("tracks", new Track[0]);
		
		return "search.html";
	}
	
	@GetMapping("/searchTracks") // localhost:8080/search?q={q}
	public String searchForTrack(@RequestParam String q, Model model) {		
		Mono<TracksResponse> response = webClient.get()
				.uri("https://api.spotify.com/v1", uriBuilder -> uriBuilder
						.path("/search")
						.queryParam("q", q)
						.queryParam("type", "track") // "artist,album,track"
						.build())
				.attributes(
					    ServerOAuth2AuthorizedClientExchangeFilterFunction
					      .clientRegistrationId("spotify"))
				.retrieve()
				.bodyToMono(TracksResponse.class);
		
		model.addAttribute("tracks", response.block().getTracks().getItems());
		
		return "search.html";
	}
	
	@GetMapping("/rateTrack/{id}")
	public String goRateTrack(@PathVariable String id, Model model) {
		ca.moodyjay.audio.beans.Track track = new ca.moodyjay.audio.beans.Track();
		
		// see if track already in db otherwise query from spotify
		Optional<ca.moodyjay.audio.beans.Track> trackQuery = trackRepo.findById(id);
		if (trackQuery.isPresent()) {
			track = trackQuery.get();
		} else {
			Mono<Track> trackResponse = webClient.get()
					.uri("https://api.spotify.com/v1", uriBuilder -> uriBuilder
							.path("/tracks/" + id)
							.build())
					.attributes(
						    ServerOAuth2AuthorizedClientExchangeFilterFunction
						      .clientRegistrationId("spotify"))
					.retrieve()
					.bodyToMono(Track.class);
			
			Mono<AudioFeatures> audioFeaturesResponse = webClient.get()
					.uri("https://api.spotify.com/v1", uriBuilder -> uriBuilder
							.path("/audio-features/" + id)
							.build())
					.attributes(
						    ServerOAuth2AuthorizedClientExchangeFilterFunction
						      .clientRegistrationId("spotify"))
					.retrieve()
					.bodyToMono(AudioFeatures.class);
			
			track = Mapper.mapSpotifyTrackAndAudioFeaturesToTrackBean(trackResponse.block(), audioFeaturesResponse.block());
		}
		
		model.addAttribute("track", track);
		model.addAttribute("labels", labelRepo.findAll());
		
		return "rateTrack.html";
	}
}

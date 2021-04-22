package ca.moodyjay.audio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

import ca.moodyjay.audio.beans.Track;
import ca.moodyjay.audio.beans.TrackForm;
import ca.moodyjay.audio.repositories.LabelRepository;
import ca.moodyjay.audio.repositories.TrackRepository;

@Controller
public class TrackController {

	@Autowired
	private TrackRepository trackRepo;
	
	@Autowired
	private LabelRepository labelRepo;
	
	@GetMapping("/")
	public String goHome() {
		return "home.html";
	}
	
	@GetMapping("/tracks")
	public String goTracks(Model model) {
		
		TrackForm trackForm = new TrackForm();
		trackForm.setTracks((ArrayList<Track>)trackRepo.findAllByOrderByTrackNameAsc());
		
		model.addAttribute("trackForm", trackForm);
		model.addAttribute("labels", labelRepo.findAll());
		return "tracks.html";
	}
	
	@PostMapping("/tracks")
	public String addTrack() {
		Track track = Track.builder()
						.spotifyId("TESTESTESTEST4")
						.acousticness(0)
						.album("DELETEME")
						.artist("I LONG FOR THE RELEASE OF THE GREAT DELETE")
						.danceability(0)
						.energy(0)
						.imgUrl("http://www.DELETEME.com")
						.instrumentalness(0)
						.liveness(0)
						.loudness(0)
						.speechiness(0)
						.tempo(0)
						.trackName("TEST TO BE REMOVED GET UP YOU MIGHT GET DOWN WITH THE SICKNESS")
						.trackKey(0)
						.build();
		
		trackRepo.save(track);
		
		return "redirect:/tracks";
	}
	
	@PostMapping("/saveTrackLabel/{id}")
	public String saveTrackLabel(@PathVariable String id, @ModelAttribute TrackForm trackForm) {
		// retrieve track with matching id from track form list
		Track track = trackForm.getTracks().stream().filter(t -> t.getSpotifyId().equals(id)).findFirst().orElse(null);
		if (track != null) {
			int labelId = track.getLabel().getId();
			
			// find track in db
			Optional<Track> trackQuery = trackRepo.findById(id);
			if (trackQuery.isPresent()) {
				track = trackQuery.get();
				
				// update label and save to db
				track.setLabel(labelRepo.findById(labelId).get());
				trackRepo.save(track);
			}
		}
		
		return "redirect:/tracks";
	}
	
	@PostMapping("/deleteTrack/{id}")
	public String deleteTrack(@PathVariable String id) {
		Optional<Track> trackQuery = trackRepo.findById(id);
		if (trackQuery.isPresent()) trackRepo.delete(trackQuery.get());
		
		return "redirect:/tracks";
	}
}

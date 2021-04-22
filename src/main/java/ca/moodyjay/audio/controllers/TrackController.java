package ca.moodyjay.audio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

import ca.moodyjay.audio.beans.*;
import ca.moodyjay.audio.repositories.*;

@Controller
public class TrackController {

	@Autowired
	private TrackRepository trackRepo;
	
	@Autowired
	private LabelRepository labelRepo;
	
	@Autowired
	private RatingRepository ratingRepo;
	
	@Autowired
	private UserRepository userRepo;
	
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
	public String addTrack(@ModelAttribute Track track, Authentication auth) {
		Label label = labelRepo.findById(track.getLabel().getId()).get();
		//Optional<Track> trackQuery = trackRepo.findById(track.getSpotifyId());
		
		// assign the label to the track if added by admin
		if (auth != null && auth.getName().toUpperCase().equals("ADMIN")) {
			track.setLabel(label);
		} else {
			track.setLabel(null);
		}
		
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
		if (trackQuery.isPresent()) {
			trackRepo.delete(trackQuery.get());
		}
		
		return "redirect:/tracks";
	}
	
	@PostMapping("/rateTrack")
	public String rateTrack(@ModelAttribute Track track, Authentication auth) {
		Label label = labelRepo.findById(track.getLabel().getId()).get();
		Optional<Track> trackQuery = trackRepo.findById(track.getSpotifyId());
		
		// assign the label to the track if added by admin
		if (auth != null && auth.getName().toUpperCase().equals("ADMIN")) {
			track.setLabel(label);
		} else {
			track.setLabel(null);
		}
		
		// save track in database if not already there
		if (trackQuery.isPresent()) {
			track = trackQuery.get();
		} else {
			trackRepo.save(track);
		}
		
		// save as rating if admin didn't one to label
		if (auth == null || !auth.getName().toUpperCase().equals("ADMIN")) {
			ratingRepo.save(Rating.builder()
					.label(label)
					.track(track)
					.user((auth != null) ? userRepo.findByUsername(auth.getName()) : null)
					.build());
		}
		
		return "redirect:/search";
	}
}

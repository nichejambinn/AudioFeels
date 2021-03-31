package ca.moodyjay.audio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.moodyjay.audio.beans.Track;
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
		model.addAttribute("tracks", trackRepo.findAll());
		model.addAttribute("labels", labelRepo.findAll());
		return "tracks.html";
	}
	
	@PostMapping("/tracks")
	public String addTrack() {
		Track track = Track.builder()
						.spotifyId("TESTESTESTEST")
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
	
	@GetMapping("/deleteTrack/{id}")
	public String deleteTrack(@PathVariable String id) {
		Track track = trackRepo.findById(id).get();
		trackRepo.delete(track);
		
		return "redirect:/tracks";
	}
}

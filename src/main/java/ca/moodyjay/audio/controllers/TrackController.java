package ca.moodyjay.audio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ca.moodyjay.audio.repositories.TrackRepository;

@Controller
public class TrackController {

	@Autowired
	private TrackRepository trackRepo;
	
	@GetMapping("/tracks")
	public String goTracks(Model model) {
		model.addAttribute("tracks", trackRepo.findAll());
		return "tracks.html";
	}
}

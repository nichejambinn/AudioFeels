package ca.moodyjay.audio.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class ModelController {
	
	@GetMapping("/model")
	public String goModel(Model model, RestTemplate restTemplate) {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
		
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));        
		messageConverters.add(converter);  
		restTemplate.setMessageConverters(messageConverters); 
		
		ResponseEntity<String[]> responseEntity = restTemplate.getForEntity(
				"http://localhost:5000/get_accuracy_k_value", String[].class);
		
		ResponseEntity<String> responseEntity2 = restTemplate.getForEntity(
				"http://localhost:5000/get_num_points", String.class);
		
		model.addAttribute("accuracy", responseEntity.getBody()[0]);
		model.addAttribute("k", responseEntity.getBody()[1]);
		
		model.addAttribute("numPoints", responseEntity2.getBody());
		
		return "model.html";
	}
}

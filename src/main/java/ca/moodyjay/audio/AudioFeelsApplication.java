package ca.moodyjay.audio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AudioFeelsApplication {

	public static void main(String[] args) {
		System.out.println(encodePassword("eventualdeliciousopportuneendgame"));
		SpringApplication.run(AudioFeelsApplication.class, args);
	}
	
	public static String encodePassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}
}

package ca.moodyjay.audio.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.moodyjay.audio.beans.Track;

public interface TrackRepository extends CrudRepository<Track, String> {
	public List<Track> findAllByOrderByTrackNameAsc();
}

package ca.moodyjay.audio.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.moodyjay.audio.beans.Track;

public interface TrackRepository extends CrudRepository<Track, String> {

}

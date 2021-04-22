package ca.moodyjay.audio.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.moodyjay.audio.beans.Rating;

public interface RatingRepository extends CrudRepository<Rating, Integer> {

}

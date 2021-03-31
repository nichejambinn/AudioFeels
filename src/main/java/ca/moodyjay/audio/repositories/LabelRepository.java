package ca.moodyjay.audio.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.moodyjay.audio.beans.Label;

public interface LabelRepository extends CrudRepository<Label, Integer> {

}

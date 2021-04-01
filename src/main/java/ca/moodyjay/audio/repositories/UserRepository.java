package ca.moodyjay.audio.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.moodyjay.audio.beans.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	public User findByUsername(String username);
}

package ca.krystasalera.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import ca.krystasalera.domain.Post;

public interface PostService {
	
	Post save(Post post);
	List<Post> getAllByUser(String user);
	
	List<Post> getAll();

}

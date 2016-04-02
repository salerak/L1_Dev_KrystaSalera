package ca.krystasalera.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import ca.krystasalera.domain.Post;

public interface PostService {
	
	Post savePost(Post post);
	List<Post> findAllByUser(String user);

	
	Post getById(int uid);
	

	Post findLatestId();
	

	List<Post> findAllPosts();
	Post findById(int id);
	boolean isPostExist(Post post);
	void updatePost(Post currentPost);
	void deletePostById(long id);
	void deleteAllPosts();

}

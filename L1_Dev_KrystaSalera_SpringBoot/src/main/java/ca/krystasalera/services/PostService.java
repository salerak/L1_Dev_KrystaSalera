package ca.krystasalera.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ca.krystasalera.domain.Post;

public interface PostService {
	
	Post savePost(Post post);
	List<Post> findAllByUser(String user);

	void incrementRank(int start_rank);
	

	Post getById(int uid);
	
	List<Post> findLatestPost(Pageable pageable);
	
	

	List<Post> findAllPosts();
	Post findById(int id);
	boolean isPostExist(Post post);
	void updatePost(Post currentPost);
	void deletePostById(long id);
	void deleteAllPosts();

}

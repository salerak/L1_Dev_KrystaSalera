package ca.krystasalera.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ca.krystasalera.domain.Post;
//
//@Repository
//public class PostRepository {
//
//	public Post getPost(int id){
//		
//		Post post = new Post();
//		post.setContent("Test content");
//		
//		return post;
//		//add db logic here
//		
//	}

public interface PostRepository extends JpaRepository<Post, String> {
	
	@Query("from Post p where p.userAcctName = ?1 order by created desc")
	List<Post> getAllByUser(String user);
	//@Query("from Post order by created desc")
	List<Post> getAll();
	//@Query("from Post p where p.uid= ?1")
	Post getById(int uid);
	
}

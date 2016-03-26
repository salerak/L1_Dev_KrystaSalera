package ca.krystasalera.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

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
	
}

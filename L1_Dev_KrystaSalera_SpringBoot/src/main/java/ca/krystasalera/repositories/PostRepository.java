package ca.krystasalera.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional 
	@Modifying(clearAutomatically=false ) 
	@Query( "UPDATE Post p  SET p.displayOrder = p.displayOrder + 1 WHERE p.displayOrder > ?1")
	void incrementRank(int start_rank);
	

	List<Post> findLatestPost(Pageable pageable);
	
	//Post findTopOrderByUidDesc(int uid);
	

	@Query("from Post p where p.userAcctName = ?1 order by displayOrder")
	List<Post> findAllByUser(String user);

	Post findById(int uid);

	List<Post> findAllPosts();

	// @Query("select top 1 uid from Post p order by uid desc ")
	// int findLatestId();

}

package ca.krystasalera.services;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.krystasalera.domain.Post;
import ca.krystasalera.repositories.PostRepository;

@Service
// @Validated
public class PostServiceImpl implements PostService {

	// use Dependecy injection here
	private final PostRepository postRepository;

	@Inject
	public PostServiceImpl(final PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	@Transactional
	public Post savePost(@NotNull final Post post) {
		return postRepository.save(post);
	}

	@Override
	public List<Post> findAllByUser(String user) {
		return postRepository.findAllByUser(user);
	}

	@Override
	public List<Post> findAllPosts() {
		return postRepository.findAllPosts();
	}

	@Override
	public Post getById(int uid) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Post findById(int id) {
		return postRepository.findById(id);
	}

	@Override
	public boolean isPostExist(Post post) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updatePost(Post currentPost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePostById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllPosts() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void incrementRank(int start_rank) {
		postRepository.incrementRank(start_rank);
		
	}

	@Override
	public List<Post> findLatestPost(Pageable pageable) {
		return postRepository.findLatestPost(pageable);
	}


	

}

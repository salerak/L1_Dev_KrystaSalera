package ca.krystasalera.services;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.krystasalera.domain.Post;
import ca.krystasalera.repositories.PostRepository;

@Service
//@Validated
public class PostServiceImpl implements PostService {

	//use Dependecy injection here
	private final PostRepository postRepository;
	
	@Inject
	public PostServiceImpl(final PostRepository postRepository){
		this.postRepository=postRepository;
	}
	
    @Override
    @Transactional
    public Post save(@NotNull  final Post post) {
        return postRepository.save(post);
    }

	@Override
	public List<Post> getAllByUser(String user) {
		return postRepository.getAllByUser(user);
	}

	@Override
	public List<Post> getAll() {
		return postRepository.getAll();
	}



}

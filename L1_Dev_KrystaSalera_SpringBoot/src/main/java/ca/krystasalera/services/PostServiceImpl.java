package ca.krystasalera.services;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import ca.krystasalera.domain.Post;
import ca.krystasalera.repositories.PostRepository;

@Service
@Validated
public class PostServiceImpl implements PostService {

	//use Dependecy injection here
	private final PostRepository postRepository;
	
	@Inject
	public PostServiceImpl(final PostRepository postRepository){
		this.postRepository=postRepository;
	}
	
    @Override
    @Transactional
    public Post save(@NotNull @Valid final Post post) {
        return postRepository.save(post);
    }



}

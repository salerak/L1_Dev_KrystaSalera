package ca.krystasalera.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.krystasalera.L1DevKrystaSaleraSpringBootApplication;
import ca.krystasalera.domain.Post;
import ca.krystasalera.repositories.PostRepository;
import ca.krystasalera.services.PostService;

@RequestMapping(value = "/api/*")
@RestController
public class PostApiController {

	static final Logger logger = LogManager.getLogger(L1DevKrystaSaleraSpringBootApplication.class.getName());
	private final PostService postService;
	

	@Inject
	public PostApiController(final PostService postService) {
		this.postService = postService;
	}

	@RequestMapping(value = "getText", params = { "text" })
	@ResponseBody
	public String getText(@RequestParam("text") String text) {

		// postService.save(post);

		return "Hello " + text;
	}

//	// CREATE
//		@RequestMapping(value = "create", method = RequestMethod.POST)
//		@ResponseBody
//		public String createPost(@RequestBody Post webpost)
//				   throws Exception {
//			
//			 return "creation successful: " + webpost.getContent();
//			
//		}
//	
	
	
	// CREATE
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public Post createPost(String content,String userAcctName, String city) {
		Post post = new Post(0, 1, 0, content,content, new Date(),userAcctName,
			city, null, null, null);
		try {
			postService.save(post);
//			postRepository.save(post);
		} catch (Exception e) {
			logger.error(e.getMessage());
			// return e.getMessage();
		}
		// return "creation successful: " + String.valueOf(post.getUid());
		return post;
	}
	
	
	@RequestMapping(value = "getAllForUser", params = { "user" },method = RequestMethod.GET)
	@ResponseBody
	public List<Post> getAllByUser(@RequestParam("user") String user) {

		return postService.getAllByUser(user);
	}

}

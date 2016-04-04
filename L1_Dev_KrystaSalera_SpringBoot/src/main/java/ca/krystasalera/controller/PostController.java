package ca.krystasalera.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import ca.krystasalera.domain.Post;
import ca.krystasalera.services.PostService;

@RequestMapping(value = "/")
@Controller
public class PostController {

	static final Logger logger = LogManager.getLogger(PostController.class.getName());

	public static final String REST_SERVICE_URI = "http://localhost:8080/api"; // "http://localhost:8080/api";

	private final PostService postService;

	@Inject
	public PostController(final PostService postService) {
		this.postService = postService;
	}

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	@RequestMapping(value = "mvchome", method = RequestMethod.GET)
	public String mvchome(Model model) {
		Post post = new Post();

		model.addAttribute("post", post);
		return "mvchome";
	}

	////////////////// REST TEMPLATE /////////////////////////////

	@RequestMapping(value = "mvchome", method = RequestMethod.POST)
	// public String create(@Valid Post post, BindingResult bindingResult, Model
	// model) {
	public ModelAndView create(@Valid Post post) {
		// if (bindingResult.hasErrors()) {
		// ModelAndView mav = new ModelAndView("mvchome");
		// Post newpost = new Post();
		//
		// mav.addObject("post", newpost);
		// return mav;
		// }
		// Post post = new Post(0, 1, 0, content,content, new
		// Date(),userAcctName,city, null, null, null);
		post.setParentId(0);
		post.setDisplayOrder(1);
		post.setIndentLevel(0);
		post.setSubject(post.getContent());
		post.setCreated(new Date());
		post.setLatitude("Lat");
		post.setLongtitude("Long");
		post.setTemperature("90 C");

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Post> responseEntity = restTemplate.postForEntity(REST_SERVICE_URI + "/post", post, Post.class);

		// postService.savePost(post);
		// RestTemplate restTemplate = new RestTemplate();
		// String url="http://localhost:8080/api/create/";
		// Post postList=restTemplate.postForObject(url, post,Post.class);
		ModelAndView mav = new ModelAndView("mvchome");

		mav.addObject("postsList", responseEntity.getBody());
		Post newpost = new Post();

		mav.addObject("post", newpost);
		return mav;

	}


	@RequestMapping(value = "getall")
	// public String create(@Valid Post post, BindingResult bindingResult, Model
	// model) {
	public ModelAndView getAll() {
		// if (bindingResult.hasErrors()) {
		// return "mvchome";
		// }

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Post>> responseEntity = restTemplate.exchange(REST_SERVICE_URI + "/post", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Post>>() {
				});
		// ResponseEntity<Post[]> responseEntity =
		// restTemplate.getForEntity(REST_SERVICE_URI+"/post", Post[].class);
		List<Post> postsMap = responseEntity.getBody();
		MediaType contentType = responseEntity.getHeaders().getContentType();
		HttpStatus statusCode = responseEntity.getStatusCode();

		if (postsMap != null) {
			for (Post map : postsMap) {
				logger.info("Post : date=" + map.getCreated());
			}
		} else {
			logger.info("No user exist----------");
		}

		// List<Post> postList = postService.findAllPosts();
		ModelAndView mav = new ModelAndView("mvchome");
		mav.addObject("postsList", postsMap);
		Post newpost = new Post();

		mav.addObject("post", newpost);
		return mav;

	}

	@RequestMapping(value = "getallbyuser", params = "user")
	// public String create(@Valid Post post, BindingResult bindingResult, Model
	// model) {
	public ModelAndView getAllByUser(@RequestParam("user") String user) {
		// if (bindingResult.hasErrors()) {
		// return "mvchome";
		// }

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Post>> responseEntity = restTemplate.exchange(REST_SERVICE_URI + "/postbyuser/" + user,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {
				});
		List<Post> postList = responseEntity.getBody();
		// List<Post> postList = postService.findAllByUser(user);
		ModelAndView mav = new ModelAndView("mvchome");
		mav.addObject("postsList", postList);
		Post newpost = new Post();

		mav.addObject("post", newpost);
		return mav;

	}
	
	
	@RequestMapping(value = "replytopost/{id}", method= RequestMethod.GET)
	// public String create(@Valid Post post, BindingResult bindingResult, Model
	// model) {
	public ModelAndView replyToPost(@PathVariable int id) {
		// if (bindingResult.hasErrors()) {
		// return "mvchome";
		// }

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Post> responseEntity = restTemplate.getForEntity(REST_SERVICE_URI + "/post/" + id,Post.class);
		Post post = responseEntity.getBody();
		
		//get parent item
		//return parent item in reply to post view to be created
		//in view display form 
		//form submit use post of replytoform/id
		
		
		
		
		// List<Post> postList = postService.findAllByUser(user);
		ModelAndView mav = new ModelAndView("replytopost");
		mav.addObject("postsList", post);
		Post newpost = new Post();

		mav.addObject("post", newpost);
		return mav;

	}

	@RequestMapping(value = "replytopost/{id}", method= RequestMethod.POST)
	// public String create(@Valid Post post, BindingResult bindingResult, Model
	// model) {
	public ModelAndView createReplyToPost(@PathVariable int id) {
		// if (bindingResult.hasErrors()) {
		// return "mvchome";
		// }

		//obtain parent object via id using rest call
		//create new object and set its parent id to parent object id
		//set indent level to 1
		//store in db
		//show newly created post via mvchome
		
		
		//modify get all query to get all and organize by parent then child
		//modify mvchome view to show child items
		//limit child depth to 1 or 2? per parent
		
		
		
		
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Post>> responseEntity = restTemplate.exchange(REST_SERVICE_URI + "/postbyuser/" + id,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {
				});
		List<Post> postList = responseEntity.getBody();
		// List<Post> postList = postService.findAllByUser(user);
		ModelAndView mav = new ModelAndView("mvchome");
		mav.addObject("postsList", postList);
		Post newpost = new Post();

		mav.addObject("post", newpost);
		return mav;

	}
}

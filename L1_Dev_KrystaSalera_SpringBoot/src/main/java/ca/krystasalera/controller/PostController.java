package ca.krystasalera.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import ca.krystasalera.domain.Post;
import ca.krystasalera.services.PostService;

@Controller
public class PostController {

	private final PostService postService;

	@Inject
	public PostController(final PostService postService) {
		this.postService = postService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/mvchome", method = RequestMethod.GET)
	public String mvchome(Model model) {
		Post post = new Post();

		model.addAttribute("post", post);
		return "mvchome";
	}

	@RequestMapping(value = "/mvchome", method = RequestMethod.POST)
	// public String create(@Valid Post post, BindingResult bindingResult, Model
	// model) {
	public ModelAndView create(@Valid Post post) {
		// if (bindingResult.hasErrors()) {
		// return "mvchome";
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
		postService.savePost(post);
//		 RestTemplate restTemplate = new RestTemplate();
//		   String url="http://localhost:8080/api/create/";    
//		  Post postList=restTemplate.postForObject(url, post,Post.class);
		ModelAndView mav = new ModelAndView("mvchome");

		mav.addObject("postsList", post);
		Post newpost = new Post();

		mav.addObject("post", newpost);
		return mav;

	}

	@RequestMapping(value = "/getAll")
	// public String create(@Valid Post post, BindingResult bindingResult, Model
	// model) {
	public ModelAndView getAll() {
		// if (bindingResult.hasErrors()) {
		// return "mvchome";
		// }

		
//		 RestTemplate restTemplate = new RestTemplate();
//		   String url="http://localhost:8080/api/getAll/";    
//		  List<LinkedHashMap> postList= restTemplate.getForObject(url, List.class);
		      //   return new ModelAndView("listUsers", "users", users);
		 
		
		List<Post> postList = postService.findAllPosts();
		ModelAndView mav = new ModelAndView("mvchome");
		mav.addObject("postsList", postList);
		Post newpost = new Post();

		mav.addObject("post", newpost);
		return mav;

	}
	
	@RequestMapping(value = "/getAllByUser")
	// public String create(@Valid Post post, BindingResult bindingResult, Model
	// model) {
	public ModelAndView getAllByUser() {
		// if (bindingResult.hasErrors()) {
		// return "mvchome";
		// }

		String user="";
		List<Post> postList = postService.findAllByUser(user);
		ModelAndView mav = new ModelAndView("mvchome");
		mav.addObject("postsList", postList);
		Post newpost = new Post();

		mav.addObject("post", newpost);
		return mav;

	}

}

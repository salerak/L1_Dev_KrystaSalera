package ca.krystasalera.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.krystasalera.services.PostService;

@Controller
public class PostController {

	private final PostService postService;
	
	@Inject
	public PostController(final PostService postService){
		this.postService=postService;
	}
	
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	

}

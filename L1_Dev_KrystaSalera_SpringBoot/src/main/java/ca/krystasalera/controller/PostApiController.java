package ca.krystasalera.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ca.krystasalera.L1DevKrystaSaleraSpringBootApplication;
import ca.krystasalera.domain.Post;
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

		return  text;
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
			postService.savePost(post);
//			postRepository.save(post);
		} catch (Exception e) {
			logger.error(e.getMessage());
			// return e.getMessage();
		}
		// return "creation successful: " + String.valueOf(post.getUid());
		return post;
	}
	
	
	@RequestMapping(value = "getAll",method = RequestMethod.GET)
	@ResponseBody
	public List<Post> getAll() {

		return postService.findAllPosts();
	}
	
	
	@RequestMapping(value = "getAllForUser", params = { "post" },method = RequestMethod.GET)
	@ResponseBody
	public List<Post> getAllByUser(@RequestParam("post") String post) {

		return postService.findAllByUser(post);
	}
	
	
	////////////////////////////////////////////////
	
	
	 //-------------------Retrieve All Posts--------------------------------------------------------
    
    @RequestMapping(value = "/post/", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> listAllUsers() {
        List<Post> posts = postService.findAllPosts();
        if(posts.isEmpty()){
            return new ResponseEntity<List<Post>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single Post--------------------------------------------------------
     
    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> getUser(@PathVariable("id") int id) {
        System.out.println("Fetching Post with id " + id);
        Post post = postService.findById(id);
        if (post == null) {
            System.out.println("Post with id " + id + " not found");
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a Post--------------------------------------------------------
     
    @RequestMapping(value = "/post/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Post post,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Post " + post.toString());
 
        if (postService.isPostExist(post)) {
            System.out.println("A Post with name " + post.toString() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        postService.savePost(post);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/post/{id}").buildAndExpand(post.getUid()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a Post --------------------------------------------------------
     
    @RequestMapping(value = "/post/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Post> updateUser(@PathVariable("id") int id, @RequestBody Post post) {
        System.out.println("Updating Post " + id);
         
        Post currentPost = postService.findById(id);
         
        if (currentPost==null) {
            System.out.println("Post with id " + id + " not found");
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }
 
        currentPost.setUserAcctName(post.getUserAcctName());
        currentPost.setCity(post.getCity());
        currentPost.setContent(post.getContent());
         
        postService.updatePost(currentPost);
        return new ResponseEntity<Post>(currentPost, HttpStatus.OK);
    }
 
    //------------------- Delete a Post --------------------------------------------------------
     
    @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Post> deleteUser(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting Post with id " + id);
 
        Post post = postService.findById(id);
        if (post == null) {
            System.out.println("Unable to delete. Post with id " + id + " not found");
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }
 
        postService.deletePostById(id);
        return new ResponseEntity<Post>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All Users --------------------------------------------------------
     
    @RequestMapping(value = "/post/", method = RequestMethod.DELETE)
    public ResponseEntity<Post> deleteAllUsers() {
        System.out.println("Deleting All Users");
 
        postService.deleteAllPosts();
        return new ResponseEntity<Post>(HttpStatus.NO_CONTENT);
    }
 
	
	
	
	
	
	
	
	
	
	
	
	

}

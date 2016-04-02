package ca.krystasalera.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ca.krystasalera.services.PostService;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = PostController.class)
@WebAppConfiguration
public class PostControllerTests {

	private MockMvc mockMvc;
	@InjectMocks
	private PostController postController;

	
	//private PostRepository postRepository;
	@Mock
	private  PostService postService;

	
	@Before
	public void setup() {
		
		//Process mock annotations
		MockitoAnnotations.initMocks(this);
		
		//setup spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
		
		
		//postService= new PostServiceImpl(postRepository);
		//postController = new PostController(postService);
		//mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
	}

	@Test
	public void testHomeLoads() throws Exception {

		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("home"));

	}

	
	
	
//	@Test
//	public void testGetPost() {
//		PostServiceImpl repository = new PostServiceImpl();
//		Post post = repository.getPost(0);
//		assertEquals(post.getContent(), "Test content");
//
//	}
	
	
	
	
	
	
}

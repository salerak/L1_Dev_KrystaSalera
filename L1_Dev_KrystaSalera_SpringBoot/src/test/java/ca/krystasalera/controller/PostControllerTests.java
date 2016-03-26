//package ca.krystasalera.controller;
//
//import static org.junit.Assert.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import ca.krystasalera.domain.Post;
//import ca.krystasalera.services.PostServiceImpl;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = PostController.class)
//@WebAppConfiguration
//public class PostControllerTests {
//
//	private MockMvc mockMvc;
//	private PostController postController;
//
//	@Before
//	public void setup() {
//		postController = new PostController();
//		mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
//	}
//
//	@Test
//	public void testHomeLoads() throws Exception {
//
//		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("home"));
//
//	}
//
//	@Test
//	public void testGetPost() {
//		PostServiceImpl repository = new PostServiceImpl();
//		Post post = repository.getPost(0);
//		assertEquals(post.getContent(), "Test content");
//
//	}
//	
//	
//	
//	
//	
//	
//}

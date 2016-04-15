package ca.krystasalera.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import ca.krystasalera.L1DevKrystaSaleraSpringBootApplication;
import ca.krystasalera.domain.Post;
import ca.krystasalera.domain.PostBuilder;
import ca.krystasalera.services.PostService;
import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { PostController.class, L1DevKrystaSaleraSpringBootApplication.class })
@WebAppConfiguration
public class PostControllerTests {

	private MockMvc mockMvc;
	@InjectMocks
	private PostController postController;

	// private PostRepository postRepository;
	@Mock
	private PostService postServiceMock;
	@Mock
	private ModelAndView mav;

	@Before
	public void setup() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".html");

		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		Mockito.reset(postServiceMock);
		Mockito.reset(mav);
		mav = new ModelAndView();

		// setup spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(postController).setViewResolvers(viewResolver).build();

		// postService= new PostServiceImpl(postRepository);
		// postController = new PostController(postService);
		// mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
	}

	@Test
	public void testHomeLoads() throws Exception {

		mockMvc.perform(get("/home")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("home"));

	}

	@Test
	public void testMVCHomeLoadWithNoDBData() throws Exception {

		mockMvc.perform(get("/mvchome")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("mvchome"))
				.andExpect(model().attributeDoesNotExist("postsList"));

	}

	@Test
	public void testEmptyFieldsSubmission() throws Exception {

		mockMvc.perform(post("/mvchome").param("userAcctName", "").param("city", "").param("content", ""))
				.andDo(print()).andExpect(view().name("mvchome"))
				.andExpect(model().attributeHasFieldErrors("post", "userAcctName"))
				.andExpect(model().attributeHasFieldErrors("post", "city"))
				.andExpect(model().attributeHasFieldErrors("post", "content"));

	}

	@Test
	public void testValidFieldsSubmission() throws Exception {

		// Post newPost = new
		// PostBuilder().withUid(0).withParentId(0).withDisplayOrder(0).withIndentLevel(0)
		// .withContent(null).withSubject(null).withCreated(new Date())
		// .withUserAcctName(null).withCity(null).withLongtitude(null).withLatitude(null)
		// .withTemperature(null).Build();

		mockMvc.perform(post("/mvchome").param("userAcctName", "Krysta").param("city", "Toronto").param("content",
				"Hello World")).andDo(print()).andExpect(view().name("mvchome"))
				.andExpect(model().attributeExists("postsList"))
				.andExpect(
						model().attribute("postsList",
								allOf(hasProperty("userAcctName", is("Krysta")), hasProperty("city", is("Toronto")),
										hasProperty("content", is("Hello World")))))
				.andExpect(model().attributeExists("post"));
	}

	@Test
	public void testGetAllBtnClick() throws Exception {

		mockMvc.perform(get("/getall")).andDo(print()).andExpect(view().name("mvchome"))
				.andExpect(model().attributeExists("post"));
	}

	@Test
	public void testGetAllByUser() throws Exception {

		mockMvc.perform(get("/getallbyuser").param("user", "Krysta")).andDo(print()).andExpect(view().name("mvchome"))
				.andExpect(model().attributeExists("post"));
	}

	
	@Test
	public void testNoPostFoundForReplyToPost() throws Exception {

//		try {
//			mockMvc.perform(get("/replytopost/{id}", 1));
//		} catch (HttpClientErrorException ex) {
//			assertThat(ex.getStatusCode(), is(equalTo(404)));
//
//		}
		mockMvc.perform(get("/replytopost/{id}", 1))
		 .andDo(print())
		 .andExpect(view().name("replytopost")).andExpect(model().attributeExists("postsList"))
		 .andExpect(model().attributeExists("post"));

	}

	
	//erroring on non existing object in get call from rest api
	@Test
	public void testEmptyCreateReplyToPostSubmission() throws Exception {
		
		
		 Post first = new PostBuilder().withUid(1).withParentId(0).withDisplayOrder(1).withIndentLevel(1)
					 .withContent("First Content").withSubject("First Subject").withCreated(new Date())
					 .withUserAcctName("First Account").withCity("First City").withLongtitude("-79.42").withLatitude("43.7")
					 .withTemperature("7.4 C").Build();
					
					
				
					
					 when(postServiceMock.findById(1)).thenReturn(first);
					 // doReturn(Arrays.asList(first,
					 // second)).when(postServiceMock).findAllPosts();

		mockMvc.perform(post("/replytopost/{id}",1).param("userAcctName", "").param("city", "").param("content", ""))
				.andDo(print()).andExpect(view().name("replytopost"))
				.andExpect(model().attributeHasFieldErrors("post", "userAcctName"))
				.andExpect(model().attributeHasFieldErrors("post", "city"))
				.andExpect(model().attributeHasFieldErrors("post", "content"));

	}

	@Test
	public void testValidCreateReplyToPostSubmission() throws Exception {

//		 Post newPost = new PostBuilder().withUid(0).withParentId(0).withDisplayOrder(0).withIndentLevel(0)
//		 .withContent(null).withSubject(null).withCreated(new Date())
//		 .withUserAcctName(null).withCity(null).withLongtitude(null).withLatitude(null)
//		 .withTemperature(null).Build();
		
		 Post first = new PostBuilder().withUid(1).withParentId(0).withDisplayOrder(1).withIndentLevel(1)
				 .withContent("First Content").withSubject("First Subject").withCreated(new Date())
				 .withUserAcctName("First Account").withCity("First City").withLongtitude("-79.42").withLatitude("43.7")
				 .withTemperature("7.4 C").Build();
				
		
		 when(postServiceMock.findById(1)).thenReturn(first);
//			  doReturn(Arrays.asList(first,
//			  second)).when(postServiceMock).findAllPosts();

		mockMvc.perform(post("/replytopost/{id}",1).param("userAcctName", "Krysta").param("city", "Toronto").param("content",
				"Hello World")).andDo(print()).andExpect(view().name("mvchome"))
				.andExpect(model().attributeExists("postsList"))
				.andExpect(
						model().attribute("postsList",
								allOf(hasProperty("userAcctName", is("Krysta")), hasProperty("city", is("Toronto")),
										hasProperty("content", is("Hello World")))))
				.andExpect(model().attributeExists("post"));
	}

	

	// @Test
	// public void getPosts_ShouldAddPostEntryToModelAndRenderMVCHomeView()
	// throws Exception {
	// Post first = new
	// PostBuilder().withUid(1).withParentId(0).withDisplayOrder(1).withIndentLevel(1)
	// .withContent("First Content").withSubject("First
	// Subject").withCreated(new Date())
	// .withUserAcctName("First Account").withCity("First
	// City").withLongtitude("-79.42").withLatitude("43.7")
	// .withTemperature("7.4 C").Build();
	//
	// Post second = new
	// PostBuilder().withUid(2).withParentId(0).withDisplayOrder(1).withIndentLevel(1)
	// .withContent("Second Content").withSubject("Second
	// Subject").withCreated(new Date())
	// .withUserAcctName("Second Account").withCity("Second
	// City").withLongtitude("-79.42")
	// .withLatitude("43.7").withTemperature("7.4 C").Build();
	//
	// List<Post> post = Arrays.asList(first, second);
	//
	// when(postServiceMock.findAllPosts()).thenReturn(post);
	// // doReturn(Arrays.asList(first,
	// // second)).when(postServiceMock).findAllPosts();
	//
	// mockMvc.perform(get("/mvchome")).andExpect(status().isOk()).andExpect(view().name("mvchome"))
	// .andExpect(model().attribute("postsList", hasSize(2)))
	// .andExpect(model().attribute("postsList",
	// hasItem(allOf(hasProperty("uid", is(1)), hasProperty("content", is("First
	// Content")),
	// hasProperty("subject", is("First Subject")),
	// hasProperty("userAcctName", is("First Account")),
	// hasProperty("city", is("First City"))))))
	// .andExpect(model().attribute("postsList",
	// hasItem(allOf(hasProperty("uid", is(2)), hasProperty("content",
	// is("Second Content")),
	// hasProperty("subject", is("Second Subject")),
	// hasProperty("userAcctName", is("Second Account")),
	// hasProperty("city", is("Second City"))))));
	//
	// verify(postServiceMock, times(1)).findAllPosts();
	// verifyNoMoreInteractions(postServiceMock);
	// }

	// @Test
	// public void testGetPost() {
	// PostServiceImpl repository = new PostServiceImpl();
	// Post post = repository.getPost(0);
	// assertEquals(post.getContent(), "Test content");
	//
	// }

}

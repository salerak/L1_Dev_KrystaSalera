package ca.krystasalera.controller;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.RequestToViewNameTranslator;

import ca.krystasalera.domain.Post;
import ca.krystasalera.domain.PostBuilder;
import ca.krystasalera.services.PostService;
import ca.krystasalera.services.PostServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = PostApiController.class)
@WebAppConfiguration
public class PostApiControllerTests {

	private MockMvc mockMvc;
	@InjectMocks
	private PostApiController postApiController;

	// private PostRepository postRepository;
	@Mock
	private PostService postServiceMock;
	@Mock
	private RestTemplate restTemplate = new RestTemplate();

	private MockRestServiceServer mockServer;

	@Before
	public void setup() {

		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// setup spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(postApiController).build();

		mockServer = MockRestServiceServer.createServer(restTemplate);

	}

	@Test
	public void testGetText() throws Exception {
		String text = "Hello Test";

		MvcResult result = mockMvc.perform(get("/api/getText?text={text}", text)).andExpect(status().isOk())
				.andReturn();

		String content = result.getResponse().getContentAsString();
		assertEquals(text, content);

		// MvcResult result =
		// mockMvc.perform(get("/api/getText?text={text}",text)).andExpect(status().isOk()).andReturn();

		// String content = result.getResponse().getContentAsString();

	}

	///// REST UNIT TEST/////

	@Test
	public void getAllPosts_NoPostsFound_ShouldReturnStatus204() throws Exception {

		Post first = new PostBuilder().withUid(1).withParentId(0).withDisplayOrder(1).withIndentLevel(1)
				.withContent("First Content").withSubject("First Subject").withCreated(new Date())
				.withUserAcctName("First Account").withCity("First City").withLongtitude("-79.42").withLatitude("43.7")
				.withTemperature("7.4 C").Build();

		Post second = new PostBuilder().withUid(2).withParentId(0).withDisplayOrder(1).withIndentLevel(1)
				.withContent("Second Content").withSubject("Second Subject").withCreated(new Date())
				.withUserAcctName("Second Account").withCity("Second City").withLongtitude("-79.42")
				.withLatitude("43.7").withTemperature("7.4 C").Build();

		when(postServiceMock.findAllPosts()).thenReturn(Arrays.asList(first, second));
		//doReturn(Arrays.asList(first, second)).when(postServiceMock).findAllPosts();

		//mockMvc.perform(get("/api/post")).andDo(print()).andExpect(status().isNoContent());
		ResultActions resultActions = mockMvc.perform(get("/api/post/"))
		        .andExpect(status().isOk())
		        .andExpect(content().contentType(MediaType.APPLICATION_JSON));


		verify(postServiceMock, times(1)).findAllPosts();
		verifyNoMoreInteractions(postServiceMock);

		// postApiController.execute(Mockito.anyString(), Mockito.any(),
		// Mockito.any(), Mockito.any(), Mockito.any());
		//
		// Mockito.verify(template,
		// Mockito.times(1)).exchange(Mockito.anyString(), Mockito.<HttpMethod>
		// any(),
		// Mockito.<HttpEntity<?>> any(), Mockito.<Class<?>> any(),
		// Mockito.<String, String> anyMap());

		// mockServer.expect(requestTo("/post")).andExpect(method(HttpMethod.GET))
		// .andRespond(withSuccess("{ \"uid\": 1\"parentId\": 0\"displayOrder\":
		// 1\"indentLevel\": 1\"content\": \"Hello World\"\"subject\": \"Hello
		// World\"\"created\": \"Thu, 14 Apr 2016 00:20:58
		// EDT\"\"userAcctName\": \"Krysta\"\"city\": \"Toronto\"\"longtitude\":
		// \"-79.42\"\"latitude\": \"43.7\"\"coordinates\": \"Latitude:
		// 43.7,Longitude: -79.42\"\"temperature\": \"1.25 C\"}",
		// MediaType.APPLICATION_JSON));
		//
		//
		// Post post = restTemplate.getForObject("/post", Post.class);
		//
		// mockServer.verify();
	}

	@Test
	public void getAllPosts_PostsFound_ShouldReturnEntries() throws Exception {

		Post first = new PostBuilder().withUid(1).withParentId(0).withDisplayOrder(1).withIndentLevel(1)
				.withContent("First Content").withSubject("First Subject").withCreated(new Date())
				.withUserAcctName("First Account").withCity("First City").withLongtitude("-79.42").withLatitude("43.7")
				.withTemperature("7.4 C").Build();

		Post second = new PostBuilder().withUid(2).withParentId(0).withDisplayOrder(1).withIndentLevel(1)
				.withContent("Second Content").withSubject("Second Subject").withCreated(new Date())
				.withUserAcctName("Second Account").withCity("Second City").withLongtitude("-79.42")
				.withLatitude("43.7").withTemperature("7.4 C").Build();

		when(postServiceMock.findAllPosts()).thenReturn(Arrays.asList(first, second));

		mockMvc.perform(get("/api/post")).andExpect(status().isNoContent());

		// verify(postService, times(1)).findAllPosts();
		verifyNoMoreInteractions(postServiceMock);

	}

	// add unit test for parameterized post call
	// @Test
	// public void testCreatePost() throws Exception {
	//
	// mockMvc.perform(post("/api/create").content("{\"userName\":\"testUserDetails\",\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"));
	//
	// }

}

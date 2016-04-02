package ca.krystasalera.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ca.krystasalera.services.PostService;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = PostApiController.class)
@WebAppConfiguration
public class PostApiControllerTests {

	private MockMvc mockMvc;
	@InjectMocks
	private PostApiController postApiController;

	// private PostRepository postRepository;
	@Mock
	private PostService postService;

	@Before
	public void setup() {

		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// setup spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(postApiController).build();

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

	//add unit test for parameterized post call
//	@Test
//	public void testCreatePost() throws Exception {
//		
//		mockMvc.perform(post("/api/create").content("{\"userName\":\"testUserDetails\",\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"));
//          
//	}

}

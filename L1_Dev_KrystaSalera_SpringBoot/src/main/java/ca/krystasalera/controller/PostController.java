package ca.krystasalera.controller;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ca.krystasalera.domain.Post;
import ca.krystasalera.services.PostService;

@RequestMapping(value = "/")
@Controller
public class PostController {

	static final Logger logger = LogManager.getLogger(PostController.class.getName());

	public static final String REST_SERVICE_URI = "https://localhost:8080/api"; // "http://localhost:8080/api";

	private final PostService postService;

	private static final String[] HEADERS_TO_TRY = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };

	private static DecimalFormat df2 = new DecimalFormat("##.##");

	@Inject
	public PostController(final PostService postService) {
		this.postService = postService;
	}

	public static String getClientIpAddress(HttpServletRequest request) {
		for (String header : HEADERS_TO_TRY) {
			String ip = request.getHeader(header);
			if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
				logger.info("Client IP : " + ip);
				return ip;
			}
		}
		logger.info("*** CLIENT IP : " + request.getRemoteAddr());
		return request.getRemoteAddr();
	}

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home() {

		return "home";
	}

	////////////////////// USING REST TEMPLATE//////////////////////////////////

	@RequestMapping(value = "mvchome", method = RequestMethod.GET)
	public ModelAndView mvchome(Model model, HttpServletRequest request) {

		// to obtain user's IP
		// for (String header : HEADERS_TO_TRY) {
		// String ip = request.getHeader(header);
		// if (ip != null && ip.length() != 0 &&
		// !"unknown".equalsIgnoreCase(ip)) {
		// logger.info("Client IP : " + ip);
		// // return ip;
		// }
		// }
		// logger.info("Client IP : " + request.getRemoteAddr());
		// GetLocation gt= new GetLocation();
		// gt.getLocation(getClientIpAddress(request));
		// ServerLocation location =gt.getLocation("174.92.127.221");
		// System.out.println(location.toString());

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Post>> responseEntity = restTemplate.exchange(REST_SERVICE_URI + "/post", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Post>>() {
				});

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

		ModelAndView mav = new ModelAndView("mvchome");
		// model.addAttribute("postsList", postsMap);
		mav.addObject("postsList", postsMap);
		// model.addAttribute("post", new Post());
		mav.addObject("post", new Post());
		return mav;
		// return "mvchome";
	}

	@RequestMapping(value = "mvchome", method = RequestMethod.POST)
	// public String create(@Valid Post post, BindingResult bindingResult, Model
	// model) {
	public ModelAndView create(@Valid Post postData, BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("mvchome");
			// Post newpost = new Post();

			mav.addObject("post", postData);
			return mav;
		} else {

			// Post post = new Post(0, 1, 0, content,content, new
			// Date(),userAcctName,city, null, null, null);

			// GetLocation gt= new GetLocation();
			// //gt.getLocation(getClientIpAddress(request));
			// ServerLocation location =gt.getLocation("174.92.127.221");
			// System.out.println(location);

			RestTemplate restTemplate = new RestTemplate();

			String firstLetterCap = postData.getCity();
			firstLetterCap = firstLetterCap.substring(0, 1).toUpperCase() + firstLetterCap.substring(1).toLowerCase();
			postData.setCity(firstLetterCap);
			ResponseEntity<String> weatherEntity = restTemplate
					.getForEntity("http://api.openweathermap.org/data/2.5/weather?q=" + postData.getCity()
							+ "&appid=99e71dd6efdb22cccbbc0c775acc8289", String.class);
			String weather = weatherEntity.getBody();
			JSONObject weatherObject = (JSONObject) JSONValue.parse(weather);

			JSONObject tempObject = null;
			JSONObject coordObject = null;
			Object value = null;
			@SuppressWarnings("unchecked")
			Set<String> keySet = weatherObject.keySet();
			for (String key : keySet) {

				switch (key) {
				case "main":
					value = weatherObject.get(key);
					tempObject = (JSONObject) JSONValue.parse(value.toString());
					break;
				case "coord":
					value = weatherObject.get(key);
					coordObject = (JSONObject) JSONValue.parse(value.toString());
					break;

				}

			}

			String weatherConverted = "" + df2.format((Double.parseDouble(tempObject.get("temp").toString()) - 273.15));

			ResponseEntity<List<Post>> latestPostRresponseEntity = restTemplate.exchange(
					REST_SERVICE_URI + "/latestpost", HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Post>>() {
					});

			List<Post> manyPosts = latestPostRresponseEntity.getBody();
			Post latestPost;
			if (manyPosts.isEmpty()) {
				latestPost = null;
			} else {

				latestPost = latestPostRresponseEntity.getBody().get(0);
			}

			Post post = new Post();
			post.setParentId(0);
			if (latestPost == null) {
				post.setDisplayOrder(1);
			} else {

				post.setDisplayOrder(latestPost.getDisplayOrder() + 1);
			}
			post.setIndentLevel(1);
			post.setUserAcctName(postData.getUserAcctName());
			post.setSubject(postData.getContent());
			post.setContent(postData.getContent());
			post.setCreated(new Date());
			post.setCity(postData.getCity());
			post.setLatitude(coordObject.get("lat").toString());
			post.setLongtitude(coordObject.get("lon").toString());
			post.setCoordinates("Latitude: " + post.getLatitude() + ",Longitude: " + post.getLongtitude());
			post.setTemperature(weatherConverted + " C");

			ResponseEntity<Post> responseEntity = restTemplate.postForEntity(REST_SERVICE_URI + "/post", post,
					Post.class);

			ModelAndView mav = new ModelAndView("mvchome");

			mav.addObject("postsList", responseEntity.getBody());
			Post newpost = new Post();

			mav.addObject("post", newpost);

			return mav;

		}
	}

	@RequestMapping(value = "getall")

	public ModelAndView getAll() {

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Post>> responseEntity = restTemplate.exchange(REST_SERVICE_URI + "/post", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Post>>() {
				});

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

		ModelAndView mav = new ModelAndView("mvchome");
		mav.addObject("postsList", postsMap);
		Post newpost = new Post();

		mav.addObject("post", newpost);
		return mav;

	}

	@RequestMapping(value = "getallbyuser", params = "user")

	public ModelAndView getAllByUser(@RequestParam("user") String user) {

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Post>> responseEntity = restTemplate.exchange(REST_SERVICE_URI + "/postbyuser/" + user,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {
				});
		List<Post> postList = responseEntity.getBody();

		ModelAndView mav = new ModelAndView("mvchome");
		mav.addObject("postsList", postList);
		Post newpost = new Post();

		mav.addObject("post", newpost);
		return mav;

	}

	@RequestMapping(value = "replytopost/{id}", method = RequestMethod.GET)
	public ModelAndView replyToPost(@PathVariable int id) {

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Post> responseEntity = restTemplate.getForEntity(REST_SERVICE_URI + "/post/" + id, Post.class);
		Post post = responseEntity.getBody();

		ModelAndView mav = new ModelAndView("replytopost");
		mav.addObject("postsList", post);

		Post newpost = new Post();
		mav.addObject("post", newpost);
		return mav;

	}

	@RequestMapping(value = "replytopost/{id}", method = RequestMethod.POST)
	public ModelAndView createReplyToPost(@Valid Post postData, BindingResult bindingResult, @PathVariable int id,
			HttpServletRequest request) {

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Post> parentResponseEntity = restTemplate.getForEntity(REST_SERVICE_URI + "/post/" + id,
				Post.class);

		if (bindingResult.hasErrors()) {

			Post post = parentResponseEntity.getBody();

			ModelAndView mav = new ModelAndView("replytopost");
			mav.addObject("postsList", post);

			mav.addObject("post", postData);
			return mav;
		}

		Post parentPost = parentResponseEntity.getBody();

		if (parentPost == null) {
			logger.info("No parent post exist----------");
		}

		String firstLetterCap = postData.getCity();
		firstLetterCap = firstLetterCap.substring(0, 1).toUpperCase() + firstLetterCap.substring(1).toLowerCase();
		postData.setCity(firstLetterCap);
		ResponseEntity<String> weatherEntity = restTemplate
				.getForEntity("http://api.openweathermap.org/data/2.5/weather?q=" + postData.getCity()
						+ "&appid=99e71dd6efdb22cccbbc0c775acc8289", String.class);
		String weather = weatherEntity.getBody();
		JSONObject weatherObject = (JSONObject) JSONValue.parse(weather);

		JSONObject tempObject = null;
		JSONObject coordObject = null;
		Object value = null;
		@SuppressWarnings("unchecked")
		Set<String> keySet = weatherObject.keySet();
		for (String key : keySet) {

			switch (key) {
			case "main":
				value = weatherObject.get(key);
				tempObject = (JSONObject) JSONValue.parse(value.toString());
				break;
			case "coord":
				value = weatherObject.get(key);
				coordObject = (JSONObject) JSONValue.parse(value.toString());
				break;

			}

		}

		String weatherConverted = "" + df2.format((Double.parseDouble(tempObject.get("temp").toString()) - 273.15));

		Post post = new Post();
		post.setParentId(parentPost.getUid());
		post.setDisplayOrder(parentPost.getDisplayOrder() + 1);
		post.setIndentLevel(parentPost.getIndentLevel() + 2);
		post.setUserAcctName(postData.getUserAcctName());
		post.setSubject(parentPost.getContent());
		post.setContent(postData.getContent());
		post.setCreated(new Date());
		post.setCity(postData.getCity());
		post.setLatitude(coordObject.get("lat").toString());
		post.setLongtitude(coordObject.get("lon").toString());
		post.setCoordinates("Latitude: " + post.getLatitude() + ",Longitude: " + post.getLongtitude());
		post.setTemperature(weatherConverted + " C");

		postService.incrementRank(parentPost.getDisplayOrder());

		ResponseEntity<Post> childResponseEntity = restTemplate.postForEntity(REST_SERVICE_URI + "/post", post,
				Post.class);

		Post childPost = childResponseEntity.getBody();

		if (childPost == null) {
			logger.info("No child post exist----------");
		}

		ModelAndView mav = new ModelAndView(new RedirectView("/mvchome"));
		
		return mav;

	}
}

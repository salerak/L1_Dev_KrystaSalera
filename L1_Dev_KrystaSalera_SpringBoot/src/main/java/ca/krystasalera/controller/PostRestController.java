package ca.krystasalera.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostRestController {

	@RequestMapping(value="/getText", params = { "text" })
	@ResponseBody
	public String getText(@RequestParam("text") String text){
		return "Hello" + text;
	}
}

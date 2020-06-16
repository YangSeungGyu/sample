package com.sample.www.api.sample;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;



@RestController
public class ApiSendController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiSendController.class);
	
	@RequestMapping(value = "/api/sample/apiSend", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void apiSend(final HttpServletResponse response, final HttpServletRequest request) throws Exception {
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
		final JsonObject param = new JsonObject();
		param.addProperty("param1", "test1");
		param.addProperty("param2", "test2");
		
		//final HttpEntity<String> entity = new HttpEntity<>(null, headers); 디폴트
		final HttpEntity<String> entity = new HttpEntity<>(param.toString(), headers);
		try {
			RestTemplate rt = new RestTemplate(); 
			final Object content = rt.postForObject("http://localhost:8080/api/sample/test", entity, String.class);
			//content 푸는 방법 확인 필요.
		} catch (final Throwable e) {
			e.getStackTrace();
		}
		
	}
	
}


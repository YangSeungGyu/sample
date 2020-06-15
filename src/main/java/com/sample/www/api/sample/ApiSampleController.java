package com.sample.www.api.sample;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ApiSampleController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiSampleController.class);
	
	@RequestMapping(value = "/api/sample/test", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void test(final HttpServletResponse response, final HttpServletRequest request) throws Exception {
		//MediaType.APPLICATION_FORM_URLENCODED_VALUE  = x-www.form.urlencoded호출
		System.out.println("/api/sample/test");
		
		final String body = this.getReqstBody(request);
		System.out.println(body);
		
		String returnStr = "리턴 결과|테스트";
		responseWriter(returnStr,response);
	}

	
//	get responseBody
	public static String getReqstBody(final HttpServletRequest request) {
		final StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			final InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				final char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			}
		} catch (final IOException ex) {
			ex.getStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (final IOException ex) {
					ex.getStackTrace();
				}
			}
		}
		final String body = stringBuilder.toString();
		// 로그에 requestBody 출력
		logger.info(" Ess Api requestBody befor Decrypt ===> " + body);
		return body;
	}
	
	public static void responseWriter(final String outTxt, final HttpServletResponse response) {
		try (PrintWriter out = response.getWriter()) {
			response.setContentType("text/html;charset=utf-8");
			out.println(outTxt);
		} catch (final IOException e) {
			e.getStackTrace();
		}
	}
	
}


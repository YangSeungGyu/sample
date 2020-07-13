package com.lotto.www.sample;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SampleController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String SAMPLE = "Sample";
	
	@Value("#{commonProperties['sample.test']}")
	private String sampleTest;
	
	@Autowired
	private SampleService sampleService;
	

	@RequestMapping(value = "/smaple/test.view", method = RequestMethod.GET)
	public String home2(Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		String test = sampleService.DbTest();
		System.out.println(test);
		
		logger.debug("sampleTest : "+sampleTest);
		
		
		String pwd = "wewewe";
		String pwdEncd = DigestUtils.sha512Hex(pwd);
		
		return "sample/test";
	}
	
	
	public void VoAutoSet() {
		SampleVO test = sampleService.getAutoVo("testVo01");
	}

}

package com.sample.www.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sample.www.api.sample.ApiSampleController;

public class CommonUtil {
	private static final Logger logger = LoggerFactory.getLogger(ApiSampleController.class);
	
	
	public static String getClientIP(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");
	    logger.info("> X-FORWARDED-FOR : " + ip);

	    if (ip == null) {
	        ip = request.getHeader("Proxy-Client-IP");
	        logger.info("> Proxy-Client-IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	        logger.info(">  WL-Proxy-Client-IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	        logger.info("> HTTP_CLIENT_IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	        logger.info("> HTTP_X_FORWARDED_FOR : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getRemoteAddr();
	        logger.info("> getRemoteAddr : "+ip);
	    }
	    logger.info("> Result : IP Address : "+ip);

	    return ip;
	}
	
	//포멧 체크
	public static boolean checkEmail(final String email) {
		final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		if (email == null) {
			return false;
		} else {
			return Pattern.matches(emailPattern, email);
		}
	}
	
	
	//랜덤 pwd
	public static String ramdomPwd(final int count) {
		// 숫자 + 영문자 랜덤 count자리 문자 추출
		final Random rnd = new Random();
		final StringBuffer buf = new StringBuffer();
		String result = "";

		for (int i = 0; i < count; i++) {
			if (rnd.nextBoolean()) {
				buf.append((char) (rnd.nextInt(26) + 65));
			} else {
				buf.append(rnd.nextInt(10));
			}
		}

		result = buf.toString();

		return result;
	}
	
	
	//Step 관련
	//사용시에는 직접 세션에 해당 값 등록하고 다음 단계에서 session으로 확인하는용
	public static boolean checkStep1(final HttpSession session) {
		return !StringUtils.equals((String) session.getAttribute("join_session_step"), "1");
	}
	public static boolean checkStep2(final HttpSession session) {
		return !StringUtils.equals((String) session.getAttribute("join_session_step"), "2");
	}
	
	
	//날짜관련
	public String getToDayStr(final String outFormat) {
		// toDay by Str
		String returnDt = null;
		if (outFormat != null) {
			final DateTimeFormatter outFmt = DateTimeFormatter.ofPattern(outFormat);
			final LocalDate dtType = LocalDate.now();
			returnDt = dtType.format(outFmt);
		}
		return returnDt;
	}

	public static String getInStr(final String msg, final String startStr, final String afterStr) {
		// 시작 문자 뒤에오는 첫번째 문자열 사이의 문자 가져오기
		String returnStr = null;

		if (msg != null && startStr != null && afterStr != null) {
			final int firstIdx = msg.indexOf(startStr);
			final int afterIdx = msg.indexOf(",", firstIdx);

			// 첫번째 문자까지 가져오는 부분 수정 필요함.
			returnStr = msg.substring(firstIdx, afterIdx);
		}
		return returnStr;
	}
	
	public static String getChngDtFormat(final String dateStr, final String getFormat, final String outFormat) {
		// String 날짜를 포맷 변경해서 출력
		// outFormat=yyyyMMdd , outFormat=yyyy.MM.dd
		String returnChngDt = null;

		if (dateStr != null && getFormat != null && outFormat != null) {
			final DateTimeFormatter getFmt = DateTimeFormatter.ofPattern(getFormat);
			final DateTimeFormatter outFmt = DateTimeFormatter.ofPattern(outFormat);
			final LocalDate dtType = LocalDate.parse(dateStr, getFmt);
			returnChngDt = dtType.format(outFmt);
		}
		return returnChngDt;

	}
	
	public String getDateToStr(final LocalDate date, final String outFormat) {
		// DATE TO String
		String returnDt = null;

		if (date != null && outFormat != null) {
			final DateTimeFormatter outFmt = DateTimeFormatter.ofPattern(outFormat);
			returnDt = date.format(outFmt);
		}
		return returnDt;
	}
	
	public LocalDate getStrToDate(final String dateStr, final String getFormat) {
		// String to DT
		LocalDate returnDt = null;
		if (dateStr != null && getFormat != null) {
			final DateTimeFormatter getFmt = DateTimeFormatter.ofPattern(getFormat);
			returnDt = LocalDate.parse(dateStr, getFmt);
		}
		return returnDt;
	}
}
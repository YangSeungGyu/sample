package com.sample.www.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LimitStrTag extends TagSupport {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = 6993049271016443497L;

	private String value;
	private Integer length;

	@Override
	public int doEndTag() throws JspException {
		try {
			final JspWriter out = this.pageContext.getOut();
			// final String returnValue = unescapeXss(this.value);

			final String returnValue = this.limitStr(this.value, this.length);

			out.print(returnValue);
		} catch (final Throwable e) {
			this.logger.warn(ExceptionUtils.getStackTrace(e));
		}
		return SKIP_BODY;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	public void setLength(Integer length) {
		if (length == null) {
			length = 10;
		}
		this.length = length;
	}

	public String limitStr(final String value, final Integer length) {
		String returnVal = "";
		if (StringUtils.isNotEmpty(value)) {
			if (value.length() > length) {
				returnVal = value.substring(0, length) + "...";
			} else {
				returnVal = value;
			}
		}
		return returnVal;
	}
}

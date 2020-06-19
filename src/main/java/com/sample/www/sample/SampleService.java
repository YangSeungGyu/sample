package com.sample.www.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SampleService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SampleDAO sampleDAO;

	@Transactional
	public String DbTest() {
		return sampleDAO.DbTest();
	}
}

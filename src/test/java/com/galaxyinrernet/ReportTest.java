package com.galaxyinrernet;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.galaxyinternet.query.HealthReport;
import com.galaxyinternet.query.HealthResult;
import com.galaxyinternet.query.Query;
import com.galaxyinternet.service.QueryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class ReportTest
{
	@Autowired
	QueryService service;
	
	@Test
	public void test2()
	{
		
	}
	@Test
	public void test1()
	{
		Query<HealthResult> query = new HealthReport();
		List<HealthResult> list = service.query(query);
		System.out.println(list.size());
	}
}

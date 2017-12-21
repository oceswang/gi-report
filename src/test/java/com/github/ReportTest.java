package com.github;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.report.query.HealthReport;
import com.github.report.query.HealthResult;
import com.github.report.query.Query;
import com.github.report.service.QueryService;

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

package com.galaxyinternet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.dao.QueryDAO;
import com.galaxyinternet.query.Query;
import com.galaxyinternet.query.Result;

@Service
public class QueryService
{
	private QueryDAO queryDAO;

	public QueryDAO getQueryDAO()
	{
		return queryDAO;
	}
	@Autowired
	public void setQueryDAO(QueryDAO queryDAO)
	{
		this.queryDAO = queryDAO;
	}
	
	public <R> List<R> query(Query<R> query)
	{
		List<Result> list = queryDAO.query(query);
		List<R> results = new ArrayList<>();
		if(list != null && query.getRowMapper() != null)
		{
			for(Result result : list)
			{
				R r = query.getRowMapper().mapRow(result);
				results.add(r);
			}
		}
		return results;
	}
}

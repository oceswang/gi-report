package com.galaxyinternet.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.galaxyinternet.query.Query;
import com.galaxyinternet.query.Result;

@Repository
public interface QueryDAO
{
	public List<Result> query(Query query);
}

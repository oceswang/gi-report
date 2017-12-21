package com.github.report.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.report.query.Query;
import com.github.report.query.Result;

@Repository
public interface QueryDAO
{
	public List<Result> query(Query query);
}

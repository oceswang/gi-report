package com.galaxyinternet.query;

public interface RowMapper<R>
{
	 public R mapRow(Result result);
}

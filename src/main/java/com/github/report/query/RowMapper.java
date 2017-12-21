package com.github.report.query;

public interface RowMapper<R>
{
	 public R mapRow(Result result);
}

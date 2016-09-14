package com.galaxyinternet.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Query<R>
{
	private Map<String,Object> params = new HashMap<>();
	protected final List<Param> values = new ArrayList<>();
	public abstract String getSql();
	public abstract RowMapper<R> getRowMapper();
	
	public Map<String, Object> getParams()
	{
		return params;
	}

	public void setParams(Map<String, Object> params)
	{
		this.params = params;
	}

	public List<Param> getValues()
	{
		return values;
	}

	public void addValues(Param value)
	{
		values.add(value);
	}

	public void addParam(String name, Object val)
	{
		params.put(name, val);
	}

}

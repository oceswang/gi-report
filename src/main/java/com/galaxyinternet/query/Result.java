package com.galaxyinternet.query;

import java.util.HashMap;

public class Result extends HashMap<String, Object>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public <T> T get(String key)
	{
		return (T)super.get(key);
	}
}

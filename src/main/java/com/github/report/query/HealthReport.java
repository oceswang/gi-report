package com.github.report.query;

import java.sql.Types;

public class HealthReport extends Query<HealthResult>
{

	@Override
	public String getSql()
	{
		String sql = "select * from sop_project_health where 1=1 and created_uid = ?";
		Param p = new Param();
		p.setType(Types.NUMERIC);
		p.setValue(105L);
		addValues(p);
		return sql;
	}

	@Override
	public RowMapper<HealthResult> getRowMapper()
	{
		return new RowMapper<HealthResult>(){

			@Override
			public HealthResult mapRow(Result result)
			{
				HealthResult r = new HealthResult();
				r.setState(result.get("health_state"));
				r.setProjectId(result.get("project_id"));
				return r;
			}
		};
	}

}

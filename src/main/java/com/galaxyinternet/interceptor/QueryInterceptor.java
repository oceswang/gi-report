package com.galaxyinternet.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;

import com.galaxyinternet.query.Param;
import com.galaxyinternet.query.Query;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class,Integer.class }) })
public class QueryInterceptor implements Interceptor
{

	@Override
	public Object intercept(Invocation invocation) throws Throwable
	{
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		BoundSql boundSql = statementHandler.getBoundSql();
		Object param = boundSql.getParameterObject();
		if(param != null && !(param instanceof Query))
		{
			return invocation.proceed();
		}
		Query<?> query = (Query<?>)param;
		StringBuilder sb = new StringBuilder(query.getSql());
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
		BoundSql newSql = new BoundSql(mappedStatement.getConfiguration(),sb.toString(),null,param);
		metaStatementHandler.setValue("delegate.boundSql", newSql);
		PreparedStatement st = (PreparedStatement)invocation.proceed();
		List<Param> params = query.getValues();
		int index = 1;
		for(Param p : params)
		{
			switch(p.getType())
			{
				case Types.INTEGER:
					st.setInt(index, (int)p.getValue());
					break;
				case Types.DOUBLE:
					st.setDouble(index, (double)p.getValue());
					break;
				case Types.FLOAT:
					st.setFloat(index, (float)p.getValue());
					break;
				case Types.VARCHAR:
					st.setString(index, (String)p.getValue());
					break;
				default:
					st.setObject(index, p.getValue());
			}
			index++;
		}
		return st;
	}

	@Override
	public Object plugin(Object target)
	{
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties)
	{
		System.out.println(properties);
		
	}

}

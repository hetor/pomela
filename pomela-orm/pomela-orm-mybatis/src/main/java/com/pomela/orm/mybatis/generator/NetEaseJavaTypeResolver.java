package com.pomela.orm.mybatis.generator;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;

/**
 * Created by tao.he on 2015/11/26.
 */
public class NetEaseJavaTypeResolver extends JavaTypeResolverDefaultImpl {
	public NetEaseJavaTypeResolver() {
		super();
		typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT",
				new FullyQualifiedJavaType(Integer.class.getName())));
	}
}

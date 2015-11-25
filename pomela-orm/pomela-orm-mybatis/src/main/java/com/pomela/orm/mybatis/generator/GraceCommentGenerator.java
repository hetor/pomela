package com.pomela.orm.mybatis.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * Created by tao.he on 2015/11/24.
 */
public class GraceCommentGenerator extends DefaultCommentGenerator {

	boolean suppressAllComments = false;

	public void addFieldComment(Field field,
								IntrospectedTable introspectedTable,
								IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		field.addJavaDocLine("/**");
		sb.append(" * ").append(introspectedColumn.getRemarks());
		sb.append("所属表字段为");
		sb.append(introspectedTable.getFullyQualifiedTable());
		sb.append('.');
		sb.append(introspectedColumn.getActualColumnName());
		field.addJavaDocLine(sb.toString());

//        addJavadocTag(field, false);

		field.addJavaDocLine(" */"); //$NON-NLS-1$
	}
}

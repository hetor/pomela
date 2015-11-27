package com.pomela.orm.mybatis.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * Created by tao.he on 2015/11/24.
 */
public class GraceCommentGenerator extends DefaultCommentGenerator {

	boolean suppressAllComments = false;

	@Override
	public void addFieldComment(Field field,
								IntrospectedTable introspectedTable,
								IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}

		field.addJavaDocLine("/**");

		StringBuilder sb1 = new StringBuilder();
		sb1.append(" * ").append(introspectedColumn.getRemarks());
		field.addJavaDocLine(sb1.toString());

		StringBuilder sb2 = new StringBuilder();
		sb2.append(" * 所属表字段");
		sb2.append(introspectedTable.getFullyQualifiedTable());
		sb2.append('.');
		sb2.append(introspectedColumn.getActualColumnName());
		field.addJavaDocLine(sb2.toString());

//        addJavadocTag(field, false);

		field.addJavaDocLine(" */");
	}

	@Override
	public void addGetterComment(Method method,
								 IntrospectedTable introspectedTable,
								 IntrospectedColumn introspectedColumn) {
		return;
	}

	@Override
	public void addSetterComment(Method method,
								 IntrospectedTable introspectedTable,
								 IntrospectedColumn introspectedColumn) {
		return;
	}
}

package com.pomela.orm.mybatis.generator;

/**
 * Created by tao.he on 2015/11/24.
 */
public class DefaultCommentGenerator {

	public void addFieldComment(Field field,
								IntrospectedTable introspectedTable,
								IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		field.addJavaDocLine("/**"); //$NON-NLS-1$
//        field.addJavaDocLine(" * This field was generated by MyBatis Generator."); //$NON-NLS-1$

		sb.append(" *  "); //$NON-NLS-1$
		sb.append(introspectedColumn.getRemarks());
		sb.append(",�������ֶ�Ϊ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		sb.append('.');
		sb.append(introspectedColumn.getActualColumnName());
		field.addJavaDocLine(sb.toString());

//        addJavadocTag(field, false);

		field.addJavaDocLine(" */"); //$NON-NLS-1$
	}
}

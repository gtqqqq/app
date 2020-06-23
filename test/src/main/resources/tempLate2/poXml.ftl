<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<hibernate-mapping package="${po}">
	<class name="${tableName}" table="${dbtable}">
		<#list columns as column>
		<#if (column_index=0)>
		<id name="${column[2]}" type="${column[0]}">
			<column name="${column[1]}" />
			<generator class="native"></generator>
		</id>
		</#if>
		</#list>
		
		<#list columns as column>
		<#if (column_index>0)>
		<property name="${column[2]}" type="${column[0]}">
			<column name="${column[1]}"> <comment>${column[3]}</comment></column>
		</property>
		</#if>
		</#list>
	</class>
</hibernate-mapping>
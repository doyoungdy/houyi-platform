package com.houyi.client.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ClientTemplateMapper {

	@Insert("insert into client_r_template_client(client_id, template_id) values(#{clientId}, #{templateId})")
	int save(@Param("clientId") Long clientId, @Param("templateId") Long templateId);

	int delete(@Param("clientId") Long clientId, @Param("templateId") Long templateId);
}

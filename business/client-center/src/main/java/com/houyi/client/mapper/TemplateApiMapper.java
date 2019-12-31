package com.houyi.client.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TemplateApiMapper {

	@Insert("insert into client_r_template_api(template_id, api_id) values(#{templateId}, #{apiId})")
	int save(@Param("templateId") Long templateId, @Param("apiId") Long apiId);

	int delete(@Param("templateId") Long templateId, @Param("apiId") Long apiId);

}

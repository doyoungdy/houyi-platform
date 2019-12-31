package com.houyi.oauth.service;

import com.houyi.common.model.PageResult;
import com.houyi.common.model.Result;
import com.houyi.common.model.client.Client;
import com.houyi.common.service.ISuperService;

import java.util.Map;

/**
 * @Author: houyi
 */
public interface IClientService extends ISuperService<Client> {
    Result saveClient(Client clientDto);

    /**
     * 查询应用列表
     * @param params
     * @param isPage 是否分页
     */
    PageResult<Client> listClent(Map<String, Object> params, boolean isPage);

    void delClient(long id);
}

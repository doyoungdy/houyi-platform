package com.houyi.oauth.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

import com.houyi.common.model.client.Client;

@Setter
@Getter
public class ClientDto extends Client {
    private static final long serialVersionUID = 1475637288060027265L;

    private List<Long> permissionIds;

    private Set<Long> serviceIds;
}

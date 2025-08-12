package com.rolling.service;

import com.common.dto.ServiceResult;

public interface PublicService {

    ServiceResult<Object> getBackgroundImage();

    ServiceResult<Object> getProfileImage();

}

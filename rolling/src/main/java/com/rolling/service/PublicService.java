package com.rolling.service;

import com.rolling.model.ServiceResult;

public interface PublicService {

    ServiceResult<Object> getBackgroundImage();

    ServiceResult<Object> getProfileImage();

}

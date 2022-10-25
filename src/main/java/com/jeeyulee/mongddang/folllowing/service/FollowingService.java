package com.jeeyulee.mongddang.folllowing.service;

import com.jeeyulee.mongddang.common.exception.DeniedAccessException;

public interface FollowingService {
    public Boolean followMember(String userId) throws DeniedAccessException;

    public Boolean unfollowMember(String userId);
}

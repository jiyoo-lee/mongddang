package com.jeeyulee.mongddang.folllowing.service;

import com.jeeyulee.mongddang.common.exception.DeniedAccessException;
import com.jeeyulee.mongddang.folllowing.domain.FollowingDTO;

import java.util.List;

public interface FollowingService {
    public Boolean followMember(String userId) throws DeniedAccessException;

    public List<FollowingDTO> retrieveMyFollowings();

    public List<FollowingDTO> retrieveMyFollowers();

    public Boolean unfollowMember(String userId);

}

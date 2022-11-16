package com.jeeyulee.mongddang.folllowing.service;

import com.jeeyulee.mongddang.common.exception.DeniedAccessException;
import com.jeeyulee.mongddang.folllowing.domain.FollowCountDTO;
import com.jeeyulee.mongddang.folllowing.domain.FollowingDTO;

import java.util.List;

public interface FollowingService {
    public Boolean followMember(String userId);

    public List<FollowingDTO> retrieveMyFollowings();

    public List<FollowingDTO> retrieveMyFollowers();

    public List<FollowingDTO> retrieveRecommendFriends();

    public Boolean unfollowMember(String userId);
    FollowCountDTO retrieveCountFollowers(String userId);
    List<FollowingDTO> retrieveLastUpdatedFriends(String userId);

}

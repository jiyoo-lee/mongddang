package com.jeeyulee.mongddang.folllowing.service;

import com.jeeyulee.mongddang.common.exception.DeniedAccessException;
import com.jeeyulee.mongddang.common.result.ResultException;
import com.jeeyulee.mongddang.folllowing.domain.FollowCountDTO;
import com.jeeyulee.mongddang.folllowing.domain.FollowingDTO;
import com.jeeyulee.mongddang.folllowing.repository.FollowingRepository;
import com.jeeyulee.mongddang.member.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FollowingServiceImpl implements FollowingService {

    private final FollowingRepository followingRepository;
    private final JwtService jwtService;

    @Override
    public Boolean followMember(String userId) {
        String userIdOnToken = jwtService.retrieveUserId();
        checkTokenIsEmpty(userIdOnToken);
        return followingRepository.save(userIdOnToken, userId) > 0;
    }

    @Override
    public List<FollowingDTO> retrieveMyFollowings() {
        String userIdOnToken = jwtService.retrieveUserId();
        checkTokenIsEmpty(userIdOnToken);
        return followingRepository.retrieveFollowingsById(userIdOnToken);
    }

    @Override
    public List<FollowingDTO> retrieveMyFollowers() {
        String userIdOnToken = jwtService.retrieveUserId();
        checkTokenIsEmpty(userIdOnToken);
        return followingRepository.retrieveFollowersById(userIdOnToken);
    }

    public List<FollowingDTO> retrieveRecommendFriends(){
        String userIdOnToken = jwtService.retrieveUserId();
        checkTokenIsEmpty(userIdOnToken);
        return followingRepository.retrieveRecommendFriends(userIdOnToken);
    }

    @Override
    public Boolean unfollowMember(String userId)  {
        String userIdOnToken = jwtService.retrieveUserId();
        checkTokenIsEmpty(userIdOnToken);
        return followingRepository.delete(userIdOnToken, userId) > 0;
    }

    @Override
    public FollowCountDTO retrieveCountFollowers(String userId) {
        return followingRepository.retrieveCountFollowers(userId);
    }

    @Override
    public List<FollowingDTO> retrieveLastUpdatedFriends(String userId) {
        checkTokenIsEmpty(userId);
        return followingRepository.retrieveLastUpdatedFriends(userId);
    }


    private void checkTokenIsEmpty(String userIdOnToken){
        if(userIdOnToken == null) throw new ResultException("유효하지않은 접근입니다.");
    }
}

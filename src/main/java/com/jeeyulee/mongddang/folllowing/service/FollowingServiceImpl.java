package com.jeeyulee.mongddang.folllowing.service;


import com.jeeyulee.mongddang.common.exception.DeniedAccessException;
import com.jeeyulee.mongddang.common.result.ResultException;
import com.jeeyulee.mongddang.folllowing.domain.FollowingDTO;
import com.jeeyulee.mongddang.folllowing.repository.FollowingRepository;
import com.jeeyulee.mongddang.member.exception.UserNotFoundException;
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

        if(userIdOnToken.equals(userId) || userId == null){
            throw new ResultException("올바른 접근이 아닙니다. 다시 로그인해주세요.");
        }

        return followingRepository.save(userIdOnToken, userId) > 0;
    }

    @Override
    public List<FollowingDTO> retrieveMyFollowings() {
        String userIdOnToken = jwtService.retrieveUserId();
        if(userIdOnToken == null){
            throw new ResultException("잘못된 접근입니다. 다시 로그인해주세요.");
        }
        return followingRepository.retrieveFollowingsById(userIdOnToken);
    }

    @Override
    public List<FollowingDTO> retrieveMyFollowers() {
        String userIdOnToken = jwtService.retrieveUserId();
        return followingRepository.retrieveFollowersById(userIdOnToken);
    }

    public List<FollowingDTO> retrieveRecommendFriends(){
        String userIdOnToken = jwtService.retrieveUserId();

        return followingRepository.retrieveRecommendFriends(userIdOnToken);
    }

    @Override
    public Boolean unfollowMember(String userId)  {

        String userIdOnToken = jwtService.retrieveUserId();

        return followingRepository.delete(userIdOnToken, userId) > 0;
    }

}

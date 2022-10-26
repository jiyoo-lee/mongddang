package com.jeeyulee.mongddang.folllowing.service;


import com.jeeyulee.mongddang.common.exception.DeniedAccessException;
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
    public Boolean followMember(String userId) throws DeniedAccessException{

        String userIdOnToken = jwtService.retrieveUserId();

        if(userIdOnToken.equals(userId) || userId == null){
            throw new DeniedAccessException();
        }

        return followingRepository.save(userIdOnToken, userId) > 0;
    }

    @Override
    public List<FollowingDTO> retrieveMyFollowings() {
        String userIdOnToken = jwtService.retrieveUserId();
        return followingRepository.retrieveFollowingsById(userIdOnToken);
    }

    @Override
    public List<FollowingDTO> retrieveMyFollowers() {
        String userIdOnToken = jwtService.retrieveUserId();
        return followingRepository.retrieveFollowersById(userIdOnToken);
    }


    @Override
    public Boolean unfollowMember(String userId)  {

        String userIdOnToken = jwtService.retrieveUserId();

        return followingRepository.delete(userIdOnToken, userId) > 0;
    }

}

package com.jeeyulee.mongddang.folllowing.service;


import com.jeeyulee.mongddang.common.exception.DeniedAccessException;
import com.jeeyulee.mongddang.folllowing.repository.FollowingRepository;
import com.jeeyulee.mongddang.member.exception.UserNotFoundException;
import com.jeeyulee.mongddang.member.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public Boolean unfollowMember(String userId)  {

        String userIdOnToken = jwtService.retrieveUserId();

        return followingRepository.delete(userIdOnToken, userId) > 0;
    }
}

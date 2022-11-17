package com.jeeyulee.mongddang.drawer.service;

import com.jeeyulee.mongddang.drawer.domain.DrawerDTO;
import com.jeeyulee.mongddang.drawer.domain.DrawerResponseDTO;
import com.jeeyulee.mongddang.drawer.repository.DrawerRepository;
import com.jeeyulee.mongddang.member.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DrawerServiceImpl implements DrawerService{

    private final DrawerRepository drawerRepository;
    private final JwtService jwtService;

    @Override
    public DrawerResponseDTO retrieveDrawer(String userId) {
        DrawerResponseDTO drawerResponseDTO = new DrawerResponseDTO();
        drawerResponseDTO.setPaintingCount(drawerRepository.countPaintingByUserId(userId));
        drawerResponseDTO.setPaintingCountGroupingGenre(drawerRepository.countPaintingGroupingGenre(userId));
        drawerResponseDTO.setDrops(drawerRepository.findDropsByUserId(userId));

        String userIdOnToken = jwtService.retrieveUserId();
        drawerResponseDTO.setDrawerUserInfo(drawerRepository.findMemberInfo(userIdOnToken,userId));

        return drawerResponseDTO;
    }

    @Override
    public List<DrawerDTO> retrieveMyDrawer(String userId) {
        return drawerRepository.retrieveMyDrops(userId);
    }
}

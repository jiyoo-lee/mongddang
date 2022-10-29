package com.jeeyulee.mongddang.drawer.service;

import com.jeeyulee.mongddang.drawer.domain.DrawerResponseDTO;
import com.jeeyulee.mongddang.drawer.repository.DrawerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DrawerServiceImpl implements DrawerService{

    private final DrawerRepository drawerRepository;

    @Override
    public DrawerResponseDTO retrieveDrawer(String userId) {
        DrawerResponseDTO drawerResponseDTO = new DrawerResponseDTO();
        drawerResponseDTO.setPaintingCount(drawerRepository.countPaintingByUserId(userId));
        drawerResponseDTO.setPaintingCountGroupingGenre(drawerRepository.countPaintingGroupingGenre(userId));
        drawerResponseDTO.setDrops(drawerRepository.findDropsByUserId(userId));

        return drawerResponseDTO;
    }
}

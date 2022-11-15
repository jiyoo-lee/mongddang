package com.jeeyulee.mongddang.drawer.service;

import com.jeeyulee.mongddang.drawer.domain.DrawerDTO;
import com.jeeyulee.mongddang.drawer.domain.DrawerResponseDTO;

import java.util.List;

public interface DrawerService {
    DrawerResponseDTO retrieveDrawer(String userId);
    List<DrawerDTO> retrieveMyDrawer(String userId);
}

package com.jeeyulee.mongddang.drawer.service;

import com.jeeyulee.mongddang.drawer.domain.DrawerResponseDTO;

public interface DrawerService {
    DrawerResponseDTO retrieveDrawer(String userId);
}

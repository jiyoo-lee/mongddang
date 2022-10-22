package com.jeeyulee.mongddang.drops.service;

import com.jeeyulee.mongddang.drops.domain.DropsDTO;

public interface DropsService {
    public Boolean createDrop(DropsDTO dropsDTO);
    public Boolean updateDrop (DropsDTO dropsDTO);
    public Boolean deleteDrop (Long dropId);
}

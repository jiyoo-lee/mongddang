package com.jeeyulee.mongddang.drops.service;

import com.jeeyulee.mongddang.drops.domain.DropsDTO;
import com.jeeyulee.mongddang.drops.domain.DropsDetailDTO;
import com.jeeyulee.mongddang.drops.domain.DropsUpdateDTO;
import com.jeeyulee.mongddang.drops.exception.DeniedUserAccessException;

public interface DropsService {
    public Boolean createDrop(DropsDTO dropsDTO);
    public DropsDetailDTO retrieveDropsDetail(Long dropsId) throws DeniedUserAccessException;
    public Boolean updateDrop (Long dropsId, DropsUpdateDTO dropsUpdateDTO);
    public Boolean deleteDrop (Long dropsId);
}

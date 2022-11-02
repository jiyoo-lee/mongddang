package com.jeeyulee.mongddang.drops.service;

import com.jeeyulee.mongddang.drops.domain.DropsDTO;
import com.jeeyulee.mongddang.drops.domain.DropsDetailDTO;
import com.jeeyulee.mongddang.drops.domain.DropsUpdateDTO;


public interface DropsService {
    public Boolean createDrop(DropsDTO dropsDTO);
    public DropsDetailDTO retrieveDropsDetail(Long dropsId);
    public Boolean updateDrop (Long dropsId, DropsUpdateDTO dropsUpdateDTO);
    public Boolean deleteDrop (Long dropsId);
}

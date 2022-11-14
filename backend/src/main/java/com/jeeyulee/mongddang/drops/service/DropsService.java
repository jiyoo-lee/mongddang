package com.jeeyulee.mongddang.drops.service;

import com.jeeyulee.mongddang.drops.domain.*;

import java.util.List;


public interface DropsService {
    public Boolean createDrop(DropsDTO dropsDTO);
    public DropsType retrieveSecret();
    public DropsDetailDTO retrieveDropsDetail(Long dropsId);
    public Boolean updateDrop (Long dropsId, DropsUpdateDTO dropsUpdateDTO);
    public Boolean deleteDrop (Long dropsId);
}

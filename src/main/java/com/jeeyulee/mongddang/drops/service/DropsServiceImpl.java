package com.jeeyulee.mongddang.drops.service;

import com.jeeyulee.mongddang.drops.domain.DropsDTO;
import com.jeeyulee.mongddang.drops.repository.DropsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class DropsServiceImpl implements DropsService{

    private final DropsRepository dropsRepository;

    @Override
    public Boolean createDrop(DropsDTO dropsDTO) {
        return dropsRepository.save(dropsDTO) > 0;
    }

    @Override
    public Boolean updateDrop(DropsDTO dropsDTO) {
        return dropsRepository.update(dropsDTO) > 0;
    }

    @Override
    public Boolean deleteDrop(Long dropId) {
        return dropsRepository.delete(dropId) > 0;
    }


}

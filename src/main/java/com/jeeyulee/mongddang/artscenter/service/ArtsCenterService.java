package com.jeeyulee.mongddang.artscenter.service;

import com.jeeyulee.mongddang.artscenter.domain.ArtsCenterDTO;

import java.util.List;

public interface ArtsCenterService {

    public List<ArtsCenterDTO> retrieveWinnersOfHistory();
}

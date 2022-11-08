package com.jeeyulee.mongddang.search;

import com.jeeyulee.mongddang.common.result.ResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final SearchRepository searchRepository;

    @Override
    public List<SearchDropsDTO> retrieveSearch(String keyword) {

        return searchRepository.retrieveDrops(keyword);
    }

    @Override
    public List<SearchPaintingDTO> retrieveByPaintingName(String keyword) {

        return searchRepository.retrievePaintings(keyword);
    }

}

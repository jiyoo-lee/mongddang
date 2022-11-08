package com.jeeyulee.mongddang.search;


import java.util.List;

public interface SearchService {
    public List<SearchDropsDTO> retrieveSearch(String keyword);

    public List<SearchPaintingDTO> retrieveByPaintingName(String keyword);
}

package com.jeeyulee.mongddang.genre.repository;

import com.jeeyulee.mongddang.genre.domain.GenreDTO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GenreRepository {

    @Select("select id, name " +
            "from genre")
    public List<GenreDTO> selectAll();
}

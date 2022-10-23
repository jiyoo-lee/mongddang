package com.jeeyulee.mongddang.painting.repository;

import com.jeeyulee.mongddang.painting.domain.PaintingCreationDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaintingRepository {

    @Insert("insert into painting(drops_id, genre_id, name, painting_url, description) " +
            "value(#{dropsId}, #{genreId}, #{name}, #{paintingUrl}, #{description}) ")
    public Integer save(PaintingCreationDTO paintingCreationDTO);

}

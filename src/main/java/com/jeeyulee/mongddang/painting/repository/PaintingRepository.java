package com.jeeyulee.mongddang.painting.repository;

import com.jeeyulee.mongddang.painting.domain.PaintingCreationDTO;
import com.jeeyulee.mongddang.painting.domain.PaintingUpdateBuilderDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PaintingRepository {

    @Insert("insert into painting(drops_id, genre_id, name, painting_url, description) " +
            "value(#{dropsId}, #{genreId}, #{name}, #{paintingUrl}, #{description}) ")
    public Integer save(PaintingCreationDTO paintingCreationDTO);

    @Update("update painting " +
            "set " +
            "genre_id = IFNULL (#{genreId},genre_id), " +
            "name = IFNULL (#{name},name), " +
            "painting_url = IFNULL (#{paintingUrl},painting_url), " +
            "description = IFNULL (#{description},description) " +
            "where id = #{paintingId}")
    public Integer update(PaintingUpdateBuilderDTO builderDTO);

    @Delete("delete " +
            "from painting " +
            "where id = #{paintingId}")
    public Integer delete(Long paintingId);
}

package com.jeeyulee.mongddang.painting.repository;

import com.jeeyulee.mongddang.painting.domain.PaintingCreationDTO;
import com.jeeyulee.mongddang.painting.domain.PaintingUpdateBuilderDTO;
import com.jeeyulee.mongddang.painting.domain.PopularPaintingsDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    @Select("select member_id " +
            "from drops " +
            "where id = #{dropsId} ")
    public String findUserIdByDropsId(Long dropsId);

    @Select("select * " +
            "from painting " +
            "left outer join" +
            "member ")
    public List<PopularPaintingsDTO> retrievePopularPaintings();

}

package com.jeeyulee.mongddang.drops.repository;

import com.jeeyulee.mongddang.drops.domain.*;
import org.apache.ibatis.annotations.*;


@Mapper
public interface DropsRepository {

    @Insert("insert into drops( member_id, genre_id, name, type_id ) value (#{userId},#{genreId},#{name},#{typeId})")
    public Integer save(DropsDTO dropsDTO);



    @Update("update drops " +
            "set genre_id = IFNULL(#{genreId},genre_id), " +
            "name = IFNULL(#{name}, name), " +
            "type_id = IFNULL(#{typeId}, type_id) " +
            "where member_id = #{userId} " +
            "and id = #{id}")
    public Integer update(DropsDTO dropsDTO);


    @Delete("delete " +
            "from drops " +
            "where id = #{dropId}")
    public Integer delete(Long dropId);
}

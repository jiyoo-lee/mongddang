package com.jeeyulee.mongddang.painting.mongddang;

import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaintingMongddangRepository {

    @Insert("insert into painting_mongddang (painting_id, member_id) " +
            "value(#{paintingId},#{userIdOnToken})")
    public Integer save(Long paintingId, String userIdOnToken);

    @Delete("delete from painting_mongddang " +
            "where painting_id = #{paintingId} and member_id = #{userIdOnToken}")
    public Integer delete(Long paintingId, String userIdOnToken);
}

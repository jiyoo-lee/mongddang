package com.jeeyulee.mongddang.artscenter.contestpainting.mongddang.repository;

import com.jeeyulee.mongddang.artscenter.contestpainting.mongddang.domain.ContestMongddangDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContestMongddangRepository {

    @Insert("insert into contest_mongddang (contest_painting_id, member_id) " +
            "value(#{contestPaintingId},#{memberId})")
    public Integer save(ContestMongddangDTO mongddangDTO);

    @Delete("delete from contest_mongddang " +
            "where contest_painting_id = #{contestPaintingId} and member_id = #{memberId}")
    public Integer delete(ContestMongddangDTO contestMongddangDTO);
}

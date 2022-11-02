package com.jeeyulee.mongddang.artscenter.contestpainting.repository;

import com.jeeyulee.mongddang.artscenter.contestpainting.domain.ContestPaintingBuilderDTO;
import com.jeeyulee.mongddang.artscenter.contestpainting.domain.ContestPaintingDeleteDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContestPaintingRepository {

    @Insert("insert into contest_painting (contest_id, member_id, title, contest_painting_url, description) " +
            "value (#{contestId},#{memberId},#{title},#{contestPaintingUrl},#{description}) ")
    public Integer savePainting(ContestPaintingBuilderDTO contestPaintingBuilderDTO);

    @Delete("delete from contest_painting " +
            "where member_id = #{memberId} and " +
            "id = #{contestPaintingId}")
    public Integer deletePainting(ContestPaintingDeleteDTO contestPaintingDeleteDTO);
}

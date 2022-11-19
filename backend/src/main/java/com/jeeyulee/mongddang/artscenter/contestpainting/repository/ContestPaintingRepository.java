package com.jeeyulee.mongddang.artscenter.contestpainting.repository;

import com.jeeyulee.mongddang.artscenter.contestpainting.domain.ContestPaintingBuilderDTO;
import com.jeeyulee.mongddang.artscenter.contestpainting.domain.ContestPaintingDTO;
import com.jeeyulee.mongddang.artscenter.contestpainting.domain.ContestPaintingDeleteDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ContestPaintingRepository {

    @Insert("insert into contest_painting (contest_id, member_id, title, contest_painting_url, description) " +
            "value (#{contestId},#{memberId},#{title},#{contestPaintingUrl},#{description}) ")
    public Integer savePainting(ContestPaintingDTO contestPaintingDTO);


    @Select("select id paintingId, " +
            "       contest_id contestId, " +
            "       member_id memberId, " +
            "       title, " +
            "       contest_painting_url contestPaintingUrl, " +
            "       description, " +
            "       create_datetime createDatetime " +
            "from contest_painting " +
            "where contest_id = #{contestId} " +
            "and member_id = #{userId}")
    public List<ContestPaintingDTO> retrieveMyPainting(Long contestId, String userId);

    @Delete("delete from contest_painting " +
            "where member_id = #{memberId} and " +
            "id = #{contestPaintingId}")
    public Integer deletePainting(ContestPaintingDeleteDTO contestPaintingDeleteDTO);
}

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


    @Select("select C.id paintingId, C.member_id memberId, C.id contestId, C.title, C.contest_painting_url contestPaintingUrl, " +
            "C.description, C.create_datetime createDatetime, " +
            "(select count(*) from contest_comment CC where CC.contest_painting_id = C.id)comment, " +
            "(select count(*) from contest_mongddang M where M.contest_painting_id = C.id)mongddangCount, " +
            "(select count(*) from contest_mongddang CM where CM.contest_painting_id = C.id)isLike " +
            "from contest_painting C " +
            "where C.contest_id = #{contestId} and C.member_id = #{userId}")
    public List<ContestPaintingDTO> retrieveMyPainting(Long contestId, String userId);

    @Delete("delete from contest_painting " +
            "where member_id = #{memberId} and " +
            "id = #{contestPaintingId}")
    public Integer deletePainting(ContestPaintingDeleteDTO contestPaintingDeleteDTO);
}

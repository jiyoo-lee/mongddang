package com.jeeyulee.mongddang.artscenter.repository;

import com.jeeyulee.mongddang.artscenter.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArtsCenterRepository {

    @Select("select id contestId, " +
            "       title, " +
            "       poster_url posterUrl, " +
            "       start_day startDay, " +
            "       end_day endDay " +
            "from contest " +
            "where deadline = 0 ")
    List<ArtsCenterResponseDTO> findInProgress();

    @Select("select P.member_id memberId, " +
            "       (select M.nickname " +
            "        from member M " +
            "        where M.user_id = P.member_id) nickname, " +
            "       (select M.profile_picture " +
            "        from member M " +
            "        where M.user_id = P.member_id) profileUrl, " +
            "       P.id paintingId, " +
            "       P.title paintingName, " +
            "       P.description, " +
            "       P.contest_painting_url paintingUrl, " +
            "       count(select member_id " +
            "        from contest_mongddang " +
            "        where member_id = #{userId} " +
            "        and contest_painting_id = P.id) isLike, " +
            "       (select count(*) " +
            "        from contest_mongddang CM " +
            "        where CM.member_id = P.member_id) paintingMongddangCount " +
            "from contest_painting P " +
            "where P.contest_id = #{contestId} " +
            "order by paintingMongddangCount desc " +
            "limit 0, 20")
    List<ArtsCenterWinnerResponseDTO> findWinnerByDropsId(Long contestId, String userId);

    @Select("select P.id paintingId, " +
            "       M.user_id memberId, " +
            "       M.nickname, P.title, " +
            "       P.contest_painting_url contestPaintingUrl, " +
            "       P.description, " +
            "       (select count(*) " +
            "        from contest_mongddang CM " +
            "        where P.id = CM.contest_painting_id) mongddangCount, " +
            "       (select count(*) " +
            "        from contest_comment CC " +
            "        where P.ID = CC.contest_painting_id) comment, " +
            "       count(select member_id " +
            "        from contest_mongddang " +
            "        where member_id = #{userId} " +
            "        and contest_painting_id = P.id) isLike " +
            "from contest_painting P " +
            "join member M " +
            "on P.member_id = M.user_id " +
            "where P.id = #{paintingId} and P.contest_id = #{contentId}")
    List<ArtsCenterPaintingDetailDTO> findPaintingDetail(Long contentId, Long paintingId, String userId);
}

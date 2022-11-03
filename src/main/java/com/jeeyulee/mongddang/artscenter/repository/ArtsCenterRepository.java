package com.jeeyulee.mongddang.artscenter.repository;

import com.jeeyulee.mongddang.artscenter.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArtsCenterRepository {

    @Select("select C.id contestId, C.title, C.poster_url, C.start_day, C.end_day, P.member_id memberId, " +
            "        (select M.nickname " +
            "        from member M " +
            "        where M.user_id = P.member_id) nickname, " +
            "P.id paintingId, P.title paintingTitle, P.contest_painting_url paintingUrl, " +
            "        (select count(*) " +
            "        from contest_mongddang CM " +
            "        where CM.member_id = P.member_id) mongddangCount " +
            "from contest_painting P " +
            "right outer join" +
            "       (select * from contest " +
            "       where deadline = false)C " +
            "on C.id = P.contest_id " +
            "order by mongddangCount desc limit 0,5")
    List<ArtsCenterResponseDTO> findAll();

    @Select("select C.title, C.poster_url, C.start_day, C.end_day, P.member_id, " +
            "           (select M.nickname " +
            "           from member M " +
            "           where M.user_id = P.member_id) nickname, " +
            "P.id, P.title, P.contest_painting_url, " +
            "           (select count(*) " +
            "           from contest_mongddang CM " +
            "           where CM.member_id = P.member_id) mongddangCount " +
            "from contest_painting P " +
            "right outer join " +
            "           (select * from contest " +
            "           where deadline = true and id = #{contestId})C " +
            "on C.id = P.contest_id " +
            "order by mongddangCount desc limit 0,20")
    List<ArtsCenterWinnerResponseDTO> findWinnerByDropsId(Long contestId);
}

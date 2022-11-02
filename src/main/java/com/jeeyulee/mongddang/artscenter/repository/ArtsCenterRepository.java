package com.jeeyulee.mongddang.artscenter.repository;

import com.jeeyulee.mongddang.artscenter.domain.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArtsCenterRepository {

    @Insert("insert into contest_painting (contest_id, member_id, title, contest_painting_url, description) " +
            "value (#{contestId},#{memberId},#{title},#{contestPaintingUrl},#{description}) ")
    public Integer savePainting(ContestPaintingBuilderDTO contestPaintingBuilderDTO);


    @Insert("insert into contest (admin_id, title, poster_url, start_day, end_day) " +
            "value (#{memberId},#{title},#{posterUrl},#{startDay},#{endDay}) ")
    public Integer saveContest(ContestBuilderDTO contestBuilderDTO);


    @Select("select P4.drops_id dropsId, " +
            "       (select name from drops where id = P4.drops_id) dropsName, " +
            "       P4.member_id memberId, " +
            "       (select nickname from member where member_id = P4.member_id) nickname, " +
            "       (select profile_picture from member where member_id = P4.member_id) profileUrl, " +
            "       P4.name paintingName, " +
            "       (select name from genre where id = P4.genre_id) genreName, " +
            "       P4.description, " +
            "       P4.painting_url paintingUrl, " +
            "       P4.count paintingMongddangCount " +
            "from (select drops_id, max(count) count " +
            "      from (select * " +
            "            from drops " +
            "            where type_id = (select id " +
            "                             from drops_type " +
            "                             where name = '몽땅전')) D " +
            "      right outer join (select * " +
            "                        from painting P " +
            "                        left outer join (select painting_id, count(*) count " +
            "                                         from painting_mongddang " +
            "                                         group by painting_id) PM " +
            "                        on P.id = PM.painting_id) P2 " +
            "      on D.id = P2.drops_id " +
            "      group by drops_id) DM " +
            "right outer join (select * " +
            "                  from painting P3 " +
            "                  left outer join (select painting_id, count(*) count " +
            "                                   from painting_mongddang " +
            "                                   group by painting_id) PM2 " +
            "                  on P3.id = PM2.painting_id) P4 " +
            "on DM.drops_id = P4.drops_id and DM.count = P4.count " +
            "order by P4.create_datetime desc")
    List<ArtsCenterResponseDTO> findAll();

    @Select("select M.user_id memberId, " +
            "       M.nickname, " +
            "       M.profile_picture profileUrl, " +
            "       P2.name paintingName, " +
            "       (select name from genre where id = P2.genre_id) genreName, " +
            "       P2.description, " +
            "       P2.painting_url paintingUrl, " +
            "       P2.paintingMongddangCount " +
            "from member M right outer join (select P.*, PM.paintingMongddangCount " +
            "                                from (select id, member_id, name, painting_url, description, genre_id " +
            "                                      from painting " +
            "                                      where drops_id = #{dropsId}) P " +
            "                                left outer join (select painting_id, count(*) paintingMongddangCount " +
            "                                                 from painting_mongddang " +
            "                                                 group by painting_id) PM " +
            "                                on P.id = PM.painting_id " +
            "                                order by paintingMongddangCount desc " +
            "                                limit 0, 10) P2 " +
            "on M.user_id = P2.member_id ")
    List<ArtsCenterWinnerResponseDTO> findWinnerByDropsId(Long dropsId);
}

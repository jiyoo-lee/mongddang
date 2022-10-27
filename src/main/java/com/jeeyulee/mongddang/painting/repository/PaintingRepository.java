package com.jeeyulee.mongddang.painting.repository;

import com.jeeyulee.mongddang.painting.domain.PaintingCreationDTO;
import com.jeeyulee.mongddang.painting.domain.PaintingUpdateBuilderDTO;
import com.jeeyulee.mongddang.painting.domain.ConditionalPaintingsDTO;
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

    @Select("select M.user_id as memberId," +
            "M.nickname, " +
            "M.profile_picture as profileUrl, " +
            "(select name from genre where id = D2.genre_id) as genreName, " +
            "D2.id as paintingId, " +
            "D2.name, " +
            "D2.painting_url as paintingUrl, " +
            "D2.description, " +
            "D2.mongddangCount, " +
            "D2.create_datetime " +
            "from member M right outer join (" +
            "select member_id, P2.* " +
            "from drops D right outer join (select P.*, C.count mongddangCount " +
            "from painting P right outer join (select painting_id, count(*) count " +
            "from painting_mongddang " +
            "group by painting_id " +
            "order by count desc " +
            "limit 0, 20) C " +
            " on P.id = C.painting_id) P2 " +
            "on D.id = P2.drops_id " +
            ") D2 " +
            "on M.user_id = D2.member_id")

    public List<ConditionalPaintingsDTO> retrievePopularPaintings();

    @Select("select M.user_id as memberId," +
            "       M.nickname, " +
            "       M.profile_picture as profileUrl, " +
            "       (select name from genre where id = D2.genre_id) as genreName, " +
            "       D2.id paintingId, " +
            "       D2.name, " +
            "       D2.painting_url as paintingUrl, " +
            "       D2.description, " +
            "       D2.mongddangCount, " +
            "       D2.create_datetime " +
            "from member M right outer join (select member_id, P2.* " +
            "                                from drops D right outer join (select P.*, C.count mongddangCount " +
            "                                                               from painting P right outer join (select painting_id, count(*) count " +
            "                                                                                                 from painting_mongddang " +
            "                                                                                                 group by painting_id " +
            "                                                                                                 order by count desc " +
            "                                                                                                 limit 0, 20) C " +
            "                                                                on P.id = C.painting_id " +
            "                                                                where P.genre_id = #{genreId}) P2 " +
            "                                on D.id = P2.drops_id) D2 " +
            "on M.user_id = D2.member_id")
    public List<ConditionalPaintingsDTO> retrievePopularPaintingsByGenreId(Long genreId);


    @Select("select M.user_id as memberId, " +
            "       M.nickname, " +
            "       M.profile_picture as profileUrl, " +
            "       (select name from genre where id = D2.genre_id) as genreName, " +
            "       D2.id paintingId, " +
            "       D2.name, " +
            "       D2.painting_url as paintingUrl, " +
            "       D2.description, " +
            "       D2.mongddangCount, " +
            "       D2.create_datetime " +
            "from member M right outer join (select member_id, P2.* " +
            "                                from drops D right outer join (select P.*, count(C.painting_id) mongddangCount " +
            "                                                               from painting P left outer join painting_mongddang C " +
            "                                                               on P.id = C.painting_id group by P.id ) P2 " +
            "                                 on D.id = P2.drops_id) D2 " +
            "on M.user_id = D2.member_id " +
            "order by D2.create_datetime desc")
    public List<ConditionalPaintingsDTO> retrieveLastPaintings();


    @Select("select P2.user_id memberId, P2.nickname, P2.profile_picture profileUrl, P2.painting_id paintingId, " +
            "(select name from genre where id = P2.genre_id) genreName, P2.name, " +
            "P2.painting_url paintingUrl, P2.description, P2.create_datetime createDatetime," +
            "count(PM.painting_id) mongddangCount " +
            "from ( select P.id painting_id, P.genre_id, P.name, painting_url, P.description, P.create_datetime," +
            " D2.user_id, D2.nickname, D2.profile_picture " +
            "from painting P right outer join (select D.id drops_id, M.* " +
            "from drops D right outer join (select user_id, nickname, profile_picture " +
            "from member " +
            "where user_id in (select follow_member_id " +
            "from social " +
            "where member_id = #{userId})) M " +
            "on D.member_id = M.user_id) D2 " +
            "on P.drops_id = D2.drops_id " +
            "order by P.create_datetime desc" +
            ") P2 left outer join painting_mongddang PM " +
            "on P2.painting_id = PM.painting_id " +
            "group by P2.painting_id, P2.user_id")
    public List<ConditionalPaintingsDTO> retrieveLastFollowingPaintings(String userId);
}

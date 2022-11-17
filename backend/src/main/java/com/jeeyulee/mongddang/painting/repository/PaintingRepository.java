package com.jeeyulee.mongddang.painting.repository;

import com.jeeyulee.mongddang.painting.domain.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaintingRepository {

    @Insert("insert into painting(member_id, drops_id, genre_id, name, painting_url, description) " +
            "value(#{memberId}, #{dropsId}, #{genreId}, #{name}, #{paintingUrl}, #{description}) ")
    public Integer save(PaintingDTO paintingDTO);

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

    @Select("select M.user_id as memberId, " +
            "            M.nickname, " +
            "            M.profile_picture as profileUrl, " +
            "            (select name from genre where id = P2.genre_id ) as genreName, " +
            "            P2.id as paintingId, " +
            "            P2.name, " +
            "            P2.painting_url as paintingUrl, " +
            "            P2.description, " +
            "            P2.mongddangCount, " +
            "            P2.create_datetime " +
            "from member M right outer join " +
            "(select P.*, C.count mongddangCount from painting P " +
            "right outer join (select painting_id, count(*) count " +
            "from painting_mongddang " +
            "group by painting_id " +
            "order by count desc " +
            "limit 0, 20) C " +
            "on P.id = C.painting_id) P2 " +
            "            on P2.member_id = M.user_id")
    public List<ConditionalPaintingsDTO> retrievePopularPaintings();

    @Select("select M.user_id as memberId, " +
            "            M.nickname, " +
            "            M.profile_picture as profileUrl, " +
            "            (select name from genre where id = P2.genre_id ) as genreName, " +
            "            P2.id as paintingId, " +
            "            P2.name, " +
            "            P2.painting_url as paintingUrl, " +
            "            P2.description, " +
            "            P2.mongddangCount, " +
            "            P2.create_datetime " +
            "from member M right outer join " +
            "(select P.*, C.count mongddangCount from painting P " +
            "join (select painting_id, count(*) count " +
            "from painting_mongddang " +
            "group by painting_id " +
            "order by count desc " +
            "limit 0, 20) C " +
            "on P.id = C.painting_id and P.genre_id = #{genreId}) P2 " +
            "            on P2.member_id = M.user_id")
    public List<ConditionalPaintingsDTO> retrievePopularPaintingsByGenreId(Long genreId);


    @Select("select M.user_id as memberId, " +
            "            M.nickname, " +
            "            M.profile_picture as profileUrl, " +
            "           (select name from genre where id = P2.genre_id ) as genreName, " +
            "            P2.id as paintingId, " +
            "            P2.name, " +
            "            P2.painting_url as paintingUrl, " +
            "            P2.description, " +
            "            P2.mongddangCount, " +
            "            P2.create_datetime " +
            "from member M right outer join " +
            "           (select P.*, IFNULL(C.count, 0)mongddangCount from painting P " +
            "       left outer join (select painting_id, count(*) count " +
            "                        from painting_mongddang " +
            "                        group by painting_id " +
            "                        order by count desc " +
            "                        limit 0, 20) C " +
            "        on P.id = C.painting_id) P2 " +
            "            on P2.member_id = M.user_id order by P2.create_datetime desc")
    public List<ConditionalPaintingsDTO> retrieveLastPaintings();


    @Select("select M.user_id memberId, " +
            "       M.nickname, " +
            "       M.profile_picture profileUrl, " +
            "       P2.painting_url paintingUrl, " +
            "       P2.id paintingId, " +
            "       P2.name, " +
            "       P2.create_datetime createDatetime, " +
            "       P2.description, " +
            "       (select count(*) " +
            "        from painting_mongddang PM " +
            "        where PM.painting_id = P2.id) mongddangCount, " +
            "       if((select PM2.painting_id " +
            "        from painting_mongddang PM2 " +
            "        where PM2.member_id = #{userId} " +
            "        and PM2.painting_id = P2.id) is null, false, true) isLike " +
            "from member M " +
            "right outer join painting P2 " +
            "on P2.member_id = M.user_id and " +
            "M.user_id in (select P.member_id " +
            "              from painting P " +
            "              where P.member_id in (select follow_member_id " +
            "                                    from social " +
            "                                    where member_id= #{userId})) " +
            "order by createDatetime desc ")
    public List<FeedPaintingsDTO> retrieveLastFollowingPaintings(String userId);

    @Select("select P.painting_url paintingUrl, P.member_id memberId, P.name from painting P " +
            "where P.id in (select painting_id from painting_mongddang " +
            "               where member_id = #{userId})")
    public List<PaintingMongddangDTO> retrieveMongddangPaintings(String userId);
}

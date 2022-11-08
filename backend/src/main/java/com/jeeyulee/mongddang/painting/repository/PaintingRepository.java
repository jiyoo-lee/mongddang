package com.jeeyulee.mongddang.painting.repository;

import com.jeeyulee.mongddang.painting.domain.PaintingCreationDTO;
import com.jeeyulee.mongddang.painting.domain.PaintingUpdateBuilderDTO;
import com.jeeyulee.mongddang.painting.domain.ConditionalPaintingsDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaintingRepository {

    @Insert("insert into painting(member_id, drops_id, genre_id, name, painting_url, description) " +
            "value(#{memberId}, #{dropsId}, #{genreId}, #{name}, #{paintingUrl}, #{description}) ")
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


    @Select("select M.user_id memberId, (select name from genre where id = P.genre_id)genreName, " +
            "M.profile_picture profileUrl, M.nickname, " +
            "P.id paintingId, P.name, P.painting_url paintingUrl, P.description, P.create_datetime, " +
            "IFNULL(M2.count, 0)mongddangCount " +
            "from member M " +
                "join painting P " +
            "                   left outer join (select painting_id, count(*) count " +
            "                                   from painting_mongddang " +
                                                "group by painting_id ) M2 " +
            "on P.id = M2.painting_id " +
                        "on M.user_id = P.member_id and M.user_id " +
            "           in (select follow_member_id from social where member_id = #{userId}) " +
            "           order by P.create_datetime desc")
    public List<ConditionalPaintingsDTO> retrieveLastFollowingPaintings(String userId);
}

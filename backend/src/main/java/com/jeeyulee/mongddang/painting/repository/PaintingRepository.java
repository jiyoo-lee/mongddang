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

    @Select("select P.id paintingId, P.name, P.description,P.create_datetime createDatetime, " +
            " M.nickname, M.profile_picture profileUrl, M.user_id memberId, " +
            "(select name from genre where P.genre_id = genre.id) genreName, " +
            "If((select PM.painting_id from painting_mongddang PM where PM.member_id = #{userIdOnToken} " +
            "and PM.painting_id = P.id) is null, false , true)isLike, " +
            "(select count(*) from painting_mongddang PM2 where P.id = PM2.painting_id)mongddangCount, " +
            "(select count(*) from comment C where C.painting_id = P.id)comment " +
            "from painting P join member M on P.member_id = M.user_id " +
            " order by mongddangCount desc limit 0,20")
    public List<ConditionalPaintingsDTO> retrievePopularPaintings(String userIdOnToken);

    @Select("select P.id paintingId, P.painting_url PaintingUrl, P.name, P.description,P.create_datetime createDatetime, " +
            " M.nickname, M.profile_picture profileUrl, M.user_id memberId, " +
            "If(" +
            "(select PM.painting_id from painting_mongddang PM where PM.member_id = #{userIdOnToken} " +
            "and PM.painting_id = P.id) is null, false , true)isLike, " +
            "(select count(*) from painting_mongddang PM2 where P.id = PM2.painting_id)mongddangCount, " +
            "(select count(*) from comment C where C.painting_id = P.id)comment " +
            "from painting P join member M on P.member_id = M.user_id " +
            "where P.genre_id = (select id from genre where id = #{genreId}) " +
            "having mongddangCount > 0 order by mongddangCount desc limit 0,20")
    public List<ConditionalPaintingsDTO> retrievePopularPaintingsByGenreId(String userIdOnToken, Long genreId);


    @Select("select P.id paintingId, P.painting_url PaintingUrl ,P.name, P.description,P.create_datetime createDatetime, " +
            " M.nickname, M.profile_picture profileUrl, M.user_id memberId, " +
            " (select name " +
            "            from genre " +
            "            where id = P.genre_id) genreName, " +
            "If((select PM.painting_id " +
            "    from painting_mongddang PM " +
            "   where PM.member_id = #{userIdOnToken} " +
            "   and PM.painting_id = P.id) is null, false , true)isLike, " +
            " (select count(*) " +
            "  from painting_mongddang PM2 " +
            "  where P.id = PM2.painting_id)mongddangCount, " +
            " (select count(*) " +
            "  from comment C " +
            "  where C.painting_id = P.id)comment " +
            "from painting P join member M on P.member_id = M.user_id " +
            "order by createDatetime desc limit 0,20 ")
    public List<ConditionalPaintingsDTO> retrieveLastPaintings(String userIdOnToken);


    @Select("select M.user_id memberId, " +
            "       M.nickname, " +
            "       M.profile_picture profileUrl, " +
            "       P2.painting_url paintingUrl, " +
            "       P2.id paintingId, " +
            "       P2.name, " +
            "       P2.create_datetime createDatetime, " +
            "       P2.description, " +
            "       (select count(*) " +
            "       from comment " +
            "       where comment.painting_id = P2.id)comment, " +
            "       (select name " +
            "        from genre" +
            "        where id = P2.genre_id) genreName, " +
            "       (select count(*) " +
            "        from painting_mongddang PM " +
            "        where PM.painting_id = P2.id) mongddangCount, " +
            "       if((select PM2.painting_id " +
            "        from painting_mongddang PM2 " +
            "        where PM2.member_id = #{userId} " +
            "        and PM2.painting_id = P2.id) is null, false, true) isLike " +
            "from member M " +
            "right outer join painting P2 " +
            "on P2.member_id = M.user_id " +
            "where M.user_id in (select P.member_id " +
            "                    from painting P " +
            "                    where P.member_id in (select follow_member_id " +
            "                                          from social " +
            "                                          where member_id= #{userId})) " +
            "order by createDatetime desc limit #{start}, #{size}")
    public List<FeedPaintingsDTO> retrieveLastFollowingPaintings(String userId, Integer start, Integer size);

    @Select("select P.painting_url paintingUrl, P.member_id memberId, P.name from painting P " +
            "where P.id in (select painting_id from painting_mongddang " +
            "               where member_id = #{userId})")
    public List<PaintingMongddangDTO> retrieveMongddangPaintings(String userId);
}

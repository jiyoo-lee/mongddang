package com.jeeyulee.mongddang.painting.repository;

import com.jeeyulee.mongddang.painting.domain.PaintingCreationDTO;
import com.jeeyulee.mongddang.painting.domain.PaintingUpdateBuilderDTO;
import com.jeeyulee.mongddang.painting.domain.PopularPaintingsDTO;
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

    public List<PopularPaintingsDTO> retrievePopularPaintings();

    @Select("select M.user_id as memberId," +
            "M.nickname, " +
            "M.profile_picture as profileUrl, " +
            "(select name from genre where id = D2.genre_id) as genreName, " +
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
            " on P.id = C.painting_id where P.genre_id = #{genreId}) P2 " +
            "on D.id = P2.drops_id " +
            ") D2 " +
            "on M.user_id = D2.member_id")
    public List<PopularPaintingsDTO> retrievePopularPaintingsByGenreId(Long genreId);


    @Select("select M.user_id as memberId, " +
            "M.nickname, " +
            "M.profile_picture as profileUrl, " +
            "(select name from genre where id = D2.genre_id) as genreName, " +
            "D2.name, " +
            "D2.painting_url as paintingUrl, \n" +
            "D2.description, " +
            "D2.mongddangCount, " +
            "D2.create_datetime " +
            "from member M right outer join (" +
            "select member_id, P2.* " +
            "from drops D right outer join (select P.*, count(C.painting_id)mongddangCount " +
            "from painting P left outer join painting_mongddang C " +
            "on P.id = C.painting_id group by P.id ) P2 " +
            "on D.id = P2.drops_id " +
            ") D2 " +
            "on M.user_id = D2.member_id order by D2.create_datetime desc;")
    public List<PopularPaintingsDTO> retrieveLastPaintings();
}

package com.jeeyulee.mongddang.drops.repository;

import com.jeeyulee.mongddang.drops.domain.*;
import com.jeeyulee.mongddang.painting.domain.PaintingDetailDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface DropsRepository {

    @Insert("insert into drops( member_id, genre_id, name, type_id ) value (#{userId},#{genreId},#{name},#{typeId})")
    public Integer save(DropsDTO dropsDTO);

    @Select("select * from drops_type")
    public DropsType retrieveSecret();


    @Select("select M.user_id as memberId," +
            "       M.nickname as nickname, " +
            "       D.name as dropsName, " +
            "       (select name " +
            "        from genre " +
            "        where id = D.genre_id) as dropsGenre," +
            "       (select name " +
            "        from drops_type " +
            "        where id = D.type_id) as dropsType," +
            "       D.create_datetime as createDateTime " +
            "from drops D left outer join member M " +
            "on D.member_id = M.user_id " +
            "where id = #{dropsId} " +
            "order by D.create_datetime desc")
    public DropsDetailDTO findById(Long dropsId);

    @Select("select count(*) " +
            "from secret_drops_access " +
            "where member_id = #{memberId} " +
            "and drops_id = #{dropsId}")
    public Integer countByMemberIdAndDropsId(String memberId, Long dropsId);

    @Select("select id paintingId, (select name " +
            "        from genre " +
            "        where id = genre_id) as genre, " +
            "       (select count(*) from comment " +
            "       where comment.painting_id = painting.id) comment, " +
            "       if((select painting_id from painting_mongddang " +
            "       where member_id = #{userId} and " +
            "       painting_id = painting.id) is null, false, true)isLike, " +
            "       (select count(*) from painting_mongddang " +
            "       where painting_mongddang.painting_id = painting.id) mongddangCount, " +
            "       name, " +
            "       painting_url paintingUrl, " +
            "       description, create_datetime createDatetime " +
            "from painting " +
            "where drops_id = #{dropsId}")
    public List<PaintingDetailDTO> findPaintingsByDropsId(Long dropsId,String userId);

    @Update("update drops " +
            "set genre_id = IFNULL(#{genreId}, genre_id), " +
            "name = IFNULL(#{name}, name), " +
            "type_id = IFNULL(#{typeId}, type_id) " +
            "where id = #{dropsId}")
    public Integer update(DropsUpdateBuilderDTO dropsUpdateBuilderDTO);

    @Delete("delete " +
            "from drops " +
            "where id = #{dropsId}")
    public Integer delete(Long dropsId);
}

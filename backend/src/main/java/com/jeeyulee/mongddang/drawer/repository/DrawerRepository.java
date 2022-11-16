package com.jeeyulee.mongddang.drawer.repository;

import com.jeeyulee.mongddang.drawer.domain.DrawerDTO;
import com.jeeyulee.mongddang.drawer.domain.DrawerDropsDTO;
import com.jeeyulee.mongddang.drawer.domain.DrawerGenreCountDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DrawerRepository {

    @Select("select count(*) " +
            "from painting " +
            "where member_id = #{userId}")
    public Integer countPaintingByUserId(String userId);

    @Select("select (select name " +
            "        from genre " +
            "        where id = P.genre_id) genreName, " +
            "       count(*) count " +
            "from painting P " +
            "where member_id = #{userId} " +
            "group by genre_id")
    public List<DrawerGenreCountDTO> countPaintingGroupingGenre(String userId);

    @Select("select D1.id dropsId, " +
            "       D1.name dropsName, " +
            "       (select name from genre where id = D1.genre_id) dropsGenre, " +
            "       P3.painting_url lastPaintingUrl, " +
            "       D2.count mongddangCount " +
            "from drops D1 " +
            "right outer join (select drops_id, count(member_id) count " +
            "                  from drops_mongddang " +
            "                  where drops_id in (select id " +
            "                                     from drops " +
            "                                     where member_id = #{userId}) " +
            "                  group by drops_id) D2 " +
            "on D1.id = D2.drops_id " +
            "left outer join (select drops_id, painting_url " +
            "                 from painting " +
            "                 where member_id = #{userId} " +
            "                 order by create_datetime desc " +
            "                 limit 0, 1) P3 " +
            "on D2.drops_id = P3.drops_id")
    public List<DrawerDropsDTO> findDropsByUserId(String userId);

    @Select("select D.*," +
            "(select P.painting_url from painting P where D.member_id = P.member_id and D.id = P.drops_id order by create_datetime desc limit 0,1)lastPaintingUrl " +
            "from drops D where D.member_id = #{userId}")
    public List<DrawerDTO> retrieveMyDrops(String userId);
}

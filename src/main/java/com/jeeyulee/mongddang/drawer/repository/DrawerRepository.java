package com.jeeyulee.mongddang.drawer.repository;

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
            "        where genre_id = P.genre_id) genreName, " +
            "       count(*) count " +
            "from painting P " +
            "where member_id = #{userId} " +
            "group by genre_id")
    public List<DrawerGenreCountDTO> countPaintingGroupingGenre(String userId);

    @Select("select D2.id dropsId, " +
            "       D2.name dropsName, " +
            "       (select name from genre where id = D2.genre_id) dropsGenre, " +
            "       (select name from drops_type where id = D2.type_id) dropsType, " +
            "       D2.painting_url lastPaintingUrl, " +
            "       DM.count mongddangCount " +
            "from (select D1.id, D1.name, D1.genre_id, D1.type_id, P1.painting_url " +
            "      from (select id, name, genre_id, type_id " +
            "            from drops " +
            "            where member_id = #{userId}) D1 left outer join (select drops_id, painting_url " +
            "                                                             from painting " +
            "                                                             where drops_id in (select id " +
            "                                                                                from drops " +
            "                                                                                where member_id = #{userId}) " +
            "                                                            ) P1 " +
            "            on D1.id = P1.drops_id) D2 right outer join (select drops_id, count(*) count " +
            "                                                         from drops_mongddang " +
            "                                                         where member_id = #{userId} " +
            "                                                         group by drops_id) DM " +
            "on D2.id = DM.drops_id")
    public List<DrawerDropsDTO> findDropsByUserId(String userId);
}

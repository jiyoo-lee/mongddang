package com.jeeyulee.mongddang.search;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SearchRepository {

    @Select("select M.user_id, M.nickname, M.profile_picture as profileUrl, D.name, " +
            "(select G.name from genre G where G.id = D.genre_id ) as genre, " +
            "(select P.painting_url from painting P where P.drops_id = D.id limit 0,1) as paintingUrl " +
            "from drops D " +
            "join member M " +
            "on D.member_id = M.user_id and D.name like CONCAT('%',#{keyword},'%')")
    public List<SearchDropsDTO> retrieveDrops(String keyword);


    @Select("select D.member_id MemberId, D.nickname, D.profile_picture profileUrl, " +
            " P.name,(select G.name from genre G where G.id = P.genre_id) as genre, " +
            "P.description, P.painting_url as paintingUrl, P.id as paintingId " +
            "from painting P " +
            "join (select D2.id, D2.member_id, M.nickname, M.profile_picture " +
            "from Drops D2 join member M " +
            "on D2.member_id = M.user_id )D " +
            "on P.drops_id = D.id and P.name like CONCAT('%',#{keyword},'%')")
    public List<SearchPaintingDTO> retrievePaintings(String keyword);
}

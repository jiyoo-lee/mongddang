package com.jeeyulee.mongddang.artscenter.repository;

import com.jeeyulee.mongddang.artscenter.domain.ArtsCenterResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArtsCenterRepository {


    @Select("select D2.id dropsId, " +
            "       D2.name dropsName, " +
            "       D2.user_id memberId, " +
            "       D2.nickname, " +
            "       D2.profile_picture profileUrl, " +
            "       P2.name paintingName, " +
            "       (select name from genre where genre_id = P2.genre_id) genreName, " +
            "       P2.description, " +
            "       P2.painting_url paintingUrl, " +
            "       P2.mongddangCount paintingMongddangCount " +
            "from (select D.id, D.name, M.user_id, M.nickname, M.profile_picture " +
            "      from member M right outer join (select id, member_id, name " +
            "                                      from drops " +
            "                                      where type_id = (select id from drops_type where name = '몽땅전')) D " +
            "      on M.user_id = D.member_id) D2 " +
            "left outer join (select P.*, max((select count(*) from painting_mongddang where painting_id = P.id)) mongddangCount " +
            "                 from painting P " +
            "                 where P.drops_id in (select id " +
            "                                      from drops " +
            "                                      where type_id = (select id " +
            "                                                       from drops_type " +
            "                                                       where name = '몽땅전')) " +
            "                 group by P.drops_id, id) P2 " +
            "on D2.id = P2.drops_id " +
            "order by P2.create_datetime desc")
    public List<ArtsCenterResponseDTO> findAll();

}

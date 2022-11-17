package com.jeeyulee.mongddang.drawer.repository;

import com.jeeyulee.mongddang.drawer.domain.DrawerDTO;
import com.jeeyulee.mongddang.drawer.domain.DrawerDropsDTO;
import com.jeeyulee.mongddang.drawer.domain.DrawerGenreCountDTO;
import com.jeeyulee.mongddang.drawer.domain.DrawerUserInfoDTO;
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

    @Select("select D.id dropsId, D.name dropsName, (select name from genre " +
            "                       where id = D.genre_id) dropsGenre, " +
            "                      (select P.painting_url from painting P where P.drops_id = D.id order by P.create_datetime desc limit 0,1) lastPaintingUrl, " +
            "                      (select count(member_id) from drops_mongddang where drops_id = D.id)mongddangCount " +
            "                        from drops D where member_id = #{userId}")
    public List<DrawerDropsDTO> findDropsByUserId(String userId);

    @Select("select D.*," +
            "(select P.painting_url from painting P where D.member_id = P.member_id and D.id = P.drops_id order by create_datetime desc limit 0,1)lastPaintingUrl " +
            "from drops D where D.member_id = #{userId}")
    public List<DrawerDTO> retrieveMyDrops(String userId);

    @Select("select profile_picture profileUrl, (select count(member_id) from social where follow_member_id = #{drawerUserId}) followerCount, " +
            "(select count(follow_member_id) from social where member_id= #{drawerUserId}) followingCount, " +
            "(select if(S1.follow_member_id is null, false, true) " +
            "       from social S1 " +
            "       left outer join social S2 " +
            "       on S1.follow_member_id=S2.member_id and S1.member_id= S2.follow_member_id " +
            "       where S1.follow_member_id = #{drawerUserId} and S1.member_id = #{userId} )isFollow " +
            "from member where user_id = #{drawerUserId} ")
    public DrawerUserInfoDTO findMemberInfo(String userId, String drawerUserId);
}

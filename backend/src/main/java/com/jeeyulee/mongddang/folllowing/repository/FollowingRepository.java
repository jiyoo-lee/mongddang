package com.jeeyulee.mongddang.folllowing.repository;

import com.jeeyulee.mongddang.folllowing.domain.FollowCountDTO;
import com.jeeyulee.mongddang.folllowing.domain.FollowingDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FollowingRepository {

    @Insert("insert into social(member_id, follow_member_id) " +
            "value(#{userIdOnToken}, #{userId})")
    public Integer save(String userIdOnToken, String userId);

    @Select("select M.user_id as memberId , M.nickname, M.profile_picture as profileUrl from member M "+
            "join (select * from social where member_id = #{userIdOnToken}) S "+
            "on S.follow_member_id = M.user_id ")
    public List<FollowingDTO> retrieveFollowingsById(String userIdOnToken);

    @Select("select M.user_id as memberId, M.nickname, M.profile_picture as profileUrl "+
            "from member M " +
            "join (select * from social where follow_member_id = #{userIdOnToken} )S "+
            "on S.member_id = M.user_id ")
    public List<FollowingDTO> retrieveFollowersById(String userIdOnToken);

    @Select("select M.user_id memberId, M.nickname, M.profile_picture profileUrl " +
            "from member M join (select member_id " +
            "                    from social " +
            "                    where follow_member_id = #{userId} " +
            "                    and member_id not in (select follow_member_id " +
            "                                          from social " +
            "                                          where member_id = #{userId})) S " +
            "on M.user_id = S.member_id")
    public List<FollowingDTO> retrieveRecommendFriends(String userId);

    @Select("select count(member_id) followers, " +
            "       (select count(follow_member_id) " +
            "       from social " +
            "       where member_id = #{userId}) followings " +
            "from social " +
            "where follow_member_id = #{userId}")
    public FollowCountDTO retrieveCountFollowers(String userId);

    @Select("select P.member_id," +
            "   (select M.nickname from member M where M.user_id = P.member_id)nickname," +
            "   (select M.profile_picture from member M where M.user_id = P.member_id)profileUrl " +
            "   from painting P where P.member_id in (select follow_member_id from social where member_id = #{userId}) " +
            "and create_datetime > date_add(now(), interval -7 day) " +
            "group by P.member_id")
    public List<FollowingDTO> retrieveLastUpdatedFriends(String userId);


    @Delete("delete from social " +
            "where member_id = #{userIdOnToken} and follow_member_id = #{userId} ")
    public Integer delete(String userIdOnToken, String userId);



}

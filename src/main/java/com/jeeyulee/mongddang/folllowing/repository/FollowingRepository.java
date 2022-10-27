package com.jeeyulee.mongddang.folllowing.repository;

import com.jeeyulee.mongddang.folllowing.domain.FollowingDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    @Select("select M.user_id memberId, M.nickname, M.profile_picture profileUrl from member M "+
            "right outer join " +
            "(select member_id from social " +
            "where member_id != #{userIdOnToken} " +
            "and follow_member_id != #{userIdOnToken} " +
            "group by member_id)S on M.user_id = S.member_id")
    public List<FollowingDTO> retrieveRecommendFriends(String userIdOnToken);

    @Delete("delete from social " +
            "where member_id = #{userIdOnToken} and follow_member_id = #{userId} ")
    public Integer delete(String userIdOnToken, String userId);
}

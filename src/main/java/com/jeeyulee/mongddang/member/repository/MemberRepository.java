package com.jeeyulee.mongddang.member.repository;

import com.jeeyulee.mongddang.member.dto.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MemberRepository {

    @Insert("insert into member (user_id, password, name, nickname, phone_number, address) " +
            "value (#{userId}, SHA2(#{password}, 512), #{name}, #{nickname}, #{phoneNumber}, #{address})")
    public int save(MemberJoinDTO memberJoinDTO);

    @Select("select * " +
            "from member " +
            "where user_id = #{userId} " +
            "and password = SHA2(#{password}, 512)")
    public MemberLoginResponseDTO findByUserIdAndPassword(MemberLoginDTO memberLoginDTO);

    @Select("select count(*) " +
            "from member "+
            "where user_id = #{userId}")
    public Integer countById(String userId);

    @Select("select * " +
            "from member " +
            "where user_id = #{userId}")
    public MemberDTO findById(String userId);

    @Update("update member " +
            "set password = IFNULL(SHA2(#{password}, 512), password), " +
            "    nickname = IFNULL(#{nickname}, nickname), " +
            "    profile_picture = IFNULL(#{profilePicture}, profile_picture), " +
            "    phone_number = IFNULL(#{phoneNumber}, phone_number), " +
            "    address = IFNULL(#{address}, address)" +
            "where user_id = #{userId}")
    public Integer update(MemberDTO memberDTO);


    @Insert("insert into login_history (member_id, access_ip, latitude, longitude) " +
            "value (#{userId}, inet_aton(#{accessIp}), #{latitude}, #{longitude})")
    public Integer saveLogInHistory(MemberLoginDTO memberLoginDTO);


    @Delete("delete from member where user_id = #{userId}")
    public int resignMember(String userId);

    @Update("update member " +
            "set last_access_token = #{jwt}" +
            "where user_id = #{userId}")
    public Integer updateToken(String userId,String jwt);

    @Select("select last_access_token " +
            "from member " +
            "where user_id = #{userId}")
    public String findLastTokenById(String userId);
}

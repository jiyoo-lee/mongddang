package com.jeeyulee.mongddang.member.repository;

import com.jeeyulee.mongddang.member.dto.MemberJoinDTO;
import com.jeeyulee.mongddang.member.dto.MemberLoginDTO;
import com.jeeyulee.mongddang.member.vo.MemberVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MemberRepository {

    @Insert("insert into member (user_id, password, name, nickname, phone_number, address) " +
            "value (#{userId}, SHA2(#{password}, 512), #{name}, #{nickname}, #{phoneNumber}, #{address})")
    public int save(MemberJoinDTO memberJoinDTO);

    @Select("select * " +
            "from member " +
            "where user_id = #{userId} " +
            "and password = SHA2(#{password}, 512)")
    public MemberVO findByUserIdAndPassword(MemberLoginDTO memberLoginDTO);

    @Select("select count(*) " +
            "from member "+
            "where user_id = #{userId}")
    public Integer countById(String userId);

    @Select("select * " +
            "from member " +
            "where user_id = #{userId}")
    public MemberVO findById(String userId);

    @Update("update member " +
            "set password = IFNULL(SHA2(#{password}, 512), password), " +
            "    nickname = IFNULL(#{nickname}, nickname), " +
            "    profile_picture = IFNULL(#{profilePicture}, profile_picture), " +
            "    phone_number = IFNULL(#{phoneNumber}, phone_number), " +
            "    address = IFNULL(#{address}, address)" +
            "where user_id = #{userId}")
    public Integer update(MemberVO memberVO);

}

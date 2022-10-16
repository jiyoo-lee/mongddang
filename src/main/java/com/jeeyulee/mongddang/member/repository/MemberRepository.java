package com.jeeyulee.mongddang.member.repository;

import com.jeeyulee.mongddang.member.dto.MemberJoinDTO;
import com.jeeyulee.mongddang.member.dto.MemberLoginDTO;
import com.jeeyulee.mongddang.member.vo.MemberVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

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
}

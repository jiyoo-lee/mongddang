package com.jeeyulee.mongddang.member.repository;

import com.jeeyulee.mongddang.member.dto.MemberJoinDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberRepository {

    @Insert("insert into member (user_id, password, name, nickname, phone_number, address) " +
            "value (#{userId}, #{password}, #{name}, #{nickname}, #{phoneNumber}, #{address})")
    public int save(MemberJoinDTO memberJoinDTO);
}

package com.jeeyulee.mongddang.admin.repository;

import com.jeeyulee.mongddang.admin.domain.AdminMemberDTO;
import com.jeeyulee.mongddang.admin.domain.AdminPaintingDTO;
import com.jeeyulee.mongddang.admin.domain.AdminResignDTO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminRepository {

    @Select("select M.user_id memberId, M.name, M.nickname, M.email, M.phone_number phoneNumber, " +
            "       (select max(create_datetime) " +
            "        from login_history where member_id = M.user_id) lastAccessDatetime " +
            "from member M where admin = false order by M.name")
    public List<AdminMemberDTO> retrieveAllMembers();


    @Delete("delete from member " +
            "where user_id = #{memberId}")
    public Integer deleteMember(AdminResignDTO adminResignDTO);

    @Select("select P.id paintingId, P.name paintingName, M.user_id memberId, M.name, M.nickname, " +
            "P.create_datetime createDatetime " +
            "from painting P join member M on P.member_id = M.user_id")
    public List<AdminPaintingDTO> retrieveAllPainting();

    @Delete("delete from painting " +
            "where id = #{paintingId}")
    public Integer deletePainting();

}

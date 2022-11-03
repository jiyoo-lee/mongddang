package com.jeeyulee.mongddang.admin.repository;

import com.jeeyulee.mongddang.admin.domain.AdminMemberDTO;
import com.jeeyulee.mongddang.admin.domain.AdminPaintingDTO;
import com.jeeyulee.mongddang.admin.domain.AdminResignDTO;
import com.jeeyulee.mongddang.artscenter.domain.ContestBuilderDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminRepository {

    @Insert("insert into contest (admin_id, title, poster_url, start_day, end_day) " +
            "value (#{memberId},#{title},#{posterUrl},#{startDay},#{endDay}) ")
    Integer saveContest(ContestBuilderDTO contestBuilderDTO);

    @Select("select M.user_id memberId, M.name, M.nickname, M.email, M.phone_number phoneNumber, " +
            "       (select max(create_datetime) " +
            "        from login_history where member_id = M.user_id) lastAccessDatetime " +
            "from member M where admin = false order by M.name")
    List<AdminMemberDTO> retrieveAllMembers();

    @Select("select P.id paintingId, P.name paintingName, M.user_id memberId, M.name, M.nickname, " +
            "P.create_datetime createDatetime " +
            "from painting P join member M on P.member_id = M.user_id")
    List<AdminPaintingDTO> retrieveAllPainting();

    @Delete("delete from member " +
            "where user_id = #{memberId}")
    Integer deleteMember(AdminResignDTO adminResignDTO);


    @Delete("delete from painting " +
            "where id = #{paintingId}")
    Integer deletePainting(Long paintingId);

}

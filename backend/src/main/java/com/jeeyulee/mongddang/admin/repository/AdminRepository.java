package com.jeeyulee.mongddang.admin.repository;

import com.jeeyulee.mongddang.admin.domain.*;
import com.jeeyulee.mongddang.painting.comment.domain.CommentDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminRepository {

    @Insert("insert into contest (admin_id, title, poster_url, start_day, end_day) " +
            "value (#{memberId},#{title},#{posterUrl},#{startDay},#{endDay}) ")
    Integer saveContest(ContestDTO contestDTO);

    @Select("select M.user_id memberId, M.name, M.nickname, M.email, M.phone_number phoneNumber, " +
            "       (select max(create_datetime) " +
            "        from login_history where member_id = M.user_id) lastAccessDatetime " +
            "from member M where admin = false order by M.name")
    List<AdminMemberDTO> retrieveAllMembers();

    @Select("select id contestId, admin_id memberId, " +
            "title, poster_url posterUrl, start_day startDay, end_day endDay, " +
            "deadline, create_datetime createDatetime" +
            " from contest order by endDay desc")
    List<ContestDTO> retrieveAllContest();


    @Select("select P.id paintingId, P.name paintingName, P.painting_url paintingUrl, P.description, M.user_id memberId, M.name, M.nickname, " +
            "P.create_datetime createDatetime " +
            "from painting P join member M on P.member_id = M.user_id order by P.create_datetime desc")
    List<AdminPaintingDTO> retrieveAllPainting();

    @Select("select M.user_id memberId, M.name, M.nickname, M.email, M.phone_number phoneNumber, " +
            "       (select max(create_datetime) " +
            "        from login_history where member_id = M.user_id) lastAccessDatetime " +
            "from member M where admin = false " +
            "and M.name like CONCAT('%',#{keyword},'%') " +
            "or M.user_id like CONCAT('%',#{keyword},'%') order by M.name")
    List<AdminMemberDTO> findMemberByKeyword(String keyword);

    @Select("select P.id paintingId, P.name paintingName, P.painting_url paintingUrl, P.description, " +
            " M.user_id memberId, M.name, M.nickname, " +
            "P.create_datetime createDatetime " +
            "from painting P join member M on P.member_id = M.user_id " +
            "where P.name like CONCAT('%',#{keyword},'%') order by P.create_datetime desc")
    List<AdminPaintingDTO> findPaintingByKeyword(String keyword);

    @Select("select id contestId, admin_id memberId, title," +
            "poster_url posterUrl, deadline, start_day startDay," +
            "end_Day EndDay, create_datetime CreateDatetime " +
            "from contest where title like CONCAT('%',#{keyword},'%')")
    List<ContestDTO> retrieveContestByKeyword(String keyword);


    @Update("update contest " +
            "set title = IFNULL(#{title},title), " +
            "poster_url = IFNULL(#{contestPaintingUrl}, poster_url), " +
            "start_day = IFNULL(#{startDay},start_day), " +
            "end_day = IFNULL(#{endDay}, end_day) " +
            "where id = #{contestId}")
    Integer updateContest(ContestUpdateBuilderDTO commentDTO);

    @Update("update contest set deadline = true where id = #{contestId}")
    Integer updateDeadline(Long contestId);

    @Delete("delete from member " +
            "where user_id = #{memberId}")
    Integer deleteMember(String memberId);


    @Delete("delete from painting " +
            "where id = #{paintingId}")
    Integer deletePainting(Long paintingId);

}

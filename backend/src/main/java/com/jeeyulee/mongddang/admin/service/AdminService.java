package com.jeeyulee.mongddang.admin.service;

import com.jeeyulee.mongddang.admin.domain.*;

import java.util.List;

public interface AdminService {

    List<AdminMemberDTO> retrieveAllMembers();

    Boolean resignMember(AdminResignDTO adminResignDTO);

    Boolean saveContest(ContestDTO contestDTO);

    List<AdminPaintingDTO> retrieveAllPaintings();

    Boolean deleteAllPaintings(Long paintingId);

    List<AdminMemberDTO> findMemberByKeyword(String keyword);

    List<AdminPaintingDTO> findPaintingByKeyword(String keyword);

    Boolean updateContest(Long contestId, ContestUpdateDTO contestUpdateDTO);

}

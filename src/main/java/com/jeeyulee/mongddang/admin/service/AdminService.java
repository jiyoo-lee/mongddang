package com.jeeyulee.mongddang.admin.service;

import com.jeeyulee.mongddang.admin.domain.AdminMemberDTO;
import com.jeeyulee.mongddang.admin.domain.AdminPaintingDTO;
import com.jeeyulee.mongddang.admin.domain.AdminResignDTO;
import com.jeeyulee.mongddang.artscenter.domain.ContestDTO;

import java.util.List;

public interface AdminService {

    List<AdminMemberDTO> retrieveAllMembers();

    Boolean resignMember(AdminResignDTO adminResignDTO);

    Boolean saveContest(ContestDTO contestDTO);

    List<AdminPaintingDTO> retrieveAllPaintings();

    Boolean deleteAllPaintings(Long paintingId);

}

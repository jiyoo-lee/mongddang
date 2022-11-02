package com.jeeyulee.mongddang.admin.service;

import com.jeeyulee.mongddang.admin.domain.AdminMemberDTO;
import com.jeeyulee.mongddang.admin.domain.AdminPaintingDTO;
import com.jeeyulee.mongddang.admin.domain.AdminResignDTO;

import java.util.List;

public interface AdminService {

    List<AdminMemberDTO> retrieveAllMembers();

    Boolean resignMember(AdminResignDTO adminResignDTO);

    List<AdminPaintingDTO> retrieveAllPaintings();

    Boolean deleteAllPaintings();

}

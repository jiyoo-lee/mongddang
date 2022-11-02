package com.jeeyulee.mongddang.admin.service;

import com.jeeyulee.mongddang.admin.domain.AdminMemberDTO;
import com.jeeyulee.mongddang.admin.domain.AdminPaintingDTO;
import com.jeeyulee.mongddang.admin.domain.AdminResignDTO;
import com.jeeyulee.mongddang.admin.repository.AdminRepository;
import com.jeeyulee.mongddang.common.result.ResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;

    @Override
    public List<AdminMemberDTO> retrieveAllMembers() {
        return adminRepository.retrieveAllMembers();
    }

    @Override
    public Boolean resignMember(AdminResignDTO adminResignDTO) {

        return adminRepository.deleteMember(adminResignDTO) > 0;
    }

    @Override
    public List<AdminPaintingDTO> retrieveAllPaintings() {

        try {
            return adminRepository.retrieveAllPainting();
        }catch (Exception e){
            throw new ResultException("그림 조회에 실패하였습니다.");
        }
    }

    @Override
    public Boolean deleteAllPaintings() {

        return adminRepository.deletePainting() > 0;
    }
}

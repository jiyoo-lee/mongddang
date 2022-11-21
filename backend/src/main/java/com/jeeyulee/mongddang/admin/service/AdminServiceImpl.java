package com.jeeyulee.mongddang.admin.service;

import com.jeeyulee.mongddang.admin.domain.*;
import com.jeeyulee.mongddang.admin.repository.AdminRepository;
import com.jeeyulee.mongddang.common.result.ResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    public Boolean resignMember(String memberId) {

        return adminRepository.deleteMember(memberId) > 0;
    }

    @Override
    public Boolean saveContest(ContestDTO contestDTO) {

        return adminRepository.saveContest(contestDTO) > 0;
    }

    @Override
    public List<ContestDTO> retrieveAllContest() {
        return adminRepository.retrieveAllContest();
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
    public Boolean deleteAllPaintings(Long paintingId) {
        if(paintingId == null) throw new ResultException("잘못된 요청입니다.");
        return adminRepository.deletePainting(paintingId) > 0;
    }

    @Override
    public List<AdminMemberDTO> findMemberByKeyword(String keyword) {
        if(keyword == null) throw new ResultException("키워드가 없습니다.");

        return adminRepository.findMemberByKeyword(keyword);
    }

    @Override
    public List<AdminPaintingDTO> findPaintingByKeyword(String keyword) {
        if(keyword == null) throw new ResultException("키워드가 없습니다.");

        return adminRepository.findPaintingByKeyword(keyword);
    }

    @Override
    public Boolean updateContest(Long contestId, ContestUpdateDTO contestDTO) {


        ContestUpdateBuilderDTO builderDTO = ContestUpdateBuilderDTO.builder()
                .contestId(contestId)
                .title(contestDTO.getTitle())
                .posterUrl(contestDTO.getPosterUrl())
                .startDay(contestDTO.getStartDay())
                .endDay(contestDTO.getEndDay())
                .build();
        return adminRepository.updateContest(builderDTO) > 0;
    }

    @Override
    public Boolean closeContest(Long contestId) {
        return adminRepository.updateDeadline(contestId) > 0;
    }

    @Override
    public List<ContestDTO> retrieveContestByKeyword(String keyword) {
        return adminRepository.retrieveContestByKeyword(keyword);
    }


    private String convertToUUID(Object first, String title){
        return UUID.randomUUID().toString() + "_" + first + "_" + title;
    }
}

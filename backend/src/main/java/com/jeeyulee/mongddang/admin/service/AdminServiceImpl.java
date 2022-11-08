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
    public Boolean resignMember(AdminResignDTO adminResignDTO) {

        return adminRepository.deleteMember(adminResignDTO) > 0;
    }

    @Override
    public Boolean saveContest(ContestDTO contestDTO) {

        if(contestDTO.getExtension() == null){
            throw new ResultException("파일이 없습니다.");
        }

        String fileName =
                convertToUUID(contestDTO.getStartDay(),contestDTO.getTitle());
        ContestBuilderDTO contestBuilderDTO = ContestBuilderDTO.builder()
                .memberId(contestDTO.getMemberId())
                .title(contestDTO.getTitle())
                .posterUrl(fileName + contestDTO.getExtension())
                .startDay(contestDTO.getStartDay())
                .EndDay(contestDTO.getEndDay())
                .build();

        return adminRepository.saveContest(contestBuilderDTO) > 0;
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

        if(contestDTO.getExtension() == null) throw new ResultException("파일이 없습니다.");
        String fileName =
                convertToUUID(contestDTO.getStartDay(),contestDTO.getTitle());

        ContestUpdateBuilderDTO builderDTO = ContestUpdateBuilderDTO.builder()
                .contestId(contestId)
                .title(contestDTO.getTitle())
                .contestPaintingUrl(fileName + contestDTO.getExtension())
                .startDay(contestDTO.getStartDay())
                .endDay(contestDTO.getEndDay())
                .build();
        return adminRepository.updateContest(builderDTO) > 0;
    }


    private String convertToUUID(Object first, String title){
        return UUID.randomUUID().toString() + "_" + first + "_" + title;
    }
}

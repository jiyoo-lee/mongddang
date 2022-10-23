package com.jeeyulee.mongddang.drops.service;

import com.jeeyulee.mongddang.drops.domain.*;
import com.jeeyulee.mongddang.drops.exception.DeniedUserAccessException;
import com.jeeyulee.mongddang.drops.repository.DropsRepository;
import com.jeeyulee.mongddang.member.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DropsServiceImpl implements DropsService{

    private final DropsRepository dropsRepository;

    private final JwtService jwtService;

    @Override
    public Boolean createDrop(DropsDTO dropsDTO) {
        return dropsRepository.save(dropsDTO) > 0;
    }

    @Override
    public DropsDetailDTO retrieveDropsDetail(Long dropsId) throws DeniedUserAccessException{
        DropsDetailDTO dropsDetailDTO = dropsRepository.findById(dropsId);
        String userIdOnToken = jwtService.retrieveUserId();

        if (isNormalDrops(dropsDetailDTO.getDropsType()) ||
            isOwner(userIdOnToken, dropsDetailDTO.getMemberId()) ||
            hasAuthority(userIdOnToken, dropsId)) {

            dropsDetailDTO.setPaintings(dropsRepository.findPaintingsByDropsId(dropsId));
            return dropsDetailDTO;
        }

        throw new DeniedUserAccessException();
    }

    private boolean isNormalDrops(String dropTypeName) {
        return DropsType.NORMAL.getName().equals(dropTypeName);
    }

    private boolean isOwner(String userIdOnToken, String userIdOnDrops) {
        return userIdOnToken.equals(userIdOnDrops);
    }

    private boolean hasAuthority(String userId, Long dropsId) {
        return dropsRepository.countByMemberIdAndDropsId(userId, dropsId) > 0;
    }

    @Override
    public Boolean updateDrop(Long dropsId, DropsUpdateDTO dropsUpdateDTO) {
        DropsUpdateBuilderDTO builderDTO = DropsUpdateBuilderDTO.builder()
                .dropsId(dropsId)
                .genreId(dropsUpdateDTO.getGenreId())
                .name(dropsUpdateDTO.getName())
                .typeId(dropsUpdateDTO.getTypeId())
                .build();
        return dropsRepository.update(builderDTO) > 0;
    }

    @Override
    public Boolean deleteDrop(Long dropsId) {
        return dropsRepository.delete(dropsId) > 0;
    }


}

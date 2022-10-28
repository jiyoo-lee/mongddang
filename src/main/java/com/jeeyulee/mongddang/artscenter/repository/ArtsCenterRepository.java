package com.jeeyulee.mongddang.artscenter.repository;

import com.jeeyulee.mongddang.artscenter.domain.ArtsCenterDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArtsCenterRepository {


    @Select("select ")
    public List<ArtsCenterDTO> retrieveWinners();

}

package com.jeeyulee.mongddang.search;

import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.common.result.ResultException;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class SearchController {
    private final SearchService searchService;

    @ApiOperation(value = "드랍 제목 검색 API", notes = "드랍 제목 검색 API")
    @GetMapping("/drops/{keyword}")
    public ResponseEntity<ResultDTO> retrieveSearchBydropsName(@PathVariable String keyword){

        ResultDTO result = new ResultDTO();
        try {
            result.setData(searchService.retrieveSearch(keyword));
            result.setSuccess(true);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (ResultException e){
            result.setSuccess(false);
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "그림 제목 검색 API", notes = "그림 제목 검색 API")
    @GetMapping("painting/{keyword}")
    public ResponseEntity<ResultDTO> retrieveSearchByPaintingName(@PathVariable String keyword){

        ResultDTO result = new ResultDTO();
        try{
            result.setSuccess(true);
            result.setData(searchService.retrieveByPaintingName(keyword));
            return new ResponseEntity<> (result, HttpStatus.OK);
        }catch (ResultException e){
            result.setSuccess(false);
            return new ResponseEntity<> (result, HttpStatus.BAD_REQUEST);
        }
    }
}

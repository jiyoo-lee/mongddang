package com.jeeyulee.mongddang.drawer.controller;

import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.drawer.service.DrawerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/drawer")
public class DrawerController {

    private final DrawerService drawerService;

    @GetMapping("/{userId}")
    public ResponseEntity<ResultDTO> retrieveDrawer(@NotBlank @PathVariable String userId) {
        ResultDTO result = new ResultDTO(true, drawerService.retrieveDrawer(userId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<ResultDTO> retrieveMyDrawer(@NotBlank @PathVariable String userId) {
        ResultDTO result = new ResultDTO(true, drawerService.retrieveMyDrawer(userId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

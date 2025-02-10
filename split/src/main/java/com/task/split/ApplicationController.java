package com.task.split;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
    Rest-Controller for money splitting and difference
 */
@RestController
@RequestMapping("/api")
public class ApplicationController {

    MoneySplitService service;

    public ApplicationController (MoneySplitService service){
        this.service = service;
    }

    @GetMapping("/split/{money}")
    public ResponseEntity<SplitEntity> getSplitInfo (@PathVariable Double money){
        ResponseEntity<SplitEntity> result = new ResponseEntity<>(service.getSplitInfo(money),HttpStatus.OK);
        return result;
    }


}

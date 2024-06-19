package com.ticod.newbeoms_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HomeController {

    /**
     * 해당 출간일의 기사 반환
     */
    @GetMapping("/{date}")
    public String getPublicationByDate(@RequestParam("date") String date) {
        return date;
    }

    /**
     * 해당 태그에 관련된 기사 반환
     */
    @GetMapping("/news/{tags}")
    public String getNewsByTag(@RequestParam("tags") String tag) {
        return tag;
    }

}

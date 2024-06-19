package com.ticod.newbeoms_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    /**
     * 해당 출간일의 기사 반환
     */
    @GetMapping("/publication/{date}")
    public String getPublicationByDate(@RequestParam("date") String date) {
        return date;
    }

    /**
     * 출간일 및 기사 추가
     */
    @PostMapping("/publication")
    public String addPublication() {
        return "ok";
    }

    /**
     * 해당 태그에 관련된 기사 반환
     */
    @GetMapping("/news/{tags}")
    public String getNewsByTag(@RequestParam("tags") String tag) {
        return tag;
    }

}

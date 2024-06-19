package com.ticod.newbeoms_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HomeController {

    /**
     * 해당 출간일의 기사 반환
     */
    @GetMapping("/datas/{date}")
    public String getDataByDate(@RequestParam("date") Date date) {
        return date.toString();
    }

    /**
     * 해당 태그에 관련된 기사 반환
     */
    @GetMapping("/datas/news/{tags}")
    public String getNewsByTag(@RequestParam("tag") String tag) {
        return tag;
    }

}

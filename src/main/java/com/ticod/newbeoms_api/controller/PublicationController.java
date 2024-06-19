package com.ticod.newbeoms_api.controller;

import com.ticod.newbeoms_api.dto.PublicationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@Controller
public class PublicationController {

    /**
     * 해당 출간일의 기사 반환
     */
    @GetMapping("/publication/{date}")
    public String getPublicationByDate(@PathVariable("date") LocalDate date) {
        log.info(String.valueOf(date));
        return "news";
    }

    /**
     * 출간일 및 기사 추가
     */
    @PostMapping("/publication")
    public String addPublication(@RequestBody PublicationDto publicationDto) {
        return "ok";
    }

    /**
     * 해당 태그에 관련된 기사 반환
     */
    @GetMapping("/publication/news/{tags}")
    public String getNewsByTag(@PathVariable("tags") String tag) {
        return tag;
    }

}

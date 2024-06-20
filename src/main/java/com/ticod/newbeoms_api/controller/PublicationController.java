package com.ticod.newbeoms_api.controller;

import com.ticod.newbeoms_api.dto.NewsDto;
import com.ticod.newbeoms_api.dto.PublicationDto;
import com.ticod.newbeoms_api.service.PublicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
public class PublicationController {

    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    /**
     * 해당 출간일의 기사 반환
     */
    @GetMapping("/publication/{date}")
    public String getPublicationByDate(@PathVariable("date") LocalDate date, Model model) {
        log.info(String.valueOf(date));
        PublicationDto publicationDto = publicationService.getPublicationDto(date);
        model.addAttribute("publication", publicationDto);
        return "news";
    }

    /**
     * 출간일 및 기사 추가
     */
    @PostMapping("/publication")
    public String addPublication(@RequestBody PublicationDto publicationDto) {
        log.info(publicationDto.toString());
        return "ok";
    }

    /**
     * 해당 태그에 관련된 기사 반환
     */
    @GetMapping("/publication/news/{tags}")
    public String getNewsByTag(@PathVariable("tags") String uriTag, Model model) {
        List<String> tags = List.of(uriTag.split(" "));
        List<NewsDto> newsList = publicationService.getNewsByTag(tags)
                .stream().map(NewsDto::from).toList();
        model.addAttribute("newsList", newsList);
        return "newsTagSearch";
    }

}

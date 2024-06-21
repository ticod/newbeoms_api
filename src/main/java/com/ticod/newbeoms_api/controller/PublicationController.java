package com.ticod.newbeoms_api.controller;

import com.ticod.newbeoms_api.dto.NewsDto;
import com.ticod.newbeoms_api.dto.PublicationDto;
import com.ticod.newbeoms_api.entity.Tag;
import com.ticod.newbeoms_api.service.PublicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        List<String> tags = List.of(uriTag.trim().split(" "));
        List<Tag> tagList = publicationService.getTagsByContents(tags);
        Set<NewsDto> result = new HashSet<>();
        for (Tag tag : tagList) {
            result.addAll(tag.getNewsTagList().stream().map(
                    newsTag -> NewsDto.from(newsTag.getNews())
            ).toList());
        }
        model.addAttribute("newsList", result);
        return "newsTagSearch";
    }

}

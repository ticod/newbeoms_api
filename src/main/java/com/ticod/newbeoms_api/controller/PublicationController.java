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
     * 해당 태그에 관련된 기사 반환
     */
    @GetMapping("/publication/news/{tags}")
    public String getNewsByTag(@PathVariable("tags") String uriTag, Model model) {
        // 문자열 정리
        List<String> tags = List.of(uriTag.trim().split(","));
        // 태그 추출
        List<Tag> tagList = publicationService.getTagsByContents(tags);
        // 태그에 따른 기사 정리 및 중복된 기사 제거
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

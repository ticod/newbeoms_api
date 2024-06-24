package com.ticod.newbeoms_api.controller;

import com.ticod.newbeoms_api.dto.*;
import com.ticod.newbeoms_api.entity.Publication;
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
        Publication publication = publicationService.getPublication(date);
        if (publication == null) {
            // TODO: 404 페이지 만들기
            return "404";
        }
        List<NewsDto> newsDtoList = publicationService.getNewsList(publication).stream()
                .map(NewsDto::from)
                .toList();
        List<GossipDto> gossipDtoList = publicationService.getGossipList(publication).stream()
                .map(GossipDto::from)
                .toList();
        List<ComingSoonDto> comingSoonDtoList = publicationService.getComingSoonList(publication).stream()
                .map(ComingSoonDto::from)
                .toList();
        List<HardwareNewsDto> hardwareNewsDtoList = publicationService.getHardwareNewsList(publication).stream()
                .map(HardwareNewsDto::from)
                .toList();
        List<WorkCitedDto> workCitedDtoList = publicationService.getWorkCitedList(publication).stream()
                .map(WorkCitedDto::from)
                .toList();

        PublicationDto publicationDto = PublicationDto.of(publication, newsDtoList,
                comingSoonDtoList, gossipDtoList,
                hardwareNewsDtoList, workCitedDtoList);
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

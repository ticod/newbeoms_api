package com.ticod.newbeoms_api.controller;

import com.ticod.newbeoms_api.dto.NewsDto;
import com.ticod.newbeoms_api.dto.PublicationDto;
import com.ticod.newbeoms_api.entity.*;
import com.ticod.newbeoms_api.service.PublicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
public class PublicationRestController {

    private final PublicationService publicationService;

    public PublicationRestController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    /**
     * 출간일 및 기사 추가
     */
    @PostMapping("/rest/publication")
    public String addPublication(@RequestBody PublicationDto publicationDto) {
        log.info(publicationDto.toString());

        Publication targetPublication = publicationService.getPublication(publicationDto.getPublicationDate());
        if (targetPublication != null) {
            return "fail";
        }

        targetPublication = PublicationDto.toPublication(publicationDto);
        Map<News, List<Tag>> newsTagMap
                = PublicationDto.getNewsTagMap(publicationDto, targetPublication);
        Map<Gossip, List<GossipLink>> gossipLinkMap
                = PublicationDto.getGossipLinkMap(publicationDto, targetPublication);
        Map<ComingSoon, List<ComingSoonContent>> comingSoonContentMap
                = PublicationDto.getComingSoonMap(publicationDto, targetPublication);
        List<HardwareNews> hardwareNewsList
                = PublicationDto.getHardwareNewsList(publicationDto, targetPublication);
        List<WorkCited> workCitedList
                = PublicationDto.getWorkCitedList(publicationDto, targetPublication);

        publicationService.addPublication(targetPublication,
                newsTagMap,
                gossipLinkMap,
                comingSoonContentMap,
                hardwareNewsList,
                workCitedList);

        return "ok";
    }

    /**
     * 해당 출간일의 기사 반환
     */
    @GetMapping("/rest/publication/{date}")
    public PublicationDto getPublicationByDate(@PathVariable("date") LocalDate date) {
        return publicationService.getPublicationDto(date);
    }

    /**
     * 해당 태그에 관련된 기사 반환
     */
    @GetMapping("/rest/publication/news/{tags}")
    public List<NewsDto> getNewsByTag(@PathVariable("tags") String uriTag) {
        List<String> tags = List.of(uriTag.trim().split(" "));
        log.info(tags.toString());
        List<Tag> tagList = publicationService.getTagsByContents(tags);
        log.info(tagList.toString());
        Set<NewsDto> result = new HashSet<>();
        for (Tag tag : tagList) {
            result.addAll(tag.getNewsTagList().stream().map(
                    newsTag -> NewsDto.from(newsTag.getNews())
            ).toList());
        }
        log.info(result.toString());
        return result.stream().toList();
    }

}

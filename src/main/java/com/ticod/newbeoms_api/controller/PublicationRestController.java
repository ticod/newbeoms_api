package com.ticod.newbeoms_api.controller;

import com.ticod.newbeoms_api.dto.*;
import com.ticod.newbeoms_api.entity.*;
import com.ticod.newbeoms_api.service.PublicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins="http://localhost:3000")
public class PublicationRestController {

    private final PublicationService publicationService;

    public PublicationRestController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    /**
     * 출간일 및 기사 추가
     */
    @PostMapping("/api/publication")
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
    @GetMapping("/api/publication/{date}")
    public PublicationDto getPublicationByDate(@PathVariable("date") LocalDate date) {
        Publication publication = publicationService.getPublication(date);

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

        return PublicationDto.of(publication, newsDtoList,
                comingSoonDtoList, gossipDtoList,
                hardwareNewsDtoList, workCitedDtoList);
    }

}

package com.ticod.newbeoms_api.controller;

import com.ticod.newbeoms_api.dto.PublicationDto;
import com.ticod.newbeoms_api.entity.*;
import com.ticod.newbeoms_api.service.PublicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

}

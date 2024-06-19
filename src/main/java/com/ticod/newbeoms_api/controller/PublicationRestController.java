package com.ticod.newbeoms_api.controller;

import com.ticod.newbeoms_api.dto.PublicationDto;
import com.ticod.newbeoms_api.service.PublicationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        publicationService.addPublcation(publicationDto);
        return "ok";
    }

}

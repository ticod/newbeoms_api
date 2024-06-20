package com.ticod.newbeoms_api.controller;

import com.ticod.newbeoms_api.dto.PublicationDto;
import com.ticod.newbeoms_api.service.PublicationService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    /**
     * 해당 출간일의 기사 반환
     */
    @GetMapping("/rest/publication/{date}")
    public PublicationDto getPublicationByDate(@PathVariable("date") LocalDate date) {
        return publicationService.getPublicationDto(date);
    }

}

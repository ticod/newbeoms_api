package com.ticod.newbeoms_api.controller;

import com.ticod.newbeoms_api.dto.PublicationDateDto;
import com.ticod.newbeoms_api.service.PublicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    private final PublicationService publicationService;

    public HomeController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<PublicationDateDto> publicationDates = publicationService.getPublicationDates();
        model.addAttribute("publicationDates", publicationDates);
        return "home";
    }

}

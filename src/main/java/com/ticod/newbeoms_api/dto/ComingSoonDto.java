package com.ticod.newbeoms_api.dto;

import com.ticod.newbeoms_api.entity.ComingSoon;
import com.ticod.newbeoms_api.entity.ComingSoonContent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ComingSoonDto {

    private LocalDate date;
    private List<String> contents;

    @Builder
    public ComingSoonDto(LocalDate date, List<String> contents) {
        this.date = date;
        this.contents = contents;
    }

    public static ComingSoonDto of(ComingSoon comingSoon, List<ComingSoonContent> comingSoonContents) {
        return ComingSoonDto.builder()
                .date(comingSoon.getComingSoonDate())
                .contents(comingSoonContents.stream().map(ComingSoonContent::getContent).toList())
                .build();
    }
}
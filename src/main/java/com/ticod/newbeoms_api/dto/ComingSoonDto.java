package com.ticod.newbeoms_api.dto;

import com.ticod.newbeoms_api.entity.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static ComingSoonDto from(ComingSoon comingSoon) {
        return ComingSoonDto.builder()
                .date(comingSoon.getComingSoonDate())
                .contents(comingSoon.getComingSoonContents()
                        .stream().map(ComingSoonContent::getContent).toList())
                .build();
    }

    public static ComingSoon toComingSoon(ComingSoonDto comingSoonDto, Publication publication) {
        return ComingSoon.builder()
                .publication(publication)
                .comingSoonDate(comingSoonDto.getDate())
                .build();
    }

    public static List<ComingSoonContent> toComingSoonContents(ComingSoonDto comingSoonDto, ComingSoon comingSoon) {
        return comingSoonDto.getContents().stream()
                .map(content -> ComingSoonContent.builder()
                        .comingSoon(comingSoon)
                        .content(content)
                        .build())
                .toList();
    }

    public static Map<ComingSoon, List<ComingSoonContent>> toEntityMap(ComingSoonDto comingSoonDto, Publication publication) {
        ComingSoon comingSoon = toComingSoon(comingSoonDto, publication);
        List<ComingSoonContent> comingSoonContents = toComingSoonContents(comingSoonDto, comingSoon);

        Map<ComingSoon, List<ComingSoonContent>> result = new HashMap<>();
        result.put(comingSoon, comingSoonContents);
        return result;
    }
}
package com.ticod.newbeoms_api.dto;

import com.ticod.newbeoms_api.entity.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class PublicationDto {

    private LocalDate publicationDate;
    private List<NewsDto> newsDtoList;
    private List<ComingSoonDto> comingSoonDtoList;
    private List<GossipDto> gossipDtoList;
    private List<HardwareNewsDto> hardwareNewsDtoList;
    private List<WorkCitedDto> workCitedDtoList;

    @Builder
    public PublicationDto(LocalDate publicationDate, List<NewsDto> newsDtoList,
                          List<ComingSoonDto> comingSoonDtoList,
                          List<GossipDto> gossipDtoList,
                          List<HardwareNewsDto> hardwareNewsDtoList,
                          List<WorkCitedDto> workCitedDtoList) {
        this.publicationDate = publicationDate;
        this.newsDtoList = newsDtoList;
        this.comingSoonDtoList = comingSoonDtoList;
        this.gossipDtoList = gossipDtoList;
        this.hardwareNewsDtoList = hardwareNewsDtoList;
        this.workCitedDtoList = workCitedDtoList;
    }

    public static PublicationDto of(Publication publication,
                                    List<NewsDto> newsDtoList,
                                    List<ComingSoonDto> comingSoonDtoList,
                                    List<GossipDto> gossipDtoList,
                                    List<HardwareNewsDto> hardwareNewsDtoList,
                                    List<WorkCitedDto> workCitedDtoList) {

        return PublicationDto.builder()
                .publicationDate(publication.getPublicationDate())
                .newsDtoList(newsDtoList)
                .comingSoonDtoList(comingSoonDtoList)
                .gossipDtoList(gossipDtoList)
                .hardwareNewsDtoList(hardwareNewsDtoList)
                .workCitedDtoList(workCitedDtoList)
                .build();

    }

    public static Map<Gossip, List<GossipLink>> getGossipLinkMap(PublicationDto publicationDto,
                                                                Publication publication) {
        return publicationDto.getGossipDtoList().stream()
                .flatMap(gossipDto -> GossipDto.toEntityMap(gossipDto, publication).entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<News, List<Tag>> getNewsTagMap(PublicationDto publicationDto,
                                                    Publication publication) {
        return publicationDto.getNewsDtoList().stream()
                .flatMap(newsDto -> NewsDto.toEntityMap(newsDto, publication).entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<ComingSoon, List<ComingSoonContent>> getComingSoonMap(PublicationDto publicationDto,
                                                                            Publication publication) {
        return publicationDto.getComingSoonDtoList().stream()
                .flatMap(comingSoonDto -> ComingSoonDto.toEntityMap(comingSoonDto, publication)
                        .entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static List<HardwareNews> getHardwareNewsList(PublicationDto publicationDto,
                                                         Publication publication) {
        return publicationDto.getHardwareNewsDtoList().stream()
                .map(hardwareNewsDto -> HardwareNewsDto.toEntity(hardwareNewsDto, publication))
                .toList();
    }

    public static List<WorkCited> getWorkCitedList(PublicationDto publicationDto,
                                                   Publication publication) {
        return publicationDto.getWorkCitedDtoList().stream()
                .map(workCitedDto -> WorkCitedDto.toEntity(workCitedDto, publication))
                .toList();
    }

}
